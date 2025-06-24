package com.Pedido.Ecomarket.Pedido.Ecomarket.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Pedido.Ecomarket.Pedido.Ecomarket.Modelo.Pedido;
import com.Pedido.Ecomarket.Pedido.Ecomarket.Repository.PedidoRepository;
import com.Pedido.Ecomarket.Pedido.Ecomarket.Service.PedidoService;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PedidoServiceTest {

    
    @Autowired
    private PedidoService pedidoService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @MockBean
    private PedidoRepository pedidoRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(pedidoRepository.findAll()).thenReturn(List.of(new Pedido ()));

        // Llama al método findAll() del servicio.
        List<Pedido> pedidos = pedidoService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
    }

    @Test
    public void testFindPedidoById() {
        Integer id = 1;
        Pedido pedido = new Pedido();


        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));

        // Llama al método findByCodigo() del servicio.
        Pedido found = pedidoService.findById(id);

        pedido.setId(id);
        pedido.setFecha_pedido(new Date());
        pedido.setHora_pedido(Time.valueOf("12:00:00"));
        pedido.setCostoPedido(new BigDecimal("5000"));
        pedido.setComentario("Entregar en portería");

        assertNotNull(found);
        assertEquals(id, found.getId());
        assertEquals(new BigDecimal("5000"), found.getCostoPedido());
        assertEquals("Entregar en portería", found.getComentario());
        assertNotNull(found.getFecha_pedido());
        assertNotNull(found.getHora_pedido());
    }

    @Test
     public void testSavePedido() {
        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setFecha_pedido(new Date());
        pedido.setHora_pedido(Time.valueOf("14:30:00"));
        pedido.setCostoPedido(new BigDecimal("7500"));
        pedido.setComentario("Entregar a Juan en recepción");

        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido saved = pedidoService.save(pedido);

        assertNotNull(saved);
        assertEquals(1, saved.getId());
        assertEquals(new BigDecimal("7500"), saved.getCostoPedido());
        assertEquals("Entregar a Juan en recepción", saved.getComentario());
        assertNotNull(saved.getFecha_pedido());
        assertNotNull(saved.getHora_pedido());
    }


    @Test
    public void testDeleteById() {
        Integer id = 1;

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(pedidoRepository).deleteById(id);

        // Llama al método deleteByCodigo() del servicio.
        pedidoService.deleteById(id);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el id proporcionado.
        verify(pedidoRepository, times(1)).deleteById(id);
    }
}
