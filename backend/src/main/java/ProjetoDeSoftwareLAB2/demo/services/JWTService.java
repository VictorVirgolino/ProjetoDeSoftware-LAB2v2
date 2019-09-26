package ProjetoDeSoftwareLAB2.demo.services;

import ProjetoDeSoftwareLAB2.demo.entities.Usuario;
import ProjetoDeSoftwareLAB2.demo.filtros.TokenFiltro;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.Optional;

@Service
public class JWTService {

    private UsuariosService usuariosService;

    public JWTService(UsuariosService usuariosService){
        super();
        this.usuariosService = usuariosService;
    }

    public boolean usuarioExiste(String authorizationHeader) throws ServletException{
        String subject = getToken(authorizationHeader);

        return usuariosService.findByEmail(subject).isPresent();
    }

    public boolean usuarioTemPermissao(String authorizationHeader, String email) throws ServletException{
        String subject = getToken(authorizationHeader);

        Optional<Usuario> usuario = usuariosService.findByEmail(subject);
        return usuario.isPresent() && usuario.get().getEmail().equals(email);
    }


    private String getToken(String authorizationHeader) throws ServletException{
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer")){
            throw new ServletException("Token inexistente ou mal formatado!");
        }

        String token = authorizationHeader.substring(TokenFiltro.TOKEN_INDEX);

        String subject = null;

        try{
            subject = Jwts.parser().setSigningKey("HakunaMatata").parseClaimsJws(token).getBody().getSubject();
        } catch(SignatureException e){
            throw new ServletException("Token Invalido ou expirado!");
        }

        return subject;
    }
}
