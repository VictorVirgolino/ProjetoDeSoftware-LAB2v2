package ProjetoDeSoftwareLAB2.demo.tools;

public class EntradaDeDados {

    private String comentario;
    private double nota;
    private String email;

    public EntradaDeDados(String comentario, double nota, String email){
        this.comentario = comentario;
        this.nota = nota;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
