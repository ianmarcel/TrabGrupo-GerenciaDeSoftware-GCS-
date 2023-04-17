package br.travelexpense.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.travelexpense.model.Cliente;
import br.travelexpense.model.Despesa;
import br.travelexpense.model.Orcamento;
import br.travelexpense.model.Viagem;

class HeaderViagemDtoTest {

	private Viagem viagem;
    private Cliente cliente;
    private Orcamento orcamento;
    private Despesa despesa;
    private List<Despesa> listaDespesas;

	@BeforeEach
	void setUp() throws Exception {
        viagem = new Viagem();
        cliente = new Cliente();
        cliente.setNome("Lucas");
        orcamento = new Orcamento();
        despesa = new Despesa();
        listaDespesas = new ArrayList<Despesa>();
        listaDespesas.add(despesa);
        viagem.setCliente(cliente);
        viagem.setDespesas(listaDespesas);
        viagem.setDtFim(null);
        viagem.setDtInicio(null);
        viagem.setFuncionarios(null);
        viagem.setId(1l);
        viagem.setOrcamento(orcamento);
	}

    @Test 
    void testCriarHeaderDtoCliente(){
        HeaderViagemDto headerViagemDtoTeste = new HeaderViagemDto(viagem);
        assertEquals(headerViagemDtoTeste.getCliente(), "Lucas"); 
    }

    @Test 
    void testCriarHeaderDtoOrcamento(){
        HeaderViagemDto headerViagemDtoTeste = new HeaderViagemDto(viagem);
        assertEquals(headerViagemDtoTeste.getOrcamento(), 0.0);
    }

    @Test 
    void testCriarHeaderDtoDataInicio(){
        HeaderViagemDto headerViagemDtoTeste = new HeaderViagemDto(viagem);
        assertEquals(headerViagemDtoTeste.getDataInicio(), null);
    }

    @Test 
    void testCriarHeaderDtoDataFinal(){
        HeaderViagemDto headerViagemDtoTeste = new HeaderViagemDto(viagem);
        assertEquals(headerViagemDtoTeste.getDataFinal(), null);
    }

}