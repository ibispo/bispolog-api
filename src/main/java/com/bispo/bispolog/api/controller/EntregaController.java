package com.bispo.bispolog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bispo.bispolog.api.assembler.EntregaAssembler;
import com.bispo.bispolog.api.model.EntregaDTO;
import com.bispo.bispolog.api.model.input.EntregaInputDTO;
import com.bispo.bispolog.domain.model.Entrega;
import com.bispo.bispolog.domain.repository.EntregaRepository;
import com.bispo.bispolog.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaAssembler entregaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO solicitarEntrega(@Valid  @RequestBody EntregaInputDTO entr) {
		Entrega novaEntr = entregaAssembler.toEntity(entr);
		Entrega entrSolicit = solicitacaoEntregaService.solicitar(novaEntr);
		return entregaAssembler.toModel(entrSolicit);
		
	}
	
	@GetMapping
	public List<EntregaDTO> listar() {
		return  entregaAssembler.toCollectionModel(
			entregaRepository.findAll());
	}
	
	/*
	 * Entrega é o modelo de representação do recurso
	 *             Resource Representation Model
	 *             
	 * Classe que modelará nossa representação
	 */
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				
				/*
			.map(entr -> {
				
				EntregaDTO entregaDTO = new EntregaDTO();
				
				// BeanUtils.copyProperties(entr, entregaDTO);
				
				entregaDTO.setId(entr.getId());
				entregaDTO.setNomeCliente(entr.getCliente().getNome());
				entregaDTO.setDestinatario(new DestinatarioDTO());
				entregaDTO.getDestinatario().setNome(entr.getDestinatario().getNome());
				entregaDTO.getDestinatario().setLogradouro(entr.getDestinatario().getLogradouro());
				entregaDTO.getDestinatario().setNumero(entr.getDestinatario().getNumero());
				entregaDTO.getDestinatario().setComplemento(entr.getDestinatario().getComplemento());
				entregaDTO.getDestinatario().setBairro(entr.getDestinatario().getBairro());
				entregaDTO.setTaxa(entr.getTaxa());
				entregaDTO.setStatus(entr.getStatusEntrega());
				entregaDTO.setDataPedido(entr.getDataPedido());
				entregaDTO.setDataFinalizacao(entr.getDataFinalizacao());
				
				
				EntregaDTO entregaDTO = modelMapper.map(entr, EntregaDTO.class);
				
				
				return ResponseEntity.ok(entregaDTO);
			})
				 */
				
				
			.map(entr -> ResponseEntity.ok(entregaAssembler.toModel(entr)))
			.orElse(ResponseEntity.notFound().build());
	}
	
}
