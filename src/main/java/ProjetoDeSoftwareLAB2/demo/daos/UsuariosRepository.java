package ProjetoDeSoftwareLAB2.demo.daos;

import ProjetoDeSoftwareLAB2.demo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UsuariosRepository<T, ID extends Serializable> extends JpaRepository<Usuario, String> {

}
