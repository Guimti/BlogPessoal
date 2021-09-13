package generation.org.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import generation.org.blogPessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	 void start() {
		
		Usuario usuario = new Usuario(0L, "Cristiano Ronaldo", "Cristiano 07", "1234567");
		
		if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
		
		 usuario = new Usuario(0L, "Lionel Ronaldo", "Messi 30", "1234567");
		
		if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
		
		
		 usuario = new Usuario(0L, "Neymar Ronaldo ", "Neymar 10", "1234567");
		
		if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
		
		 usuario = new Usuario(0L, "Halland Mainz", "Halland 09", "1234567");
		
		if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
		
	}
	
	@Test
	@DisplayName("💾 Retorna Nome")
	public void findByRetornaNome() {
		
		Usuario usuario = usuarioRepository.findByNome("Cristiano Ronaldo");
		assertTrue(usuario.getNome().equals("Cristiano Ronaldo"));
		
	}
	
	@Test
	@DisplayName("💾 Retorna 3 Usuarios")
	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {
		
		List<Usuario> listaDeUsuarios =  usuarioRepository.findAllByNomeContainingIgnoreCase("Ronaldo");
		assertEquals(3, listaDeUsuarios.size());
	}
	
	@AfterAll
	public void end() {
		
		
		System.out.println("Teste Finalizado!");
		
		
	}
	
}
