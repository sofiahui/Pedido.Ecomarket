package com.Pedido.Ecomarket.Pedido.Ecomarket.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Pedido.Ecomarket.Pedido.Ecomarket.Modelo.Pedido;
import com.Pedido.Ecomarket.Pedido.Ecomarket.Service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("api/v1/pedidos")
@Tag(name = "Pedidos", description = "Operaciones relacionadas con los pedidos")

public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Obtener todos los pedidos", description = "Obtiene una lista de todos los pedidos")
    public List<Pedido> getAllPedidos() {
        return pedidoService.findAll();
    }
    
     @GetMapping("/{id}")
    @Operation(summary = "Obtener un pedido por ID", description = "Retorna un pedido según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })

    public Pedido getPedidoById(@PathVariable Integer id) {
        return pedidoService.findById(id);
    }


    @PostMapping
    @Operation(summary = "Crear un nuevo pedido", description = "Registra un nuevo pedido en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
          })


    
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoService.save(pedido);
    }

    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pedido",description = "Actualiza un pedido existente")
    @ApiResponses(value = {
         @ApiResponse (responseCode = "200", description = "pedido actualizado exitosamente",
                 content = @Content(mediaType = "application/json",
                 schema = @Schema (implementation = Pedido.class))),
                 @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    
    
    public Pedido updatePedido(@PathVariable Integer id, @RequestBody Pedido pedido){
        pedido.setId(id);
        return pedidoService.save(pedido);
    }



    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar un pedido", description = "Eliminar un pedido por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Pedido eliminado exitosamente" ),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado ")
    })
   
    public void deletePedido(@PathVariable Integer id ){
        pedidoService.deleteById(id);
     }

} 