package com.deliveryRangers.deliveryRangers.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cliente;
	
	@NotBlank(message = " O NOME é obrigatorio!!")
	@Size(min = 3, max = 30, message = "O nome deve ter de 3 letras até no máximo 30...")
	private String nome;
	
	@NotBlank(message = " O ENDEREÇO é obrigatorio!!")
	@Size(min = 6, max = 255)
	private String endereco;
	
	@NotBlank(message = " O NÚMERO DE TELEFONE é obrigatorio!!")
	@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$")
	private String telefone;
	
	@Email(message = "Email inválido!!!")
	@Size(min = 10, max = 60, message = " O atributo titulo deve ter no minimo 05 e no maximo 100 caracteres")
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente",cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("cliente")
	private List<Pedido>pedido;

	public Long getId() {
		return id_cliente;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
