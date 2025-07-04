package com.Pedido.Ecomarket.Pedido.Ecomarket.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.Pedido.Ecomarket.Pedido.Ecomarket.Controller.PedidoController;
import com.Pedido.Ecomarket.Pedido.Ecomarket.Modelo.Pedido;
import com.Pedido.Ecomarket.Pedido.Ecomarket.Service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@WebMvcTest(PedidoController.class) 
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockBean
    private PedidoService pedidoService; 

    @Autowired
    private ObjectMapper objectMapper; 

    private Pedido pedido;

    @BeforeEach
    void setUp() throws Exception {
        
        pedido = new Pedido();

        Time hora = Time.valueOf("10:30:00");
        Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse("2025-06-15");
        pedido.setFecha_pedido(fecha);
        pedido.setId(1);
        pedido.setHora_pedido(hora);
        pedido.setCostoPedido(BigDecimal.valueOf(150000));
        pedido.setComentario("Dejar en portería");
    }
    
    
    @Test
    public void testGetPedidos() throws Exception {
        when(pedidoService.findAll()).thenReturn(List.of(pedido));

        mockMvc.perform(get("/api/v1/pedido")) 
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fecha_pedido").value("2025-06-15"))
                .andExpect(jsonPath("$[0].hora_pedido").value("10:30:00"))
                .andExpect(jsonPath("$[0].costoPedido").value(15000))
                .andExpect(jsonPath("$[0].comentario").value("Dejar en portería"));
    }
    
    @Test
    public void testGetPedidoById() throws Exception {
        // Define el comportamiento del mock: cuando se llame a findById() con 1
        when(pedidoService.findById(1)).thenReturn(pedido);


        mockMvc.perform(get("/api/v1/pedido/1"))
                .andExpect(status().isOk()) // Verifica que el estado de la respuesta sea 200 OK
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fecha_pedido").value("2025-06-15"))
                .andExpect(jsonPath("$[0].hora_pedido").value("10:30:00"))
                .andExpect(jsonPath("$[0].costoPedido").value(15000))
                .andExpect(jsonPath("$[0].comentario").value("Dejar en portería"));
      }       
    @Test
    public void testCreatePedido() throws Exception {
      
        when(pedidoService.save(any(Pedido.class))).thenReturn(pedido);

        
        mockMvc.perform(post("/api/v1/pedido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedido))) 
                .andExpect(status().isOk()) // Verifica que el estado de la respuesta sea 200 OK
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fecha_pedido").value("2025-06-15"))
                .andExpect(jsonPath("$[0].hora_pedido").value("10:30:00"))
                .andExpect(jsonPath("$[0].costoPedido").value(15000))
                .andExpect(jsonPath("$[0].comentario").value("Dejar en portería"));

    }

    @Test
    public void testUpdatePedido() throws Exception {
        // Define el comportamiento del mock: cuando se llame a save(), devuelve el objeto 
        when(pedidoService.save(any(Pedido.class))).thenReturn(pedido);

        
        mockMvc.perform(put("/api/v1/pedido/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedido))) 
                .andExpect(status().isOk()) // Verifica que el estado de la respuesta sea 200 OK
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fecha_pedido").value("2025-06-15"))
                .andExpect(jsonPath("$[0].hora_pedido").value("10:30:00"))
                .andExpect(jsonPath("$[0].costoPedido").value(15000))
                .andExpect(jsonPath("$[0].comentario").value("Dejar en portería"));
    }        
                

    @Test
    public void testDeletePedido() throws Exception {
        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada
        doNothing().when(pedidoService).deleteById(1);

        
        mockMvc.perform(delete("/api/v1/pedido/1"))
                .andExpect(status().isOk()); // Verifica que el estado de la respuesta sea 200 OK

        // Verifica que el método deleteById() del servicio se haya llamado exactamente una vez con el id 1
        verify(pedidoService, times(1)).deleteById(1);
    }


    @Test
    public void testGetPedidoById_NotFound() throws Exception {
       when(pedidoService.findById(99)).thenReturn(null);

        mockMvc.perform(get("/api/v1/pedido/99"))
           .andExpect(status().isNotFound());
    }

}

   