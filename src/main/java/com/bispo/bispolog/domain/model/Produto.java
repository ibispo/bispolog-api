package com.bispo.bispolog.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
@Entity
@Table(name="TB_PRODUTO")
public class Produto extends RepresentationModel<Produto> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	
	@GeneratedValue(strategy=GenerationType.IDENTITY) // GenerationType.AUTO --- Error below

	/*
	 * So to fix it, you had to change the id field generate strategy from GenerationType.AUTO to GenerationType.IDENTITY 
	 * and make sure the MySQL table’s primary key ( id ) column has the AI ( auto increment ) checkbox checked.
	 * 
	 * See 'application.properties'
	 * 
	 */

	private Long idProduto;
	private String nome;
	private BigDecimal valor;
	
}
