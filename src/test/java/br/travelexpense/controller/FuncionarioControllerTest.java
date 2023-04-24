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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.travelexpense.model.Cliente;
import br.travelexpense.model.Endereco;
import br.travelexpense.model.Funcionario;
import br.travelexpense.model.Viagem;

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
        funcionario.setId(1l);
        funcionario.setEndereco(endereco);
        funcionario.setCargo("cargo novo");
        funcionario.setCpf("q1321123123132");
        funcionario.setNome("null");
        funcionario.setSenha("null");
        funcionario.setSetor("null");
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
        Random random = new Random();
        long numeroAleatorio = random.nextLong();
        Funcionario funcionario = new Funcionario();
        Endereco endereco = new Endereco();
        List<Viagem> listaViagem = new ArrayList<Viagem>();
        funcionario.setId(numeroAleatorio);
        funcionario.setEndereco(endereco);
        funcionario.setCargo("cargo novo");
        funcionario.setCpf("q13211231231336757");
        funcionario.setNome("null2221321");
        funcionario.setSenha("null221");
        funcionario.setSetor("null221");
        funcionario.setViagens(listaViagem);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(funcionario);
        this.mockMvc.perform(post("/funcionario/add")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJson))
        .andExpect(status().isOk());
        this.mockMvc.perform(get("/funcionario/get/"+37)
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ="))
        .andExpect(status().isOk());
    }


    @Test
    public void editarFuncionario() throws Exception{
        Random random = new Random();
        long numeroAleatorio = random.nextLong();
        Funcionario funcionario = new Funcionario();
        Endereco endereco = new Endereco();
        List<Viagem> listaViagem = new ArrayList<Viagem>();
        funcionario.setId(numeroAleatorio);
        funcionario.setEndereco(endereco);
        funcionario.setCargo("cargo novo");
        funcionario.setCpf("q132112312313367123");
        funcionario.setNome("null22213212");
        funcionario.setSenha("null2212");
        funcionario.setSetor("null2212");
        funcionario.setViagens(listaViagem);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(funcionario);
        this.mockMvc.perform(post("/funcionario/add")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJson))
        .andExpect(status().isOk());
        Funcionario funcionarioAux = new Funcionario();
        Endereco enderecoAux = new Endereco();
        List<Viagem> listaViagemAux = new ArrayList<Viagem>();
        funcionarioAux.setId((long)37);
        funcionarioAux.setEndereco(enderecoAux);
        funcionarioAux.setCargo("cargo novo");
        funcionarioAux.setCpf("q132112312313367123");
        funcionarioAux.setNome("null22213212");
        funcionarioAux.setSenha("null2212");
        funcionarioAux.setSetor("null2212");
        funcionarioAux.setViagens(listaViagemAux);
        ObjectMapper mapperAux = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter owAux = mapper.writer().withDefaultPrettyPrinter();
        String requestJsonAux=owAux.writeValueAsString(funcionario);
        this.mockMvc.perform(post("/funcionario/edit/")
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ=").contentType(APPLICATION_JSON_UTF8).content(requestJsonAux))
        .andExpect(status().isOk());
    }
    @Test
    public void deletarClientePorId() throws Exception{
        this.mockMvc.perform(delete("/funcionario/delete/"+37)
        .header("Authorization", "Basic dXNlcjplNDZhZjMyMS02NGY5LTQxZDItOTU3OC00NWQ0YmU0YzRmMGQ="))
        .andExpect(status().isOk());
    }
}
