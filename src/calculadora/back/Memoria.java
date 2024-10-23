package calculadora.back;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

    private static Memoria instancia = new Memoria();
    private String textoAtual = "";
    private String buffer = "";
    private boolean substituir = false;
    private TipoComando ultimaOperacao = null;

    private enum TipoComando {
        SINAL, ZERAR, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, VIRGULA;
    }

    // singleton
    private Memoria() {

    }

    private final List<MemoriaObserver> listObeserver = new ArrayList<>();

    public void adicionarObserver(MemoriaObserver observer) {
        listObeserver.add(observer);
    }

    public void processarComando(String texto) {

        TipoComando tipoComando = detectarTipoComando(texto);

        System.out.println(tipoComando);
        if (tipoComando == null) {
            return;
        } else if (tipoComando == TipoComando.ZERAR) {
            textoAtual = "";
            buffer = "";
            substituir = false;
            ultimaOperacao = null;
        } else if (tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA) {
            textoAtual = substituir ? texto : textoAtual + texto;
            substituir = false;
        } else if(tipoComando == TipoComando.SINAL && textoAtual.contains("-")) {
            textoAtual = textoAtual.substring(1);
        }
        else if(tipoComando == TipoComando.SINAL && !texto.contains("-")) {
            textoAtual = "-" + textoAtual;
        }
        else {

            substituir = true;
            textoAtual = obterResultadoOperacao();
            buffer = textoAtual;
            ultimaOperacao = tipoComando;
        }

        listObeserver.forEach(observer -> observer.valorAlterado(getTextoAtual()));
    }

    private String obterResultadoOperacao() {
        if (ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
            return textoAtual;
        }
        double numeroBuffer = Double.parseDouble(buffer.replace(",", "."));
        double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));
        double resultado = 0;

        // if (ultimaOperacao == TipoComando.SOMA) {
        // resultado = numeroBuffer + numeroAtual;
        // textoAtual = String.valueOf(resultado);
        // }

        switch (ultimaOperacao) {
        case SOMA:
            resultado = numeroBuffer + numeroAtual;
            break;
        case SUB:
            resultado = numeroBuffer - numeroAtual;
            break;
        case SINAL:
            // resultado = numeroBuffer * (numeroAtual < 0 ? -1 : 1);
            System.out.println(TipoComando.SINAL);
            break;
        case MULT:
            resultado = numeroBuffer * numeroAtual;
            break;
        case DIV:
            resultado = numeroBuffer / numeroAtual;
            break;
        case IGUAL:
            textoAtual = String.valueOf(resultado);
            break;

        }
        String resultadoToString = Double.toString(resultado).replace(".", ",");
        boolean inteiro = resultadoToString.endsWith(",0");
        return inteiro ? resultadoToString.replace(",0", "") : resultadoToString; // caso seja inteiro, remover o ",0"
                                                                                  // no final da string

    }

    private TipoComando detectarTipoComando(String texto) {

        if (textoAtual.isEmpty() && texto == null) {
            return null;
        }

        try {
            Integer.parseInt(texto);
            return TipoComando.NUMERO;

        } catch (NumberFormatException e) {
            switch (texto) {
            case "AC":
                return TipoComando.ZERAR;
            case "+":
                return TipoComando.SOMA;
            case "±":
                return TipoComando.SINAL;
            case "-":
                return TipoComando.SUB;
            case "X":
                return TipoComando.MULT;
            case "/":
                return TipoComando.DIV;
            case "=":
                return TipoComando.IGUAL;
            case ",":
                if (!textoAtual.contains(",")) { // so vai adicionar virgula se campo não tiver virgula
                    return TipoComando.VIRGULA;
                }

            }

        }
        return null;

    }

    public static Memoria getInstancia() {
        return instancia;
    }

    public static void setInstancia(Memoria instancia) {
        Memoria.instancia = instancia;
    }

    public String getTextoAtual() {
        return textoAtual.isEmpty() ? "0" : textoAtual;
    }

    public void setTextoAtual(String textoAtual) {
        this.textoAtual = textoAtual;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((textoAtual == null) ? 0 : textoAtual.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Memoria other = (Memoria) obj;
        if (textoAtual == null) {
            if (other.textoAtual != null)
                return false;
        } else if (!textoAtual.equals(other.textoAtual))
            return false;
        return true;
    }

}
