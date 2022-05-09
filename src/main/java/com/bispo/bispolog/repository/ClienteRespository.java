package com.bispo.bispolog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bispo.bispolog.domain.model.Cliente;

import java.util.Optional;

public interface ClienteRespository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByName(String nm);
	
}
