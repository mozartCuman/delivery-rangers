package com.deliveryRangers.deliveryRangers.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deliveryRangers.deliveryRangers.model.Cliente;
import com.deliveryRangers.deliveryRangers.repository.ClienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {
	
	 @Autowired
	    private ClienteRepository clienteRepository;


@PostMapping
public ResponseEntity<Cliente> post(@Valid @RequestBody Cliente cliente){
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(clienteRepository.save(cliente));
}

@PutMapping
public ResponseEntity<Cliente> put(@Valid @RequestBody Cliente cliente){
    return clienteRepository.findById(cliente.getId())
        .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
        .body(clienteRepository.save(cliente)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}

@ResponseStatus(HttpStatus.NO_CONTENT)
@DeleteMapping("/{id}")
public void delete(@PathVariable Long id) {
    Optional<Cliente> cliente = clienteRepository.findById(id);
    
    if(cliente.isEmpty())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    
    clienteRepository.deleteById(id);              
}
}
