package org.subhashis.simplestecommerceapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.Decimal128;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.subhashis.simplestecommerceapp.documents.Order;
import org.subhashis.simplestecommerceapp.documents.OrderProduct;
import org.subhashis.simplestecommerceapp.documents.OrderStatus;
import org.subhashis.simplestecommerceapp.documents.Product;
import org.subhashis.simplestecommerceapp.repositories.OrderRepository;
import org.subhashis.simplestecommerceapp.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class DataInitializerBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    public DataInitializerBootstrap(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProductData();
        loadOrdersData();
    }

    private void loadProductData() {
        productRepository.deleteAll();

        var tvSet = new Product("10001", "TV Set", new Decimal128(new BigDecimal("300.00")), "http://placehold.it/200x100");
        var gameConsole = new Product("10002", "Game Console", new Decimal128(new BigDecimal("200.00")), "http://placehold.it/200x100");
        var sofa = new Product("10003", "Sofa", new Decimal128(new BigDecimal("100.00")), "http://placehold.it/200x100");
        var beer = new Product("10004", "Beer", new Decimal128(new BigDecimal("5.00")), "http://placehold.it/200x100");
        var iceCream = new Product("10005", "Ice Cream", new Decimal128(new BigDecimal("3.00")), "http://placehold.it/200x100");
        var phone = new Product("10006", "Phone", new Decimal128(new BigDecimal("500.00")), "http://placehold.it/200x100");
        var watch = new Product("10007", "Watch", new Decimal128(new BigDecimal("30.00")), "http://placehold.it/200x100");

        productRepository.saveAll(List.of(tvSet,gameConsole,sofa,beer,iceCream,phone,watch));
        log.info("Product details initialized");

    }

    private void loadOrdersData() {
        orderRepository.deleteAll();

        var tvSet = productRepository.findById("10001").get();
        var beer= productRepository.findById("10004").get();
        var phone= productRepository.findById("10006").get();
        var orderProduct1 = new OrderProduct(tvSet, 1);
        var orderProduct2 = new OrderProduct(beer, 5);
        var orderProduct3 = new OrderProduct(phone, 1);

        var order1 = new Order(OrderStatus.PAID, List.of(orderProduct1, orderProduct2, orderProduct3));

        orderRepository.save(order1);
        log.info("Order details initialized");
    }
}
