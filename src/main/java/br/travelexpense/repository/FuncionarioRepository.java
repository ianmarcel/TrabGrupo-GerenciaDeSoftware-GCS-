package br.travelexpense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.travelexpense.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Optional<Funcionario> findByCpf(String cpf);

}
