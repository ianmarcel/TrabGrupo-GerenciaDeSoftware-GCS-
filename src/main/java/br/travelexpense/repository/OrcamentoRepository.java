package br.travelexpense.repository;

import br.travelexpense.model.Cliente;
import br.travelexpense.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
}
