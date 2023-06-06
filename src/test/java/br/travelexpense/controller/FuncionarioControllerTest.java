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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.travelexpense.model.Cliente;
import br.travelexpense.model.Endereco;
import br.travelexpense.model.Funcionario;
import br.travelexpense.model.Viagem;
import br.travelexpense.utils.RandomNameGenerator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class FuncionarioControllerTest {
    

    @Autowired
    private MockMvc mockMvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void funcionarioList() throws Exception{
        this.mockMvc.perform(get("/funcionario/list")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ="))
        .andExpect(status().isOk());
    }

    @Test
    public void adicionarFuncionario() throws Exception{
        Funcionario funcionario = new Funcionario();
        Endereco endereco = new Endereco();
        List<Viagem> listaViagem = new ArrayList<Viagem>();
        RandomNameGenerator random = new RandomNameGenerator();
        funcionario.setId(1l);
        funcionario.setEndereco(endereco);
        funcionario.setCargo(random.generateRandomName());
        funcionario.setCpf(random.generateRandomNumber());
        funcionario.setNome(random.generateRandomName());
        funcionario.setSenha(random.generateRandomNumber());
        funcionario.setSetor(random.generateRandomName());
        funcionario.setViagens(listaViagem);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(funcionario);
        this.mockMvc.perform(post("/funcionario/add")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJson))
        .andExpect(status().isOk());
    }

    @Test
    public void pegarFuncionarioPorId() throws Exception{
        Funcionario funcionario = new Funcionario();
        Endereco endereco = new Endereco();
        List<Viagem> listaViagem = new ArrayList<Viagem>();
        RandomNameGenerator random = new RandomNameGenerator();
        funcionario.setId(1l);
        funcionario.setEndereco(endereco);
        funcionario.setCargo(random.generateRandomName());
        funcionario.setCpf(random.generateRandomNumber());
        funcionario.setNome(random.generateRandomName());
        funcionario.setSenha(random.generateRandomName());
        funcionario.setSetor(random.generateRandomName());
        funcionario.setViagens(listaViagem);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(funcionario);
        MvcResult result = this.mockMvc.perform(post("/funcionario/add")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJson))
        .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        String funcionarioId = jsonResponse.get("id").asText();
        this.mockMvc.perform(get("/funcionario/get/"+funcionarioId)
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ="))
        .andExpect(status().isOk());
    }


    @Test
    public void editarFuncionario() throws Exception{
        Funcionario funcionario = new Funcionario();
        Endereco endereco = new Endereco();
        List<Viagem> listaViagem = new ArrayList<Viagem>();
        RandomNameGenerator random = new RandomNameGenerator();
        funcionario.setId(1l);
        funcionario.setEndereco(endereco);
        funcionario.setCargo(random.generateRandomName());
        funcionario.setCpf(random.generateRandomNumber());
        funcionario.setNome(random.generateRandomName());
        funcionario.setSenha(random.generateRandomName());
        funcionario.setSetor(random.generateRandomName());
        funcionario.setViagens(listaViagem);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(funcionario);
        MvcResult result = this.mockMvc.perform(post("/funcionario/add")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJson))
        .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        Long funcionarioId = Long.parseLong(jsonResponse.get("id").asText());
        Funcionario funcionarioAux = new Funcionario();
        Endereco enderecoAux = new Endereco();
        List<Viagem> listaViagemAux = new ArrayList<Viagem>();
        funcionarioAux.setId(funcionarioId);
        funcionarioAux.setEndereco(enderecoAux);
        funcionario.setCargo(random.generateRandomName());
        funcionario.setCpf(random.generateRandomNumber());
        funcionario.setNome(random.generateRandomName());
        funcionario.setSenha(random.generateRandomName());
        funcionario.setSetor(random.generateRandomName());
        funcionarioAux.setViagens(listaViagemAux);
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter owAux = mapper.writer().withDefaultPrettyPrinter();
        String requestJsonAux=owAux.writeValueAsString(funcionario);
        this.mockMvc.perform(post("/funcionario/edit/")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJsonAux))
        .andExpect(status().isOk());
    }
    @Test
    public void deletarClientePorId() throws Exception{
        Funcionario funcionario = new Funcionario();
        Endereco endereco = new Endereco();
        List<Viagem> listaViagem = new ArrayList<Viagem>();
        RandomNameGenerator random = new RandomNameGenerator();
        funcionario.setId(1l);
        funcionario.setEndereco(endereco);
        funcionario.setCargo(random.generateRandomName());
        funcionario.setCpf(random.generateRandomNumber());
        funcionario.setNome(random.generateRandomName());
        funcionario.setSenha(random.generateRandomName());
        funcionario.setSetor(random.generateRandomName());
        funcionario.setViagens(listaViagem);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(funcionario);
        MvcResult result = this.mockMvc.perform(post("/funcionario/add")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJson))
        .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        String funcionarioId = jsonResponse.get("id").asText();
        this.mockMvc.perform(delete("/funcionario/delete/"+funcionarioId)
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ="))
        .andExpect(status().isOk());
    }
}
