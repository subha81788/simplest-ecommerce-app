package org.subhashis.simplestecommerceapp.services;

import org.springframework.validation.annotation.Validated;
import org.subhashis.simplestecommerceapp.documents.Product;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Validated
public interface ProductService {

    @NotNull
    List<Product> getAllProducts();

    Optional<Product> getProduct(@NotBlank(message = "Invalid product ID.") String id);

    Product save(@NotNull(message = "The product cannot be null.") @Valid Product product);
}