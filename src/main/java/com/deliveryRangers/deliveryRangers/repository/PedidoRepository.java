package com.deliveryRangers.deliveryRangers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.deliveryRangers.deliveryRangers.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
		
		public List <Pedido> findAllBydescricaoContainingIgnoreCase(@Param("descricao") String descricao);
	
}
