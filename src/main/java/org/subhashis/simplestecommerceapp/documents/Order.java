package org.subhashis.simplestecommerceapp.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
@Data
@NoArgsConstructor
public class Order {
    @Id
    private String id = "_id" + UUID.randomUUID().toString();

    @JsonFormat(pattern = "dd/MM/yyyy")
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
    public BigDecimal getTotalOrderPrice() {
        BigDecimal sum = new BigDecimal(0);
        List<OrderProduct> orderProducts = getOrderProducts();
        for (OrderProduct op : orderProducts) {
            sum = sum.add(op.getTotalPrice());
        }

        return sum;
    }

}
