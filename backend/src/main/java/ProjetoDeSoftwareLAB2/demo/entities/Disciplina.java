package ProjetoDeSoftwareLAB2.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Disciplina {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String nome;

    private double nota;
    private String comentarios;
    private int likes;

    public Disciplina(){
        super();
    }

    public Disciplina(int id, String nome){
        super();
        this.id = id;
        this.nome = nome;
        this.nota = 0;
        this.comentarios = "";
        this.likes = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
