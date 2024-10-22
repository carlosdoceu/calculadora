package calculadora.back;

public class Memoria {

    private static  Memoria instancia = new Memoria();
    private String textoAtual = "";

    //singleton
    private Memoria(){
        
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

