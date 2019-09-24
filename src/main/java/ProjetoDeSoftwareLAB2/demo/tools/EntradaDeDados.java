package ProjetoDeSoftwareLAB2.demo.tools;

public class EntradaDeDados {

    private String comentario;
    private double nota;

    public EntradaDeDados(String comentario, double nota){
        this.comentario = comentario;
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
