package com.bispo.bispolog.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bispo.bispolog.domain.model.Cliente;
import com.bispo.bispolog.repository.ClienteRespository;

@Service
public class ClienteService {

	private static final int CLIENT_NOT_FOUND = -1;
	
	@Autowired
	ClienteRespository clienteRespository;
	
	// Feito
	public int buscarIdadePorNome(String nm) {
		
		Optional<Cliente> clienteOptional = clienteRespository.findByName(nm); 
		
		return clienteOptional.isPresent() ? Period.between(clienteOptional.get().getNascimento(), 
			LocalDate.now()).getYears() : ClienteService.CLIENT_NOT_FOUND;
	}

	
}
