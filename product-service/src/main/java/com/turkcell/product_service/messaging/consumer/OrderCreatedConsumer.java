package com.turkcell.product_service.messaging.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class OrderCreatedConsumer {
    @Bean
    public Consumer<OrderCreatedEvent> orderCreated(){
        return event -> {
            System.out.println("Yeni bir order create edildi");
            System.out.println(event.productId);
        };
    }
    record OrderCreatedEvent(String productId) {
    }
}
