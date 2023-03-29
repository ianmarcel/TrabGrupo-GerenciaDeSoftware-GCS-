package br.travelexpense.repository;

import br.travelexpense.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	boolean existsByCnpj(String cnpj);

	@Query(nativeQuery = true, 
			value = "SELECT e.id" + 
					" FROM empresa e , empresa_funcionarios ef , funcionario f " + 
					"WHERE e.id = ef.empresa_id " + 
					"AND f.id = ef.funcionarios_id " + 
					"AND f.cpf = :cpf")
	Long getIdByFuncionario(@Param("cpf") String cpf);
}
