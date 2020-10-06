package org.subhashis.simplestecommerceapp.services.impl;

import org.springframework.stereotype.Service;
import org.subhashis.simplestecommerceapp.documents.Product;
import org.subhashis.simplestecommerceapp.repositories.ProductRepository;
import org.subhashis.simplestecommerceapp.services.ProductService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public @NotNull List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(@NotBlank(message = "Invalid product ID.") String id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Product save(@NotNull(message = "The product cannot be null.") @Valid Product product) {
        return this.productRepository.save(product);
    }
}
