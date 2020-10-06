package org.subhashis.simplestecommerceapp.services;

import org.springframework.validation.annotation.Validated;
import org.subhashis.simplestecommerceapp.documents.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
public interface ProductService {

    @NotNull
    Flux<Product> getAllProducts();

    Mono<Product> getProduct(@NotBlank(message = "Invalid product ID.") String id);

    Mono<Product> save(@NotNull(message = "The product cannot be null.") @Valid Product product);
}