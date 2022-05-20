package com.bispo.bispolog.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bispo.bispolog.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
// @Table(name="TB_CLIENTE")
public class Cliente {

	// @NotNull(groups = ValidationGroups.ClienteId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Email
	@Size(max = 244)
	private String email;
	
	@Column(name = "fone")
	@NotBlank
	@Size(max = 20)
	private String telefone;
	
	private LocalDate nascimento;
	
	
}
