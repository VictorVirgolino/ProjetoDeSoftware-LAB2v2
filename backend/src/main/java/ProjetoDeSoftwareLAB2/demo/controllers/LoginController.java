package ProjetoDeSoftwareLAB2.demo.controllers;

import ProjetoDeSoftwareLAB2.demo.entities.Usuario;
import ProjetoDeSoftwareLAB2.demo.services.JWTService;
import ProjetoDeSoftwareLAB2.demo.services.UsuariosService;
import ProjetoDeSoftwareLAB2.demo.tools.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UsuariosService usuariosService;
    @Autowired
    private JWTService jwtService;

    public LoginController(UsuariosService usuariosService, JWTService jwtService){
        super();
        this.usuariosService = usuariosService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody Usuario usuario) throws ServletException {

        Optional<Usuario> authUsuario = usuariosService.findByEmail(usuario.getEmail());

        if(!authUsuario.isPresent()){
            throw new ServletException("Usuario nao encontrado!");
        }

        if(!authUsuario.get().getSenha().equals(usuario.getSenha())){
            throw new ServletException("Senha invalida!");
        }

        String TOKEN_KEY = "HakunaMatata";
        String token = Jwts.builder().
                setSubject(authUsuario.get().getEmail()).
                signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
                setExpiration(new Date(System.currentTimeMillis() +  3600 * 1000)).
                compact();

        return new LoginResponse(token);

    }

    @DeleteMapping("/usuarios/{email}")
    public ResponseEntity<Usuario> removeUsuario(@PathVariable String email, @RequestHeader("Authorization") String header){
        if(!usuariosService.findByEmail(email).isPresent()){
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
        try{
            if(jwtService.usuarioTemPermissao(header, email)){
                return new ResponseEntity<Usuario>(usuariosService.deletarUsuario(email), HttpStatus.OK);
            }
        } catch (ServletException e) {
            return new ResponseEntity<Usuario>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
    }


}
