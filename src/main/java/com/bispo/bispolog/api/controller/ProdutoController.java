package com.bispo.bispolog.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bispo.bispolog.domain.model.Produto;

@RestController
public class ProdutoController {

	@GetMapping("/produtos")
	public List<Produto> getListaProdutos() {
		
		 
		return null;
		
	}
	
	@GetMapping("/produto")
	public Optional<Produto> getProduto(Long id) {
		
		return null;
		
	}
	
	
	
	
}
