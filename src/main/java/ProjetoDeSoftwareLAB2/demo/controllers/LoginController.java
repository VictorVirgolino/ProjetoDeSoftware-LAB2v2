package ProjetoDeSoftwareLAB2.demo.controllers;

import ProjetoDeSoftwareLAB2.demo.entities.Usuario;
import ProjetoDeSoftwareLAB2.demo.services.UsuariosService;
import ProjetoDeSoftwareLAB2.demo.tools.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private UsuariosService usuariosService;

    public LoginController(UsuariosService usuariosService){
        super();
        this.usuariosService = usuariosService;
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody Usuario usuario) throws ServletException {

        Usuario authUsuario = usuariosService.findByEmail(usuario.getEmail());

        if(authUsuario == null){
            throw new ServletException("Usuario nao encontrado!");
        }

        if(!authUsuario.getSenha().equals(usuario.getSenha())){
            throw new ServletException("Senha invalida!");
        }

        String TOKEN_KEY = "HakunaMatata";
        String token = Jwts.builder().
                setSubject(authUsuario.getEmail()).
                signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
                setExpiration(new Date(System.currentTimeMillis() +  60 * 1000)).
                compact();

        return new LoginResponse(token);

    }


}
