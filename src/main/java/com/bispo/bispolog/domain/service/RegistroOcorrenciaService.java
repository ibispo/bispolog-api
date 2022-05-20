package com.bispo.bispolog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bispo.bispolog.domain.model.Entrega;
import com.bispo.bispolog.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public Ocorrencia registrar(Long idEntr, String desc) {
		
		Entrega entr = buscaEntregaService.buscar(idEntr);
		
		return entr.adicionarOcorrencia(desc);
		
	}
	
	
	
	
	
}
