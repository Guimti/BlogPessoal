package generation.org.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import generation.org.blogPessoal.model.Usuario;
import generation.org.blogPessoal.repository.UsuarioRepository;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Usuario usuario;
	private Usuario usuarioUpdate;
	private Usuario usuarioAdmin;
	
	@BeforeAll
	public void start() {
		
		 usuarioAdmin = new Usuario(0L, "Administrador ", "Admin 01", "1234567");
		 
		 if(!usuarioRepository.findByUsuario(usuarioAdmin.getUsuario()).isPresent()) {
			 
			 HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioAdmin);
			 testRestTemplate.exchange("/usuario/cadastrar", HttpMethod.POST, request, Usuario.class);
			 
		 }
		
		 
		 usuario = new Usuario(0L, "Cristiano Ronaldo ", "Cristiano 07", "1234567");
		 
		 usuarioUpdate = new Usuario(2L, "Cristiano Ronaldo Siiii ", "PapaiCris 07", "1234567");
	}
	
	
	@Test
	@DisplayName("✅ Cadastrar Usuario !")
	@Order(1)
	public void deveReaizarPostUsuario() {
		
		 HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuario/cadastrar", HttpMethod.POST, request, Usuario.class);
		 assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	@Test
	@DisplayName("👍 Listar todos os Usuario !")
	@Order(2)
	public void deveReaizarGetAllUsuario() {
		
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("Admin 01", "1234567").exchange("/usuario/all", HttpMethod.GET, null, String.class);
		 assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	@DisplayName("👊 Alterar Usuario !")
	@Order(3)
	public void deveReaizarPutUsuario() {
		
		 HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioUpdate);
			ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("Admin 01", "1234567").exchange("/usuario/alterar", HttpMethod.PUT, request, Usuario.class);
			 assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	
	
	
	
}
