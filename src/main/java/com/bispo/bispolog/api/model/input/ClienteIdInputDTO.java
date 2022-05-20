package com.bispo.bispolog.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteIdInputDTO {

	@NotNull
	private Long id;
	
}
