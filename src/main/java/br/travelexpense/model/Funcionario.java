package br.travelexpense.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@FilterDef(name = "funcionario", parameters = @ParamDef(name = "funcionarioId", type = "long"))
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    private String nome;
    private String cargo;
    private String setor;

    @Enumerated(EnumType.STRING)
    private TipoFuncionario tipo;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @ManyToMany(mappedBy = "funcionarios")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "funcionarios" })
    List<Viagem> viagens;

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String password) {
        this.senha = password;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoFuncionario getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoFuncionario tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
