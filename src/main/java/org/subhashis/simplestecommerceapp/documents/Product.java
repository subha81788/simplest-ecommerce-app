package org.subhashis.simplestecommerceapp.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    @NotBlank(message = "Product name is required.")
    private String name;

    @NotNull(message = "Product price is required.")
    private Decimal128 price;

    private String imageUrl;

    public Product(String id, String name, Decimal128 price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
