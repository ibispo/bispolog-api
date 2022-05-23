package com.bispo.bispolog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bispo.bispolog.domain.exception.NegocioException;
import com.bispo.bispolog.domain.model.Entrega;
import com.bispo.bispolog.domain.model.StatusEntrega;
import com.bispo.bispolog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

	EntregaRepository entregaRepository;
	BuscaEntregaService buscaEntregaService;
	
	
	
	
	
	@Transactional
	public void finalizar(Long id) {
		
		Entrega entr = buscaEntregaService.buscar(id);
		
	
		entr.finalizar();
		
		entregaRepository.save(entr);
		
		
		
	}
	
}
