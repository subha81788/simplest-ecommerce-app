package org.subhashis.simplestecommerceapp.services;

import org.springframework.validation.annotation.Validated;
import org.subhashis.simplestecommerceapp.documents.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderService {

    @NotNull
    Flux<Order> getAllOrders();

    Mono<Order> create(@NotNull(message = "The order cannot be null.") @Valid Order order);

    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);
}