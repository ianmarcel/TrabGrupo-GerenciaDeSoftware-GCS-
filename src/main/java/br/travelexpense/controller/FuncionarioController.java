package br.travelexpense.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import br.travelexpense.model.Funcionario;
import br.travelexpense.repository.FuncionarioRepository;


@RestController
@CrossOrigin
@RequestMapping("funcionario/")
public class FuncionarioController {


    private FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @PostMapping("add")
    public Funcionario add(@RequestBody Funcionario funcionario){
       return funcionarioRepository.save(funcionario);
    }

    @GetMapping("get/{id}")
    public Funcionario get(@PathVariable Long id){
        return funcionarioRepository.getReferenceById(id);
    }

    @GetMapping("/list")
    public List<Funcionario> list() {
        return funcionarioRepository.findAll();
    }

    @PostMapping("edit")
    public Funcionario edit(@RequestBody Funcionario funcionarioNew) {
        Funcionario funcionario = funcionarioRepository.findByCpf(funcionarioNew.getCpf()).orElse(null);
        if (funcionario == null) return null;

        funcionario.setNome(funcionarioNew.getNome());
        funcionario.setCpf(funcionarioNew.getCpf());
        funcionario.setSenha(funcionarioNew.getSenha());
        funcionario.setSetor(funcionarioNew.getSetor());
        funcionario.setCargo(funcionarioNew.getCargo());
        funcionario.setEndereco(funcionarioNew.getEndereco());

        return funcionarioRepository.save(funcionario);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);
    }


}
