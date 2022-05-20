package com.bispo.bispolog.domain.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bispo.bispolog.domain.model.Cliente;
import com.bispo.bispolog.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	private static final int CLIENT_NOT_FOUND = -1;
	
	@Autowired
	ClienteRepository clienteRespository;
	
	public int buscarIdadePorNome(String nm) {
		
		Optional<Cliente> clienteOptional = clienteRespository.findByNome(nm); 
		
		return clienteOptional.isPresent() ? Period.between(clienteOptional.get().getNascimento(), 
			LocalDate.now()).getYears() : ClienteService.CLIENT_NOT_FOUND;
	}

	
}
