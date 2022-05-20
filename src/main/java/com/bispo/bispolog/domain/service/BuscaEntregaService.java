package com.bispo.bispolog.domain.service;

import org.springframework.stereotype.Service;

import com.bispo.bispolog.domain.exception.EntidadeNaoEncontradaException;
import com.bispo.bispolog.domain.model.Entrega;
import com.bispo.bispolog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;

	public Entrega buscar(Long id) {
		return entregaRepository.findById(id)
				.orElseThrow( () -> new EntidadeNaoEncontradaException("não encontreada"));
		
	}
	
	
}
