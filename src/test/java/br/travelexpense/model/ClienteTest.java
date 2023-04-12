package br.travelexpense.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteTest {

	private Cliente c;

	@BeforeEach
	void setUp() throws Exception {
		c  = new Cliente();
		
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
		c.setEndereco(e);
		assertEquals(e,c.getEndereco());
	}


	 
	@Test  
	void testGetViagens() {
		Viagem v = new Viagem();
		List <Viagem> listaDeViagens = new ArrayList<Viagem>();
		listaDeViagens.add(v);
		v.setCliente(this.c); 
		c.setViagens(listaDeViagens);
	    assertEquals(listaDeViagens,c.getViagens()); 
	}

	@Test 
	void testGetId() {
		c.setId(1);
		assertEquals(1,c.getId());
	}

	@Test  
	void testGetCnpj() {
		c.setCnpj("cnpj");
		assertEquals("cnpj",c.getCnpj());
		
	}

	@Test
	void testGetNome() {
		c.setNome("nome");
		assertEquals("nome",c.getNome());
	}

}
