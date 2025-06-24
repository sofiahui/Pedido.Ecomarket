package com.Pedido.Ecomarket.Pedido.Ecomarket.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pedido.Ecomarket.Pedido.Ecomarket.Assemblers.PedidoModelAssemblers;
import com.Pedido.Ecomarket.Pedido.Ecomarket.Modelo.Pedido;
import com.Pedido.Ecomarket.Pedido.Ecomarket.Service.PedidoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v2/pedidos")
@Tag(name="Pedidos",description="Operaciones relacionadas con los pedidos")
public class PedidoControllerV2  {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoModelAssemblers assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Pedido>> getAllPedidos() {
        List<EntityModel<Pedido>> pedidos = pedidoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(pedidos,linkTo(methodOn(PedidoControllerV2.class).getAllPedidos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Pedido> getPedidoById(@PathVariable Integer id) {
        Pedido pedido = pedidoService.findById(id);
        return assembler.toModel(pedido);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pedido>> createEnvio(@RequestBody Pedido pedido) {
        Pedido newePedido = pedidoService.save(pedido);
        return ResponseEntity
                .created(linkTo(methodOn(PedidoControllerV2.class).getPedidoById(newePedido.getId())).toUri())
                .body(assembler.toModel(newePedido));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pedido>> updatePedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        Pedido updatedPedido = pedidoService.save(pedido);
        return ResponseEntity
                .ok(assembler.toModel(updatedPedido));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deletePedido(@PathVariable Integer id) {
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
