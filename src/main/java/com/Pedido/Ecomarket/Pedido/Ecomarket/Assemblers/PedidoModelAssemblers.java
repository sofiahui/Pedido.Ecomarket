package com.Pedido.Ecomarket.Pedido.Ecomarket.Assemblers;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.Pedido.Ecomarket.Pedido.Ecomarket.Controller.PedidoControllerV2;
import com.Pedido.Ecomarket.Pedido.Ecomarket.Modelo.Pedido;



@Component
public class PedidoModelAssemblers implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    @NonNull
    public EntityModel<Pedido> toModel(Pedido pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoControllerV2.class).getPedidoById(pedido.getId())).withSelfRel(),
                linkTo(methodOn(PedidoControllerV2.class).getAllPedidos()).withRel("pedido"));
    }
}