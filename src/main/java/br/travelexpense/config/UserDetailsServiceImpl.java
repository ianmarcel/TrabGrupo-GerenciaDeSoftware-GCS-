package br.travelexpense.config;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.travelexpense.model.Funcionario;
import br.travelexpense.repository.FuncionarioRepository;

@Repository
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final FuncionarioRepository repository;

	public UserDetailsServiceImpl(FuncionarioRepository repository) {
		this.repository = repository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	Optional<Funcionario> funcionario = repository.findByCpf(username);
	
	if (funcionario.isEmpty()) {
		throw new UsernameNotFoundException(username);
	}
		
		return new UserDetailsImpl(funcionario);
	}

}
