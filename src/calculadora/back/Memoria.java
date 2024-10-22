package calculadora.back;


import java.util.ArrayList;
import java.util.List;

public class Memoria {

    private static Memoria instancia = new Memoria();
    private String textoAtual = "";

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

        System.out.println(tipoComando);

        if ("ac".equalsIgnoreCase(texto)) {
            textoAtual = "";
        } else {
            textoAtual += texto;
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
                case ",":
                    return TipoComando.VIRGULA;
                    
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
