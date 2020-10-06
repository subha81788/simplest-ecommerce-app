package org.subhashis.simplestecommerceapp.services.impl;

import org.springframework.stereotype.Service;
import org.subhashis.simplestecommerceapp.documents.OrderProduct;
import org.subhashis.simplestecommerceapp.repositories.OrderProductRepository;
import org.subhashis.simplestecommerceapp.services.OrderProductService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
}