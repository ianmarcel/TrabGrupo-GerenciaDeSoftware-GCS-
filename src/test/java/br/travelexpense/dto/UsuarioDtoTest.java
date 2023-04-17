package br.travelexpense.dto;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioDtoTest {

    public UsuarioDto usuarioDtoTest;
    @BeforeEach
	void setUp() throws Exception {
        usuarioDtoTest = new UsuarioDto();
        usuarioDtoTest.setId(1l);
        usuarioDtoTest.setLogado(false);
        usuarioDtoTest.setMessage("Logado");
        usuarioDtoTest.setNome("Lucas");
	}

    @Test 
    void testCriarUsuarioNome(){
        assertEquals(usuarioDtoTest.getNome(), "Lucas"); 
    }

    @Test 
    void testCriarUsuarioLogado(){
        assertEquals(usuarioDtoTest.getMessage(), "Logado"); 
    }
}
