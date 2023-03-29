package br.travelexpense.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.sql.Date;
import java.util.Base64;

@Entity
@FilterDef(name="viagem", parameters=@ParamDef( name="viagemId", type="long" ) )
@Filter(name="viagem", condition="viagem_id = :viagemId")
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    Date dtComprovante;

    double valor;

    String titulo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "viagem_id")
    @JsonIgnoreProperties("despesas")
    Viagem viagem;

    @Column(name = "imagem_comprovante")
    private byte[] imagemComprovante;

    public String getImagemComprovante() {

        if(this.imagemComprovante == null)
            return null;

        Base64.Encoder enc = Base64.getEncoder();
        return enc.encodeToString(this.imagemComprovante);
    }

    public void setImagemComprovante(byte[] imagemComprovante) {
        this.imagemComprovante = imagemComprovante;
    }

    public Date getDtComprovante() {
        return dtComprovante;
    }

    public void setDtComprovante(Date dtComprovante) {
        this.dtComprovante = dtComprovante;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }


}
