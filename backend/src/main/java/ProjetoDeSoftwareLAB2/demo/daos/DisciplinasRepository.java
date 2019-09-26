package ProjetoDeSoftwareLAB2.demo.daos;

import ProjetoDeSoftwareLAB2.demo.entities.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface DisciplinasRepository<T, ID extends Serializable> extends JpaRepository<Disciplina, Long> {

    public Disciplina findByNome(String nome);
}
