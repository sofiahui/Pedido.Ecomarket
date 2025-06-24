package com.Pedido.Ecomarket.Pedido.Ecomarket.Modelo;


import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="pedido")

@Data
@NoArgsConstructor
@AllArgsConstructor


public class PedidoModelo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date fecha_pedido;
    private Time hora_pedido;
    private BigDecimal costoPedido;
    private String comentario;

}
