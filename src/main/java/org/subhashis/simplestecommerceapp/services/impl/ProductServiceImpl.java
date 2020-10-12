package org.subhashis.simplestecommerceapp.services.impl;

import org.bson.types.Decimal128;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.subhashis.simplestecommerceapp.documents.Product;
import org.subhashis.simplestecommerceapp.repositories.ProductRepository;
import org.subhashis.simplestecommerceapp.services.ProductService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Validated
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public @NotNull List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public @NotNull List<Product> getAllProductsSortedBy(Sort sort) {
        return this.productRepository.findAll(sort);
    }

    @Override
    public List<Product> getAllProductsByOrderByPrice() {
        return this.productRepository.findAllByOrderByPrice();
    }

    @Override
    public List<Product> getAllProductsWithinPriceRange(BigDecimal start, BigDecimal end) {
        var productsByPriceRange = this.productRepository.findByPriceRange(new Decimal128(start), new Decimal128(end));

        // sort the products in ascending order of their price
        Comparator<Product> priceComparator = (Product p1, Product p2) -> {
            return p1.getPrice().bigDecimalValue().compareTo(p2.getPrice().bigDecimalValue());
        };
        productsByPriceRange.sort(priceComparator);
        return productsByPriceRange;
    }

    @Override
    public Optional<Product> getProduct(@NotBlank(message = "Invalid product ID.") String id) {
        return this.productRepository.findById(id);
    }

    public Optional<Product> getProductByPrice(BigDecimal price) {
        return this.productRepository.findByPrice(new Decimal128(price));
    }

    @Override
    public Optional<Product> getProductByNameAndImageUrl(@NotBlank(message = "Invalid product name.") String name,
                                                         @NotBlank(message = "Invalid product image url.") String imageUrl) {
        return this.productRepository.findByNameAndImageUrl(name, imageUrl);
    }

    @Override
    public Product save(@NotNull(message = "The product cannot be null.") @Valid Product product) {
        return this.productRepository.save(product);
    }
}
