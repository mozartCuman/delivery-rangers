package com.deliveryRangers.deliveryRangers.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_pedido")
public class Pedido {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = " A DESCRIÇÃO DO PRODUTO é obrigatorio!!")
	@Size(min = 3, max = 30, message = "O nome deve ter de 3 letras até no máximo 30...")
	private String descricao;
	
	@NotBlank(message = " O PREÇO é obrigatório!!!")
	@Size(min = 1, max = 6)
	private float preco;
	
	@UpdateTimestamp
	private LocalDateTime data;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@JsonIgnoreProperties("pedido")
	private Cliente cliente;
	


	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}

