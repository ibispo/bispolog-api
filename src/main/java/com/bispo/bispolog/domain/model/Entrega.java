package com.bispo.bispolog.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.bispo.bispolog.domain.exception.NegocioException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity


/**
 * Modelo de domínio
 * @author negol
 *
 */
public class Entrega {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	 * Com a separação do DomainModel e RepresentationalModel (API), não 
	 * nas classes DTO (Input) já estão sendo implementadas as validações
	 * Não sendo necessárias validações na classe de entidade.
	 * 
	 * O controller recebe o objeto DTO Input, onde nele já está sendo
	 * aplicada a validação
	 */
	
	
	// @Valid
	// @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	// @NotNull
	@ManyToOne
	private Cliente cliente;
	
	// @NotNull
	// @Valid
	@Embedded   /// Destinatario ficará dentro da tabela Entrega e não em outra tabela
	private Destinatario destinatario;
	
	// @NotNull
	private BigDecimal taxa;
	
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();

	// @JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusEntrega statusEntrega;
	
	// @JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataPedido;
	
	// @JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;

	public Ocorrencia adicionarOcorrencia(String desc) {

		Ocorrencia ocorr = new Ocorrencia();
		
		ocorr.setDescricao(desc);
		ocorr.setDataRegistro(OffsetDateTime.now());
		ocorr.setEntrega(this);
		
		this.ocorrencias.add(ocorr);
		
		return ocorr;
		
	}

	public void finalizar() {

		if ( naoPodeSerFinalizada() ) {
			throw new NegocioException("Entrega não pode ser finalizada.");
		}
		
		this.statusEntrega = StatusEntrega.FINALIZADA;
		this.dataFinalizacao = OffsetDateTime.now();
		
	}
	
	public boolean podeSerFinalizada() {
		return StatusEntrega.PENDENTE.equals(this.statusEntrega);
	}
	
	public boolean naoPodeSerFinalizada() {
		return !podeSerFinalizada();
	}
	
}
