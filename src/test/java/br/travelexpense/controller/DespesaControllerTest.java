package br.travelexpense.controller;
import java.nio.charset.Charset;
import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.travelexpense.model.Cliente;
import br.travelexpense.model.Despesa;
import br.travelexpense.model.Endereco;
import br.travelexpense.model.Viagem;
import br.travelexpense.utils.RandomNameGenerator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class DespesaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void listaDeDespesas() throws Exception{
        this.mockMvc.perform(get("/despesa/list")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ="))
        .andExpect(status().isOk());
    }
    
    //adicionar
    @Test
    public void adicionarDespesa() throws Exception{
        Despesa despesa = new Despesa();
        Viagem viagem = new Viagem();
        Date dataAux = new Date(2131123321);
        byte[] aux = new byte[10];
        RandomNameGenerator random = new RandomNameGenerator();
        despesa.setId(1l);
        despesa.setTitulo(random.generateRandomName());
        despesa.setValor((Double)random.generateRandomDouble());
        despesa.setViagem(viagem);
        despesa.setDtComprovante(dataAux);
        despesa.setImagemComprovante(aux);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(despesa);
        this.mockMvc.perform(post("/despesa/add/")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJson))
        .andExpect(status().isOk());
    }

    @Test
    public void verificarDespesaPorId() throws Exception{
        Despesa despesa = new Despesa();
        Viagem viagem = new Viagem();
        Date dataAux = new Date(2131123321);
        byte[] aux = new byte[10];
        RandomNameGenerator random = new RandomNameGenerator();
        despesa.setId(1l);
        despesa.setTitulo(random.generateRandomName());
        despesa.setValor((Double) random.generateRandomDouble());
        despesa.setViagem(viagem);
        despesa.setDtComprovante(dataAux);
        despesa.setImagemComprovante(aux);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(despesa);
        MvcResult result = this.mockMvc.perform(post("/despesa/add/")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJson))
        .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        String despesaId = jsonResponse.get("id").asText();
        this.mockMvc.perform(get("/despesa/get/"+despesaId)
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ="))
        .andExpect(status().isOk());
    }

    @Test
    public void verificarDeleteDespesa() throws Exception{
        Despesa despesa = new Despesa();
        Viagem viagem = new Viagem();
        Date dataAux = new Date(2131123321);
        byte[] aux = new byte[10];
        RandomNameGenerator random = new RandomNameGenerator();
        despesa.setId(1l);
        despesa.setTitulo(random.generateRandomName());
        despesa.setValor((Double) random.generateRandomDouble());
        despesa.setViagem(viagem);
        despesa.setDtComprovante(dataAux);
        despesa.setImagemComprovante(aux);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(despesa);
        MvcResult result = this.mockMvc.perform(post("/despesa/add/")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJson))
        .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        String despesaId = jsonResponse.get("id").asText();
        this.mockMvc.perform(delete("/despesa/delete/"+despesaId)
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ="))
        .andExpect(status().isOk());
    }
}
