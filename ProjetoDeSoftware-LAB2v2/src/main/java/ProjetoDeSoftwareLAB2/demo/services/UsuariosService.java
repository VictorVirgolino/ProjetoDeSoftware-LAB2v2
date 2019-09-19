package ProjetoDeSoftwareLAB2.demo.services;


import ProjetoDeSoftwareLAB2.demo.daos.UsuariosRepository;
import ProjetoDeSoftwareLAB2.demo.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService {

    private UsuariosRepository<Usuario, Long > usuariosDAO;

    public UsuariosService(UsuariosRepository<Usuario, Long> usuariosDAO){
        super();
        this.usuariosDAO = usuariosDAO;
    }


    public Usuario adicionarUsuario(Usuario usuario){
        return usuariosDAO.save(usuario);
    }

    public void deletarUsuario(Usuario usuario){
        usuariosDAO.delete(usuario);
    }

    public Usuario findByEmail(String email) {
        return usuariosDAO.findByEmail(email);
    }


    public List<Usuario> getUsuarios() {
        return usuariosDAO.findAll();
    }
}
