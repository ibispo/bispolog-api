package com.bispo.bispolog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.bispo.bispolog.api.model.EntregaDTO;
import com.bispo.bispolog.api.model.input.EntregaInputDTO;
import com.bispo.bispolog.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {

	private ModelMapper modelMapper; 
	
	public EntregaDTO toModel(Entrega entr) {
		return modelMapper.map(entr, EntregaDTO.class);
	}
	
	public List<EntregaDTO> toCollectionModel(List<Entrega> listEntr) {
		
		return listEntr.stream()
			.map(this::toModel)
			.collect(Collectors.toList());
		
	}
	
	
	public Entrega toEntity(EntregaInputDTO entr) {
		return modelMapper.map(entr, Entrega.class);
	}
	
	
	
	
	
}
