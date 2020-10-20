package org.subhashis.simplestecommerceapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.subhashis.simplestecommerceapp.documents.Order;
import org.subhashis.simplestecommerceapp.documents.OrderProduct;
import org.subhashis.simplestecommerceapp.documents.OrderStatus;
import org.subhashis.simplestecommerceapp.dto.OrderProductDto;
import org.subhashis.simplestecommerceapp.exceptions.ProductNotFoundException;
import org.subhashis.simplestecommerceapp.services.OrderProductService;
import org.subhashis.simplestecommerceapp.services.OrderService;
import org.subhashis.simplestecommerceapp.services.ProductService;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;
    private final OrderProductService orderProductService = null;

    public OrderController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
        //this.orderProductService = orderProductService;
    }

    @GetMapping(value = { "", "/" })
    @ResponseStatus(HttpStatus.OK)
    public List<Order> list() {
        return this.orderService.getAllOrders();
    }

    @PostMapping(value = { "", "/" }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> create(@Valid @RequestBody OrderForm form) {
        List<OrderProductDto> formDtos = form.getProductOrders();
        validateProductsExistence(formDtos);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID);
        order.setDateCreated(LocalDateTime.now());

        order = this.orderService.create(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
            var product = productService.getProduct(dto.getProduct().getId())
                    .orElseThrow(() -> new ProductNotFoundException("Product with id " +
                            dto.getProduct().getId() + " is not found"));;
            orderProducts.add(new OrderProduct(product, dto.getQuantity()));
        }

        order.setOrderProducts(orderProducts);

        this.orderService.update(order);

         URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    private void validateProductsExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(productService.getProduct(op
                        .getProduct()
                        .getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            throw new ProductNotFoundException("Product not found");
        }
    }

    public static class OrderForm {

        private List<OrderProductDto> productOrders;

        public List<OrderProductDto> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<OrderProductDto> productOrders) {
            this.productOrders = productOrders;
        }
    }
}
