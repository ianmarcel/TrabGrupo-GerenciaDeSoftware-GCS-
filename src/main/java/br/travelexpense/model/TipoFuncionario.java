package br.travelexpense.model;

import org.springframework.security.core.GrantedAuthority;

public enum TipoFuncionario implements GrantedAuthority {

	GESTOR("ROLE_GESTOR"), FUNCIONARIO("ROLE_FUNCIONARIO"), DONO("ROLE_DONO");

	private final String role;

	TipoFuncionario(String i) {
		this.role = i;
	}

	@Override
	public String getAuthority() {
		return role;
	}
}
