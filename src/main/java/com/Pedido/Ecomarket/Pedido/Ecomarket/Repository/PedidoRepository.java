package com.Pedido.Ecomarket.Pedido.Ecomarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pedido.Ecomarket.Pedido.Ecomarket.Modelo.Pedido;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido , Integer >{

}
