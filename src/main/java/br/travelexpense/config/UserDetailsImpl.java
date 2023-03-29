package br.travelexpense.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.travelexpense.model.Funcionario;
import br.travelexpense.model.TipoFuncionario;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private final Optional<Funcionario> funcionariOptional;

	public UserDetailsImpl(Optional<Funcionario> funcionariOptional) {
		this.funcionariOptional = funcionariOptional;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<TipoFuncionario> roles = new ArrayList<>();
		roles.add(funcionariOptional.orElse(new Funcionario()).getTipo());
		
		return roles;
	}

	@Override
	public String getPassword() {
		return funcionariOptional.orElse(new Funcionario()).getSenha();
	}

	@Override
	public String getUsername() {
		return funcionariOptional.orElse(new Funcionario()).getCpf();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
