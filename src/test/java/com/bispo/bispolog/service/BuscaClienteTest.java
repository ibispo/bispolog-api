package com.bispo.bispolog.service;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bispo.bispolog.domain.model.Cliente;
import com.bispo.bispolog.domain.repository.ClienteRepository;
import com.bispo.bispolog.domain.service.ClienteService;

@RunWith(SpringRunner.class)
public class BuscaClienteTest {

	@TestConfiguration
	static class ClienteServiceTestConfiguration {
		
		@Bean
		public ClienteService clienteService() {
			return new ClienteService();
		}
		
	}
	
	
	@Autowired
	ClienteService clienteService;

	@MockBean
	ClienteRepository clienteRepository;
	
	
	@Test
	public void buscarDataNascimento() {
		
		String nm = "Bispo Teste";
		
		int idade = clienteService.buscarIdadePorNome(nm);
		
		Assertions.assertEquals(idade, 48);
		
	}
	
	
	@Before
	public void setup() {
		
		Cliente cliente = new Cliente(); // 1L, "Bispo Teste", "bispo.teste@bispo.com", "11234564", LocalDate.parse("1974-04-18"));
		
		Mockito.when(clienteRepository.findByNome(cliente.getNome()))
			.thenReturn(Optional.of(cliente));
		
		
	}
	
}
