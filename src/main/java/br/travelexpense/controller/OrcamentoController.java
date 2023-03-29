package br.travelexpense.controller;

import br.travelexpense.model.Cliente;
import br.travelexpense.model.Orcamento;
import br.travelexpense.model.Viagem;
import br.travelexpense.repository.ClienteRepository;
import br.travelexpense.repository.OrcamentoRepository;
import br.travelexpense.repository.ViagemRepository;

import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("orcamento/")
public class OrcamentoController {

    private OrcamentoRepository orcamentoRepository;
    private ViagemRepository viagemRepository;

    public OrcamentoController(OrcamentoRepository orcamentoRepository, ViagemRepository viagemRepository) {
		this.viagemRepository = viagemRepository;
		this.orcamentoRepository = orcamentoRepository;
    }

    @PostMapping("add")
    public Orcamento add(@RequestBody Orcamento orcamento){
    	Viagem v = viagemRepository.findById(orcamento.getViagem().getId()).get();
    	orcamento.setViagem(v);
    	v.setOrcamento(orcamento);
       return orcamentoRepository.save(orcamento);
    }

    @GetMapping("get/{id}")
    public Orcamento get(@PathVariable Long id){
        return orcamentoRepository.getReferenceById(id);
    }

    @GetMapping("/list")
    public List<Orcamento> list() {
        return orcamentoRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id) {
        orcamentoRepository.deleteById(id);
    }


}
