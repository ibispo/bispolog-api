package com.bispo.bispolog.domain.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Cliente {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private LocalDate nascimento;
	
	public Cliente(Long id, String nome, String email, String telefone, LocalDate nascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.nascimento = nascimento;
	}
	
}
