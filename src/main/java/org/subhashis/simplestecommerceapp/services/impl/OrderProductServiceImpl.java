package org.subhashis.simplestecommerceapp.services.impl;

import org.subhashis.simplestecommerceapp.documents.OrderProduct;
import org.subhashis.simplestecommerceapp.services.OrderProductService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class OrderProductServiceImpl implements OrderProductService {

    @Override
    public OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct) {
        return new OrderProduct();
    }
}
