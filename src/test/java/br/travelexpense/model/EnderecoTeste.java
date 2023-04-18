package br.travelexpense.model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 class EnderecoTeste {

    private Endereco e;

	@BeforeEach
	void setUp() throws Exception {
		e  = new Endereco();
		
	}

    @Test
    void testGetRua(){
        e.setRua("rua");
        assertEquals("rua",e.getRua());
    }
    @Test
    void testGetNumero(){
        e.setNumero("numero");
        assertEquals("numero",e.getNumero());
    }
    @Test
    void testGetMunicipio(){
        e.setMunicipio("municipio");
        assertEquals("municipio",e.getMunicipio());
    }
    
    @Test
    void testGetUf(){
        e.setUf("mg");
        assertEquals("mg",e.getUf());
    }

    // TESTE COM UF N EXISTENTE
    @Test
    void testGetUfNexistente(){
        e.setUf("mj");
        assertEquals("mj",e.getUf());
    }

    
    @Test
    void testComplemento(){
        e.setComplemento("complemento");
        assertEquals("complemento",e.getComplemento());
    }
    
}
