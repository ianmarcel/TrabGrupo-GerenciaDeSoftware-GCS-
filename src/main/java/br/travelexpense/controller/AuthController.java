package br.travelexpense.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.travelexpense.dto.UsuarioDto;
import br.travelexpense.model.Empresa;
import br.travelexpense.model.Funcionario;
import br.travelexpense.repository.EmpresaRepository;
import br.travelexpense.repository.FuncionarioRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authManager;
	private final EmpresaRepository eRepository;
	private final PasswordEncoder encoder;
	private final FuncionarioRepository fRepository;

	public AuthController(PasswordEncoder encoder, EmpresaRepository eRepository, AuthenticationManager authManager,
			FuncionarioRepository fRepository) {
		this.authManager = authManager;
		this.eRepository = eRepository;
		this.encoder = encoder;
		this.fRepository = fRepository;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, Object> body, HttpServletResponse res) {

		String cpf = (String) body.getOrDefault("login", "");
		String senha = (String) body.getOrDefault("senha", "");

		UsuarioDto user = new UsuarioDto();
		try {
			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(cpf, senha);
			Authentication auth = authManager.authenticate(authReq);
			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(auth);

			Funcionario func = fRepository.findByCpf(cpf).get();
			Long idEmpresa = eRepository.getIdByFuncionario(cpf);

			Cookie empresaId = new Cookie("EMPRESA_ID", idEmpresa.toString());
			empresaId.setPath("/");
			empresaId.setHttpOnly(true);
			res.addCookie(empresaId);

			user.setNome(func.getNome());
			user.setId(func.getId());
			user.setLogado(true);
			user.setMessage("Logado com sucesso");
		}		
		catch (AuthenticationException ex) {
			user.setMessage(ex.getMessage());
			user.setLogado(false);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/empresa")
	public ResponseEntity<?> newEmpresa(@RequestBody Empresa empresa) {
		Funcionario func = empresa.getDono();
		String senha = func.getSenha();
		func.setSenha(encoder.encode(senha));
		Empresa emp = eRepository.saveAndFlush(empresa);
		return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
	}

}
