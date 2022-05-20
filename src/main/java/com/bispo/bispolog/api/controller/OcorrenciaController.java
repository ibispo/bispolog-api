package com.bispo.bispolog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bispo.bispolog.api.assembler.OcorrenciaAssembler;
import com.bispo.bispolog.api.model.OcorrenciaDTO;
import com.bispo.bispolog.api.model.input.OcorrenciaInputDTO;
import com.bispo.bispolog.domain.model.Entrega;
import com.bispo.bispolog.domain.model.Ocorrencia;
import com.bispo.bispolog.domain.service.BuscaEntregaService;
import com.bispo.bispolog.domain.service.RegistroOcorrenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	private BuscaEntregaService buscaEntregaService;
	private RegistroOcorrenciaService registroOcorrenciaService;
	private OcorrenciaAssembler ocorrenciaAssembler;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public OcorrenciaDTO registrar(@PathVariable Long entregaId,
			@Valid @RequestBody OcorrenciaInputDTO ocorrInput) {
		
		Ocorrencia ocorrReg = 
				registroOcorrenciaService.registrar(entregaId, ocorrInput.getDescricao());
		
		return ocorrenciaAssembler.toModel(ocorrReg);
		
	}
	
	@GetMapping
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	public List<OcorrenciaDTO> listar(@PathVariable Long entregaId) {
		Entrega entr = buscaEntregaService.buscar(entregaId);
		List<OcorrenciaDTO> listOcorr = ocorrenciaAssembler.toCollectionModel( entr.getOcorrencias() );
		return listOcorr;
	}
	
	
	
}
