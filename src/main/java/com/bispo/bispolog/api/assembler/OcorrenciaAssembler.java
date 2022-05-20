package com.bispo.bispolog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.bispo.bispolog.api.model.OcorrenciaDTO;
import com.bispo.bispolog.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

	private ModelMapper modelMapper;
	
	public OcorrenciaDTO toModel(Ocorrencia ocorr)	{
		return modelMapper.map(ocorr, OcorrenciaDTO.class);
	}
	
	public List<OcorrenciaDTO> toCollectionModel(List<Ocorrencia> listOcorr) {
		return listOcorr.stream()
			.map(this::toModel)
			.collect(Collectors.toList());
	}
	
	
}
