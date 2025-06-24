package com.Pedido.Ecomarket.Pedido.Ecomarket;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.Pedido.Ecomarket.Pedido.Ecomarket.Modelo.Pedido;
import com.Pedido.Ecomarket.Pedido.Ecomarket.Repository.PedidoRepository;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.Date;


@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PedidoRepository pedidoRepository;
    

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        


        

        // Generar pedidos
        for (int i = 0; i < 50; i++) {
            Pedido pedido = new Pedido();

            pedido.setId(i + 1);
            // Fecha del pedido como Date
            pedido.setFecha_pedido(faker.date().past(30, TimeUnit.DAYS));

            // Hora del pedido como java.sql.Time
            Date randomDate = faker.date().future(15, TimeUnit.DAYS, new Date());
            pedido.setHora_pedido(new java.sql.Time(randomDate.getTime()));

            // Costo del pedido como BigDecimal
            pedido.setCostoPedido(BigDecimal.valueOf(faker.number().numberBetween(5000, 100000)));
            

            // Comentario como String
            pedido.setComentario(faker.options().option("Dejar en porteria", "Llamar al llegar", "Entregar solo al titular"));

            pedidoRepository.save(pedido);
        }

       
       
    }
}