package org.subhashis.simplestecommerceapp.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Decimal128;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {

    @NotNull
    @Valid
    @DBRef
    private Product product;

    @Min(1)
    private Integer quantity;

    public Product getProduct() {
        return getProduct();
    }

    public Decimal128 getTotalPrice() {
        return new Decimal128(getProduct().getPrice().bigDecimalValue().multiply(BigDecimal.valueOf(getQuantity())));
    }
}
