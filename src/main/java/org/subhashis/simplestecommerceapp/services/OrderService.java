package org.subhashis.simplestecommerceapp.services;

import org.springframework.validation.annotation.Validated;
import org.subhashis.simplestecommerceapp.documents.Order;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface OrderService {

    @NotNull
    List<Order> getAllOrders();

    Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);
}