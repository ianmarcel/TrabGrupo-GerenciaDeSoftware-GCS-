package br.travelexpense.controller;

import br.travelexpense.model.Cliente;
import br.travelexpense.model.Funcionario;
import br.travelexpense.repository.ClienteRepository;
import br.travelexpense.repository.FuncionarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("cliente/")
public class ClienteController {

    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("add")
    public Cliente add(@RequestBody Cliente cliente){
       return clienteRepository.save(cliente);
    }

    @GetMapping("get/{id}")
    public Cliente get(@PathVariable Long id){
        return clienteRepository.getReferenceById(id);
    }

    @GetMapping("/list")
    public List<Cliente> list() {
        return clienteRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }


}
