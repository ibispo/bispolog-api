package com.bispo.bispolog.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bispo.bispolog.domain.model.Cliente;
import com.bispo.bispolog.domain.model.Entrega;
import com.bispo.bispolog.domain.model.StatusEntrega;
import com.bispo.bispolog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private EntregaRepository entregaRepository;
	
	private CatalogoClienteService cliService;

	@Transactional
	public Entrega solicitar( Entrega entrega) {
		
		/// implementa todos os casos de uso
		/// implementa regras de negocio (ex. horario de entrega)
		/// valida se tem entregadores disponivel
		
		
		// se o cliente não exisate 
		Cliente cli =  cliService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cli);
		entrega.setStatusEntrega(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);
		
	}
	
	
}
