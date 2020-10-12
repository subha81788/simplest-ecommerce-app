package org.subhashis.simplestecommerceapp.controllers;

import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.subhashis.simplestecommerceapp.documents.Product;
import org.subhashis.simplestecommerceapp.exceptions.ProductNotFoundException;
import org.subhashis.simplestecommerceapp.services.ProductService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = { "", "/" })
    public List<Product> getProducts() {
        return this.productService.getAllProductsByOrderByPrice();
    }

    @GetMapping(value = { "/sortBy/{field}" })
    public List<Product> getAllProductsSortedByName(@NotBlank @PathVariable("field") String field) {
        Sort sort = null;
        if(field.equalsIgnoreCase("name")) {
            sort = Sort.by("name").ascending().and(Sort.by("price").descending());
        } else if(field.equalsIgnoreCase("price")) {
            sort = Sort.by("price").ascending();
        } else {
            throw new IllegalArgumentException("Invalid sorting specified. Supported only on name and price fields.");
        }
        return this.productService.getAllProductsSortedBy(sort);

    }

    @GetMapping(value = { "/range/{start}/{end}" })
    public List<Product> getAllProductsWithinPriceRange(@NotBlank @PathVariable String start, @NotBlank @PathVariable String end) {
        return this.productService.getAllProductsWithinPriceRange(new BigDecimal(start), new BigDecimal(end));
    }

    @GetMapping("/{id}")
    public Product getProduct(@NotBlank @PathVariable(name = "id") String id) {
        return this.productService.getProduct(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " is not found"));
    }

    @GetMapping("/price/{price}")
    public Product getProductByPrice(@NotBlank @PathVariable(name = "price") String price) {
        return this.productService.getProductByPrice(new BigDecimal(price))
                .orElseThrow(() -> new ProductNotFoundException("Product with price " + price + " is not found"));
    }
}
