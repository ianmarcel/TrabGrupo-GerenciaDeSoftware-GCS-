package br.travelexpense.controller;
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
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.travelexpense.model.Cliente;
import br.travelexpense.model.Endereco;
import br.travelexpense.model.Viagem;
import br.travelexpense.utils.RandomNameGenerator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void clienteList() throws Exception{
        this.mockMvc.perform(get("/cliente/list")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ="))
        .andExpect(status().isOk());
    }

    @Test
    public void adicionarCliente() throws Exception{
        Endereco endereco = new Endereco();
        Cliente clientDto = new Cliente();
        List<Viagem> listaViagems = new ArrayList<Viagem>();
        RandomNameGenerator randon = new RandomNameGenerator();
        clientDto.setNome(randon.generateRandomName());
        clientDto.setCnpj(randon.generateRandomNumber());
        clientDto.setEndereco(endereco);
        clientDto.setViagens(listaViagems);
        clientDto.setId(100002);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(clientDto);
        this.mockMvc.perform(post("/cliente/add")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJson))
        .andExpect(status().isOk());
    }

    @Test
    public void pegarClientePorId() throws Exception{
        Endereco endereco = new Endereco();
        Cliente clientDto = new Cliente();
        List<Viagem> listaViagems = new ArrayList<Viagem>();
        RandomNameGenerator randon = new RandomNameGenerator();
        clientDto.setNome(randon.generateRandomName());
        clientDto.setCnpj(randon.generateRandomNumber());
        clientDto.setEndereco(endereco);
        clientDto.setViagens(listaViagems);
        clientDto.setId(1l);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(clientDto);
        MvcResult result = this.mockMvc.perform(post("/cliente/add")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJson))
        .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        String clienteId = jsonResponse.get("id").asText();
        this.mockMvc.perform(get("/cliente/get/"+clienteId
        )
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ="))
        .andExpect(status().isOk());
    }

    @Test
    public void deletarCliente() throws Exception{
        Endereco endereco = new Endereco();
        Cliente clientDto = new Cliente();
        List<Viagem> listaViagems = new ArrayList<Viagem>();
        RandomNameGenerator randon = new RandomNameGenerator();
        clientDto.setNome(randon.generateRandomName());
        clientDto.setCnpj(randon.generateRandomNumber());
        clientDto.setEndereco(endereco);
        clientDto.setViagens(listaViagems);
        clientDto.setId(1l);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(clientDto);
        MvcResult result = this.mockMvc.perform(post("/cliente/add")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJson))
        .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        String clienteId = jsonResponse.get("id").asText();
        this.mockMvc.perform(delete("/cliente/delete/"+clienteId
        )
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ="))
        .andExpect(status().isOk());
    }
}
