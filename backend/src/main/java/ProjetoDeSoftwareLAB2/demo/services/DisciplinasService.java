package ProjetoDeSoftwareLAB2.demo.services;

import ProjetoDeSoftwareLAB2.demo.daos.DisciplinasRepository;
import ProjetoDeSoftwareLAB2.demo.entities.Disciplina;
import ProjetoDeSoftwareLAB2.demo.tools.ComparatorLike;
import ProjetoDeSoftwareLAB2.demo.tools.ComparatorNota;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinasService {

    private DisciplinasRepository<Disciplina, Long> disciplinasDAO;

    public DisciplinasService(DisciplinasRepository<Disciplina, Long> disciplinasDAO){
        super();
        this.disciplinasDAO = disciplinasDAO;
    }

    public Disciplina adicionaDisciplina(Disciplina disciplina){

        if(DisciplinaExist(disciplina)){
            return null;
        }

        return disciplinasDAO.save(disciplina);
    }

    public void save(List<Disciplina> disciplinas) {
        for (Disciplina disciplina: disciplinas) {
            adicionaDisciplina(disciplina);
        }
    }

    public List<Disciplina> getDisciplinas(){
        return disciplinasDAO.findAll();
    }

    public Optional<Disciplina> getDisciplina(Long id){
        return disciplinasDAO.findById(id);
    }

    public Disciplina deletaDisciplina(Long id){
       Optional<Disciplina> excluido = disciplinasDAO.findById(id);

       if(excluido.isPresent()){
           disciplinasDAO.delete(excluido.get());
           return excluido.get();
       }

       return null;
    }

    public Disciplina incrementaLikes(Long id){
        Optional<Disciplina> disciplina = disciplinasDAO.findById(id);

        if(disciplina.isPresent()){
            Disciplina disciplinaAtual = disciplina.get();
            disciplinaAtual.setLikes(disciplinaAtual.getLikes() + 1);
            return disciplinasDAO.save(disciplinaAtual);
        }

        return null;
    }

    public Disciplina adicionarComentario(Long id, String comentario){
        Optional<Disciplina> disciplina = disciplinasDAO.findById(id);

        if(disciplina.isPresent()){
            Disciplina disciplinaAtual = disciplina.get();
            String novoComentario = disciplinaAtual.getComentarios() + " " + comentario;
            disciplinaAtual.setComentarios(novoComentario);
            return disciplinasDAO.save(disciplinaAtual);
        }

        return null;
    }

    public Disciplina modificarNota(Long id, double nota){
        Optional<Disciplina> disciplina = disciplinasDAO.findById(id);

        if(disciplina.isPresent()){
            Disciplina disciplinaAtual = disciplina.get();
            disciplinaAtual.setNota(nota);
            return disciplinasDAO.save(disciplinaAtual);
        }

        return null;
    }

    public List<Disciplina> listarPorNota(){
        List<Disciplina> disciplinas = this.getDisciplinas();
        disciplinas.sort(new ComparatorNota());
        return disciplinas;
    }

    public List<Disciplina> listarPorLikes(){
        List<Disciplina> disciplinas = this.getDisciplinas();
        disciplinas.sort(new ComparatorLike());
        return disciplinas;
    }

    private Boolean DisciplinaExist(Disciplina disciplina){
        Disciplina existe = disciplinasDAO.findByNome(disciplina.getNome());
        return existe != null;
    }
}
