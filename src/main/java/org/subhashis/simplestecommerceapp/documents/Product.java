package org.subhashis.simplestecommerceapp.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Product {

    @Id
    private String id;

    @NotNull(message = "Product name is required.")
    private String name;

    @NotNull(message = "Product price is required.")
    private BigDecimal price;

    private String imageUrl;

    public Product(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
