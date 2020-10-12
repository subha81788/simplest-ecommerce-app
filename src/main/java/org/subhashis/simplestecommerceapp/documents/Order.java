package org.subhashis.simplestecommerceapp.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Validated
@Data
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id = "_id" + UUID.randomUUID().toString();

    @Field(value = "orderCreatedAt")
    @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime dateCreated = LocalDateTime.now();

    @NotNull
    private OrderStatus status;

    @Valid
    @NotNull
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public Order(OrderStatus status, List<OrderProduct> orderProducts) {
        this.status = status;
        this.orderProducts = orderProducts;
    }

    @Transient
    public Decimal128 getTotalOrderPrice() {
        BigDecimal sum = new BigDecimal(0);
        List<OrderProduct> orderProducts = getOrderProducts();
        for (OrderProduct op : orderProducts) {
            sum = sum.add(op.getTotalPrice().bigDecimalValue());
        }

        return new Decimal128(sum);
    }

}
