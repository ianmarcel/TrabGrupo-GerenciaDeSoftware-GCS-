package br.travelexpense.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.travelexpense.model.Empresa;
import br.travelexpense.model.Funcionario;
import br.travelexpense.repository.EmpresaRepository;
import br.travelexpense.utils.CookieHelper;

@RestController
@CrossOrigin
@RequestMapping("empresa/")
public class EmpresaController {

	
	private final EmpresaRepository eRepository;
	private final PasswordEncoder encoder;
	

	public EmpresaController(EmpresaRepository eRepository, PasswordEncoder encoder) {
		this.eRepository = eRepository;
		this.encoder = encoder;
	}

	@GetMapping("get/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		Optional<Empresa> empresa = eRepository.findById(id);
		ResponseEntity<Empresa> resp = null;

		if (empresa.isPresent()) {
			resp = new ResponseEntity<>(empresa.get(), HttpStatus.OK);
		} else {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return resp;
	}

	@DeleteMapping("/delete/{id}")
	void delete(@PathVariable Long id) {
		eRepository.deleteById(id);
	}

	@GetMapping("/exists")
	public ResponseEntity<Boolean> existe(@RequestParam String cnpj) {
		boolean exists = eRepository.existsByCnpj(cnpj);
		return new ResponseEntity<>(exists, HttpStatus.OK);
	}
	
	@GetMapping("list/funcionarios")
	public ResponseEntity<?> funcionarios(HttpServletRequest req){
		
		Long idEmpresa = CookieHelper.getIdValue(req.getCookies());
		
		Empresa empresa = eRepository.findById(idEmpresa).get();
		
		return new ResponseEntity<>(empresa.getFuncionarios(), HttpStatus.OK);
	}
	
	@PostMapping("/add/funcionario")
	public ResponseEntity<?> addFuncionario(@RequestBody Funcionario novo, HttpServletRequest req) {

		Long idEmpresa = CookieHelper.getIdValue(req.getCookies());

		Empresa empresa = eRepository.findById(idEmpresa).get();
		empresa.addFuncionario(novo);

		String senha = novo.getSenha();
		novo.setSenha(encoder.encode(senha));

		eRepository.saveAndFlush(empresa);

		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}


}
