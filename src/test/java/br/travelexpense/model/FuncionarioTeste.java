package br.travelexpense.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class FuncionarioTeste {

    private Funcionario f;

	@BeforeEach
	void setUp() throws Exception {
		f  = new Funcionario();
	}

	@Test  
	void testGetViagens() {
		Viagem v = new Viagem();
		List <Viagem> listaDeViagens = new ArrayList<Viagem>();
		listaDeViagens.add(v);

		f.setViagens(listaDeViagens);
	    assertEquals(listaDeViagens,f.getViagens()); 
	}

    @Test  
	void testGetCpf() {
		f.setCpf("cpf");
		assertEquals("cpf",f.getCpf());
    }

    @Test
	void testGetNome() {
		f.setNome("nome");
		assertEquals("nome",f.getNome());
	}

    @Test
	void testGetCargo() {
		f.setCargo("cargo");
		assertEquals("cargo",f.getCargo());
	}

    @Test
	void testGetSetor() {
		f.setSetor("setor");
		assertEquals("setor",f.getSetor());
	}

    @Test
	void testGetSenha() {
		f.setSenha("senha");
		assertEquals("senha",f.getSenha());
	}

	@Test 
	void testGetEndereco() {
		Endereco e = new Endereco();
		e.setComplemento("apto 200");
		e.setId(1); 
		e.setMunicipio("alderan");
		e.setRua("rua x");
		e.setNumero("12");
		e.setUf("mi");
		f.setEndereco(e);
		assertEquals(e,f.getEndereco());
	}

    @Test
	void testGetTipo() {
		TipoFuncionario tipo;
		f.setTipo(TipoFuncionario.FUNCIONARIO);
		assertEquals(TipoFuncionario.FUNCIONARIO,f.getTipo());
	}

    

  
    
}
