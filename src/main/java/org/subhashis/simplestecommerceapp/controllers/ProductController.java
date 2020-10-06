package org.subhashis.simplestecommerceapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.subhashis.simplestecommerceapp.documents.Product;
import org.subhashis.simplestecommerceapp.exceptions.ProductNotFoundException;
import org.subhashis.simplestecommerceapp.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = { "", "/" })
    public List<Product> getProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable(name = "id") String id) {
        return this.productService.getProduct(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " is not found"));
    }

}
