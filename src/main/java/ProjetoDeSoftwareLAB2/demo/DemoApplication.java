package ProjetoDeSoftwareLAB2.demo;

import ProjetoDeSoftwareLAB2.demo.filtros.TokenFiltro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

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

}
