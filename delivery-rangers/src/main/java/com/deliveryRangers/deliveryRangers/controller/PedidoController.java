package com.deliveryRangers.deliveryRangers.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deliveryRangers.deliveryRangers.model.Pedido;
import com.deliveryRangers.deliveryRangers.repository.ClienteRepository;
import com.deliveryRangers.deliveryRangers.repository.PedidoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

	@Autowired 
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> getAll() {
		return ResponseEntity.ok(pedidoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> getById(@PathVariable Long id) {
		return pedidoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Pedido>> getByTitle(@PathVariable String descricao) {
		return ResponseEntity.ok(pedidoRepository.findAllBydescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping 
	public ResponseEntity<Pedido> post(@Valid @RequestBody Pedido pedido){
		if (clienteRepository.existsById(pedido.getCliente().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRepository.save(pedido));
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente!!!", null);
	    }
	
	@PutMapping
	public ResponseEntity<Pedido> put(@Valid @RequestBody Pedido pedido) {
		if (pedidoRepository.existsById(pedido.getId())) {
			if (clienteRepository.existsById(pedido.getCliente().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(pedidoRepository.save(pedido));

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Pedido> postagem = pedidoRepository.findById(id);

		if (postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		pedidoRepository.deleteById(id);
	}
}









