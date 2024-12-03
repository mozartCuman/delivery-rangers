package com.deliveryRangers.deliveryRangers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.deliveryRangers.deliveryRangers.model.Pedido;
import com.deliveryRangers.deliveryRangers.repository.PedidoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

	@Autowired 
	private PedidoRepository pedidoRepository;
	
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
	    return ResponseEntity.status(HttpStatus.CREATED)
	    .body(pedidoRepository.save(pedido));
	    }
	
}









