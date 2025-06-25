package com.Pedido.Ecomarket.Pedido.Ecomarket.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Pedido.Ecomarket.Pedido.Ecomarket.Modelo.Pedido;
import com.Pedido.Ecomarket.Pedido.Ecomarket.Repository.PedidoRepository;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido findById(Integer id) {
        return pedidoRepository.findById(id).get();
    }

    public List <Pedido> findAll(){
        return pedidoRepository.findAll();

    }

    public Pedido save (Pedido pedi){
        return pedidoRepository.save(pedi);

    
    }
    
    public void deleteById (Integer id){  
        pedidoRepository.deleteById(id);
    }
}
