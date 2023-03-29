package br.travelexpense.repository;

import br.travelexpense.model.Empresa;
import br.travelexpense.model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {
}
