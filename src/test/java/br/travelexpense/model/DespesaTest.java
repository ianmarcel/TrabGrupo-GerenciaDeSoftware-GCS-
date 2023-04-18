package br.travelexpense.model;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DespesaTest {
    
    private Despesa d;

	@BeforeEach
	void setUp() throws Exception {
		d  = new Despesa();
		
	}


    @Test 
    void testGetDtComprovante(){
        java.sql.Date dtComprovante1 = new java.sql.Date(1);
        d.setDtComprovante(dtComprovante1);
        assertEquals(dtComprovante1,d.getDtComprovante());
        
    }
    
    @Test 
    void testGetTitulo(){
        d.setTitulo("titulo");
        assertEquals("titulo", d.getTitulo());
    }

    @Test
    void testGetValor(){
        d.setValor(12);
        assertEquals(12, d.getValor());
    }

    // Teste com VALOR NEGATIVO
    @Test
    void testGetValorNegativo(){
        d.setValor(-12);
        assertEquals(-12, d.getValor());
    }

    @Test
    void testGetViagens(){
        Viagem v = new Viagem();
        d.setViagem(v);
        assertEquals(v, d.getViagem());
    }
}
