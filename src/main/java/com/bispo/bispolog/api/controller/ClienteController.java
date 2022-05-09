package com.bispo.bispolog.api.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bispo.bispolog.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {

		
		Cliente cli1 = new Cliente(1L, "Israel Bispo", "bispo@bispo.com", "11-9952-0000", LocalDate.of(1974, 4, 18));
		Cliente cli2 = new Cliente(2L, "Celia Bispo", "celia@celia.com", "11-8850-0000", LocalDate.of(1973, 2, 11));
		
		return Arrays.asList(cli1,cli2);
		
	}
	
}
