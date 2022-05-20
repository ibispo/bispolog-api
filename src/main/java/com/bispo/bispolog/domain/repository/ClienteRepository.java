package com.bispo.bispolog.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bispo.bispolog.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<List<Cliente>> findByNomeContaining(String nm);
	Optional<Cliente> findByNome(String nm);
	Optional<Cliente> findByEmail(String email);
	
}
