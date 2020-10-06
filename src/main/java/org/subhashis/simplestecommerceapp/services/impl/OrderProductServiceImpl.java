package org.subhashis.simplestecommerceapp.services.impl;

import org.springframework.stereotype.Service;
import org.subhashis.simplestecommerceapp.documents.OrderProduct;
import org.subhashis.simplestecommerceapp.repositories.OrderProductReactiveRepository;
import org.subhashis.simplestecommerceapp.services.OrderProductService;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private OrderProductReactiveRepository orderProductReactiveRepository;

    public OrderProductServiceImpl(OrderProductReactiveRepository orderProductRepository) {
        this.orderProductReactiveRepository = orderProductRepository;
    }

    @Override
    public Mono<OrderProduct> create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct) {
        return this.orderProductReactiveRepository.save(orderProduct);
    }
}