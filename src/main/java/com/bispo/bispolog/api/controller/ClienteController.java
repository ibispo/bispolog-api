package com.bispo.bispolog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bispo.bispolog.domain.model.Cliente;
import com.bispo.bispolog.domain.repository.ClienteRepository;
import com.bispo.bispolog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@GetMapping("/ola")
	public String helloWorld() {
		return "Olá mundão";
	}
	
	// Opcao 2
	//public ClienteController(ClienteRepository clienteRespository) {
	//	super();
	//	this.clienteRespository = clienteRespository;
	//}

//	@PersistenceContext
//	private EntityManager manager;
	
	// @Autowired  ----- tirou por causa do lombok @AllArgsConstructor
	private ClienteRepository clienteRepository;
	private CatalogoClienteService cliService;
	
	@GetMapping
	public List<Cliente> listar() {
		//Optional<List<Cliente>> listaCli = clienteRespository.findByNomeContaining("gg");
		//return listaCli.isPresent() ? listaCli.get() : null;
		//return manager.createQuery("from Cliente", Cliente.class).getResultList();
		return clienteRepository.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> obter(@PathVariable(name = "clienteId") Long id) {
//		Optional<Cliente> cli = clienteRespository.findById(id);
//		return cli.isPresent() ? 
//				ResponseEntity.ok(cli.get()) : 
//					ResponseEntity.notFound().build();  // cli.orElse(null);
		return clienteRepository.findById(id)
				// .map(cli -> ResponseEntity.ok(cli))   // Lambda
				.map(ResponseEntity::ok)                // Reference Method
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cli) {
		return cliService.salvar(cli);
		// return cliService.salvar(cli);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cli) {
		// Optional<Cliente> cli = clienteRepository.findById(id);
		if ( !clienteRepository.existsById(id)  ) {
			return ResponseEntity.notFound().build();
		} 
		cli.setId(id);
		//clienteRepository.save(cli);
		cliService.salvar(cli);
		return ResponseEntity.ok(cli);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		if ( !clienteRepository.existsById(id)  ) {
			return ResponseEntity.notFound().build();
		} 
		// clienteRepository.deleteById(id);
		cliService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
}
