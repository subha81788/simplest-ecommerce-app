package org.subhashis.simplestecommerceapp.services.impl;

import org.springframework.stereotype.Service;
import org.subhashis.simplestecommerceapp.documents.Order;
import org.subhashis.simplestecommerceapp.repositories.OrderReactiveRepository;
import org.subhashis.simplestecommerceapp.services.OrderService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderReactiveRepository orderReactiveRepository;

    public OrderServiceImpl(OrderReactiveRepository orderReactiveRepository) {
        this.orderReactiveRepository = orderReactiveRepository;
    }

    @Override
    public @NotNull Flux<Order> getAllOrders() {
        return this.orderReactiveRepository.findAll();
    }

    @Override
    public Mono<Order> create(@NotNull(message = "The order cannot be null.") @Valid Order order) {
        return this.orderReactiveRepository.save(order);
    }

    @Override
    public void update(@NotNull(message = "The order cannot be null.") @Valid Order order) {
        this.orderReactiveRepository.save(order);
    }
}
