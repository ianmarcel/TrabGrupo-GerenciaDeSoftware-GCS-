package br.travelexpense.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViagemTest {

	private Viagem v;

	@BeforeEach
	void setUp() throws Exception {
		v  = new Viagem();
		
	}

    @Test
    void testGetCliente(){
        Cliente c = new Cliente();
        v.setCliente(c);
        assertEquals(c, v.getCliente());
    }

     @Test  
     void testGetFuncionarios() {
     Funcionario f = new Funcionario();
     List <Funcionario> listaDeFuncionarios = new ArrayList<Funcionario>();
     listaDeFuncionarios.add(f);
     v.setFuncionarios(listaDeFuncionarios);
	 assertEquals(listaDeFuncionarios,v.getFuncionarios());
	}
     

     @Test
     void testGetOrçamento(){
        Orcamento o = new Orcamento();
        v.setOrcamento(o);
        assertEquals(o, v.getOrcamento());
    }

    @Test
     void testGetAnotacoes(){
        v.setAnotacoes("anotacao");
        assertEquals("anotacao", v.getAnotacoes());
    }

    @Test
     void testGetDespesas(){
        List <Despesa> listaDeDespesas = new ArrayList<Despesa>();
        v.setDespesas(listaDeDespesas);
        assertEquals(listaDeDespesas, v.getDespesas());
    }
     
     @Test 
     void testGetDtInicio(){
        java.sql.Date dtComprovante1 = new java.sql.Date(1);
        v.setDtInicio(dtComprovante1);
        assertEquals(dtComprovante1,v.getDtInicio());
     }

     @Test 
     void testGetDtFim(){
        java.sql.Date dtComprovante1 = new java.sql.Date(1);
        v.setDtFim(dtComprovante1);
        assertEquals(dtComprovante1,v.getDtFim());
     }



}
