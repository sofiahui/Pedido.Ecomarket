package com.Pedido.Ecomarket.Pedido.Ecomarket.Modelo;


import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Pedido {

    @Id
    private Integer id;
    private Date fecha_pedido;
    private Time hora_pedido;
    private BigDecimal costoPedido;
    private String comentario;

}
