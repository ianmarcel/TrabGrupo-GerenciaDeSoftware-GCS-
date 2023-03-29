package br.travelexpense.repository;

import br.travelexpense.model.Cliente;
import br.travelexpense.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
