package org.subhashis.simplestecommerceapp.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Validated
@Data
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id = UUID.randomUUID().toString();

    @Field(value = "orderCreatedAt")
    @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime dateCreated = LocalDateTime.now();

    @NotNull
    @Valid
    private OrderStatus status;

    @NotNull
    @Valid
    private List<OrderProduct> orderProducts;

    public Order(OrderStatus status, List<OrderProduct> orderProducts) {
        this.status = status;
        this.orderProducts = orderProducts;
    }

    public Decimal128 getTotalOrderPrice() {
        BigDecimal sum = new BigDecimal(0);
        for (OrderProduct op : orderProducts) {
            sum = sum.add(op.getTotalPrice().bigDecimalValue());
        }

        return new Decimal128(sum);
    }

}
