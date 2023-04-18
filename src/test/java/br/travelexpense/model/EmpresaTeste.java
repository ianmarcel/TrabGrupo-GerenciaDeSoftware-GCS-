
package br.travelexpense.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmpresaTeste {

	private Empresa empresa;

	@BeforeEach
	void setUp() throws Exception {
		empresa  = new Empresa();
		
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
		empresa.setEndereco(e);
		assertEquals(e,empresa.getEndereco());
	}

	@Test 
	void testGetId() {
		empresa.setId(1);
		assertEquals(1,empresa.getId());
	}

	//TESTE COM ID NEGATIVO 
	@Test 
	void testGetIdNegativo() {
		empresa.setId(-1);
		assertEquals(-1,empresa.getId());
	}

	@Test  
	void testGetCnpj() {
		empresa.setCnpj("cnpj");
		assertEquals("cnpj",empresa.getCnpj());
		
	}

	@Test
	void testGetNome() {
		empresa.setNome("nome");
		assertEquals("nome",empresa.getNome());
	}
   
	@Test  
     void testGetFuncionarios() {
     Funcionario f = new Funcionario();
     List <Funcionario> listaDeFuncionarios = new ArrayList<Funcionario>();
     listaDeFuncionarios.add(f);

     empresa.setFuncionarios(listaDeFuncionarios);
	 assertEquals(listaDeFuncionarios,empresa.getFuncionarios());
	}

   
	
}
