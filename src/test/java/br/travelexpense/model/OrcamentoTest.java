package br.travelexpense.model;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrcamentoTest {
    
    private Orcamento o;

    @BeforeEach
	void setUp() throws Exception {
		o  = new Orcamento();
		
	}

    @Test
    void testGetViagem(){
        Viagem v = new Viagem();
        o.setViagem(v);
        assertEquals(v, o.getViagem());
    }

    @Test
    void testGetValor(){
        o.setValor(12);
        assertEquals(12, o.getValor());
    }
    
    // @@ TESTE COM VALOR NEGATIVO
    @Test
    void testGetValorNegativo(){
        o.setValor(-12);
        assertEquals(-12, o.getValor());
    }

    @Test 
    void testGetDtComprovante(){
        java.sql.Date dtComprovante1 = new java.sql.Date(1);
        o.setData(dtComprovante1);
        assertEquals(dtComprovante1,o.getData());
        
    }

    @Test
    void testGetObservacao(){
        o.setObservacao("observacao");
        assertEquals("observacao", o.getObservacao());
    }

    @Test
	void testGetStatus() {
        Status status;
        o.setStatus(Status.APROVADO);
        assertEquals(Status.APROVADO,o.getStatus());
	}

    
}
