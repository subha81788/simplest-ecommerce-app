package org.subhashis.simplestecommerceapp.services.impl;

import org.springframework.stereotype.Service;
import org.subhashis.simplestecommerceapp.documents.Product;
import org.subhashis.simplestecommerceapp.exceptions.ProductNotFoundException;
import org.subhashis.simplestecommerceapp.repositories.ProductReactiveRepository;
import org.subhashis.simplestecommerceapp.services.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductReactiveRepository productReactiveRepository;

    public ProductServiceImpl(ProductReactiveRepository productReactiveRepository) {
        this.productReactiveRepository = productReactiveRepository;
    }

    @Override
    public @NotNull Flux<Product> getAllProducts() {
        return this.productReactiveRepository.findAll();
    }

    @Override
    public Mono<Product> getProduct(@NotBlank(message = "Invalid product ID.") String id) {
        return this.productReactiveRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product with id " + id + " not found")));
    }

    @Override
    public Mono<Product> save(@NotNull(message = "The product cannot be null.") @Valid Product product) {
        return this.productReactiveRepository.save(product);
    }
}
