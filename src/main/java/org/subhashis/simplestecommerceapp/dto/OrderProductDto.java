package org.subhashis.simplestecommerceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.subhashis.simplestecommerceapp.documents.Product;

@Data
@AllArgsConstructor
public class OrderProductDto {
    private Product product;
    private Integer quantity;
}
