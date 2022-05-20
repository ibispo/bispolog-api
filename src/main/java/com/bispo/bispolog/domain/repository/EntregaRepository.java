package com.bispo.bispolog.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bispo.bispolog.domain.model.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long>{

}
