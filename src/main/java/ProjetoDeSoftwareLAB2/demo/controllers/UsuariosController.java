package ProjetoDeSoftwareLAB2.demo.controllers;

import ProjetoDeSoftwareLAB2.demo.entities.Usuario;
import ProjetoDeSoftwareLAB2.demo.services.UsuariosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuariosController {

    private UsuariosService usuariosService;

    public UsuariosController( UsuariosService usuariosService){
        super();
        this.usuariosService = usuariosService;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> adicionarUsuario(@RequestBody Usuario usuario) {

        Optional<Usuario> authUsuario = usuariosService.findByEmail(usuario.getEmail());

        if(authUsuario.isPresent()){
            return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
        }
        else{
            return new ResponseEntity<Usuario>(usuariosService.adicionarUsuario(usuario), HttpStatus.CREATED);
        }

    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuarios(){
        return new ResponseEntity<List<Usuario>>(usuariosService.getUsuarios(), HttpStatus.OK);
    }
}
