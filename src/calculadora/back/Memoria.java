package calculadora.back;



import java.util.ArrayList;
import java.util.List;

public class Memoria {

    private static Memoria instancia = new Memoria();
    private String textoAtual = "";
    private String buffer = "";
    private boolean substituir = false;
    private TipoComando comandoOperacao = null;

    private enum TipoComando {
        ZERAR, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, VIRGULA;
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

        // System.out.println(tipoComando);
        if (tipoComando == null) {
            return;
        } else if (tipoComando == TipoComando.ZERAR) {
            textoAtual = "";
            buffer = "";
            substituir = false;
            comandoOperacao = null;
        } else if (tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA) {
            textoAtual = substituir ? texto : textoAtual + texto;
            substituir = false;
        }else if (tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.SOMA) {
            float resultado = Float.parseFloat(texto) + Float.parseFloat(texto) ;
            System.out.println("valor div"+resultado);
        }

        listObeserver.forEach(observer -> observer.valorAlterado(getTextoAtual()));
    }

    private TipoComando detectarTipoComando(String texto) {

        if (textoAtual.isEmpty() && texto == "0") {
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
            case "-":
                return TipoComando.SUB;
            case "X":
                return TipoComando.MULT;
            case "/":
                return TipoComando.DIV;
            case "=":
                return TipoComando.IGUAL;
            case "," :
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
