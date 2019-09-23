package ProjetoDeSoftwareLAB2.demo.filtros;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFiltro extends GenericFilterBean {

    public final static int TOKEN_INDEX = 7;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{

        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader("Authorization");

        if (header == null|| !header.startsWith("Bearer ")){
            throw new ServletException("Token inexistente ou mal formatado!");
        }

        String token = header.substring(TOKEN_INDEX);

        try{
            Jwts.parser().setSigningKey("HakunaMatata").parseClaimsJws(token).getBody();
        }   catch(ExpiredJwtException e){
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }

        chain.doFilter(request, response);
    }
}
