package ProjetoDeSoftwareLAB2.demo;

import ProjetoDeSoftwareLAB2.demo.entities.Disciplina;
import ProjetoDeSoftwareLAB2.demo.entities.Usuario;
import ProjetoDeSoftwareLAB2.demo.filtros.TokenFiltro;
import ProjetoDeSoftwareLAB2.demo.services.DisciplinasService;
import ProjetoDeSoftwareLAB2.demo.services.UsuariosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class
DemoApplication {

	@Bean
	public FilterRegistrationBean<TokenFiltro> filterJwt(){
		FilterRegistrationBean<TokenFiltro> filterRB = new FilterRegistrationBean<TokenFiltro>();
		filterRB.setFilter(new TokenFiltro());
		filterRB.addUrlPatterns("/api/disciplinas", "/auth/usuarios");
		return filterRB;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(UsuariosService usuariosService, DisciplinasService disciplinasService){
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Usuario>> typeReferenceUsuario = new TypeReference <List<Usuario>>(){};
			InputStream inputStreamUsuario = TypeReference.class.getResourceAsStream("/data/usuarios.json");
			TypeReference<List<Disciplina>> typeReferenceDisciplinas = new TypeReference <List<Disciplina>>(){};
			InputStream inputStreamDisciplinas = TypeReference.class.getResourceAsStream("/data/disciplinas.json");
			try {
				List<Usuario> users = mapper.readValue(inputStreamUsuario,typeReferenceUsuario);
				usuariosService.save(users);
				System.out.println("Usuarios Adicionados!");
			} catch (IOException e){
				System.out.println("Não foi possivel adicionar: " + e.getMessage());
			}
			try {
				List<Disciplina> disciplinas = mapper.readValue(inputStreamDisciplinas,typeReferenceDisciplinas);
				disciplinasService.save(disciplinas);
				System.out.println("Disciplinas Adicionadas!");
			} catch (IOException e){
				System.out.println("Não foi possivel adicionar: " + e.getMessage());
			}
		};
	}
}
