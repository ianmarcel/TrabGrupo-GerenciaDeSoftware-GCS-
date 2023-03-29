package br.travelexpense.controller;


import br.travelexpense.dto.HeaderViagemDto;
import br.travelexpense.model.Despesa;
import br.travelexpense.model.Empresa;
import br.travelexpense.model.Viagem;
import br.travelexpense.repository.ViagemRepository;
import org.hibernate.Session;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("viagem/")
public class ViagemController {

    private ViagemRepository viagemRepository;

    private EntityManager entityManager;

    public ViagemController(ViagemRepository viagemRepository, EntityManager entityManager) {
        this.viagemRepository = viagemRepository;
        this.entityManager = entityManager;
    }

    @PostMapping("add")
    public Viagem add(@RequestBody Viagem viagem){
       return viagemRepository.save(viagem);
    }


    @GetMapping("get/{id}")
    public Viagem get(@PathVariable Long id){
        return viagemRepository.getReferenceById(id);
    }

    @GetMapping("/list")
    public List<Viagem> list(@RequestParam(required = false) String funcionarioId) {

        if(funcionarioId != null){
            entityManager.unwrap(Session.class)
                    .enableFilter("funcionario")
                    .setParameter("funcionarioId", Long.parseLong(funcionarioId));

            return viagemRepository.findAll().stream().filter(v -> !v.getFuncionarios().isEmpty()).toList();
        }

        return viagemRepository.findAll();
    }

    @GetMapping("/get-by-funcionario/{id}")
    public List<Viagem> list(@PathVariable Long id) {

        if(id != null){
            entityManager.unwrap(Session.class)
                    .enableFilter("funcionario")
                    .setParameter("funcionarioId", id);

            return viagemRepository.findAll().stream().filter(v -> !v.getFuncionarios().isEmpty()).toList();
        }

        return viagemRepository.findAll();
    }

    @PutMapping("update")
    public Viagem update(@RequestBody Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id) {
        viagemRepository.deleteById(id);
    }


}
