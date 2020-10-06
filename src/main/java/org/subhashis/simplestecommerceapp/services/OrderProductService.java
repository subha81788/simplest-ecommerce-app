package org.subhashis.simplestecommerceapp.services;

import org.springframework.validation.annotation.Validated;
import org.subhashis.simplestecommerceapp.documents.OrderProduct;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderProductService {

    OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct);
}
