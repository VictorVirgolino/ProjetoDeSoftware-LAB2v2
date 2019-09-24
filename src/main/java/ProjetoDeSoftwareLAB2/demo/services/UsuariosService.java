package ProjetoDeSoftwareLAB2.demo.services;


import ProjetoDeSoftwareLAB2.demo.daos.UsuariosRepository;
import ProjetoDeSoftwareLAB2.demo.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    private UsuariosRepository<Usuario, String > usuariosDAO;

    public UsuariosService(UsuariosRepository<Usuario, String> usuariosDAO){
        super();
        this.usuariosDAO = usuariosDAO;
    }


    public Usuario adicionarUsuario(Usuario usuario){
        return usuariosDAO.save(usuario);
    }

    public Usuario deletarUsuario(String email){

        Optional<Usuario> usuario = findByEmail(email);

        if(usuario.isPresent()) {
            usuariosDAO.delete(usuario.get());
            return usuario.get();
        }

        return null;
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuariosDAO.findById(email);
    }


    public List<Usuario> getUsuarios() {
        return usuariosDAO.findAll();
    }

    public void save(List<Usuario> users) {

        for (Usuario usuario: users) {
            adicionarUsuario(usuario);
        }
    }
}
