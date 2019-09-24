package ProjetoDeSoftwareLAB2.demo.services;

import ProjetoDeSoftwareLAB2.demo.daos.DisciplinasRepository;
import ProjetoDeSoftwareLAB2.demo.entities.Disciplina;
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
        return disciplinasDAO.save(disciplina);
    }

    public List<Disciplina> getDisciplinas(){
        return disciplinasDAO.findAll();
    }

    public Optional<Disciplina> getDisciplina(Long id){
        return disciplinasDAO.findById(id);
    }


    public void save(List<Disciplina> disciplinas) {
        for (Disciplina disciplina: disciplinas) {
            adicionaDisciplina(disciplina);
        }
    }
}
