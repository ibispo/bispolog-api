package com.bispo.bispolog.domain.service;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bispo.bispolog.domain.exception.NegocioException;
import com.bispo.bispolog.domain.model.Cliente;
import com.bispo.bispolog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

	// @Autowired -- para não colocar, usar @AllArgsConstructor na assinatura da classe
	private ClienteRepository clienteRepository;
	private MessageSource mensagemSource;
	
	/**
	 * Método que busca cliente pelo ID
	 * @param cliId (Long) ID do cliente
	 * @return (Cliente) objeto cliente ou exception de Negocio
	 */
	public Cliente buscar(Long cliId) {
		
		Optional<Cliente> buscaCli = clienteRepository.findById(cliId);
		
		return buscaCli
				.orElseThrow( () -> new NegocioException(mensagemSource.getMessage("cliente.inexistente", null, 
						LocaleContextHolder.getLocale())) );		
	}
	
	@Transactional
	public Cliente salvar(Cliente cli) {
		
		
		boolean emailExiste = 
			clienteRepository.findByEmail(cli.getEmail()).stream()
				.anyMatch(cliExiste -> !cliExiste.equals(cli));
		
		
		if ( emailExiste ) {
			throw new NegocioException(	mensagemSource.getMessage("email.existente", null, 
				LocaleContextHolder.getLocale()) );
		}
		
		return clienteRepository.save(cli);
	}
	
	@Transactional
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}
	
	
}
