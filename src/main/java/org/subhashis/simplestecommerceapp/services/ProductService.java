package org.subhashis.simplestecommerceapp.services;

import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.subhashis.simplestecommerceapp.documents.Product;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Validated
public interface ProductService {

    @NotNull
    public List<Product> getAllProducts();

    @NotNull
    public List<Product> getAllProductsSortedBy(Sort sort);

    @NotNull
    public List<Product> getAllProductsByOrderByPrice();

    public List<Product> getAllProductsWithinPriceRange(BigDecimal start, BigDecimal end);

    public Optional<Product> getProduct(@NotBlank(message = "Invalid product ID.") String id);

    public Optional<Product> getProductByPrice(BigDecimal price);

    public Optional<Product> getProductByNameAndImageUrl(@NotBlank(message = "Invalid product name.") String name,
                                                  @NotBlank(message = "Invalid product image url.") String imageUrl);

    Product save(@NotNull(message = "The product cannot be null.") @Valid Product product);
}