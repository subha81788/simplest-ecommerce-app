package org.subhashis.simplestecommerceapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.subhashis.simplestecommerceapp.documents.Order;
import org.subhashis.simplestecommerceapp.documents.OrderProduct;
import org.subhashis.simplestecommerceapp.documents.OrderStatus;
import org.subhashis.simplestecommerceapp.documents.Product;
import org.subhashis.simplestecommerceapp.repositories.OrderProductRepository;
import org.subhashis.simplestecommerceapp.repositories.OrderRepository;
import org.subhashis.simplestecommerceapp.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class DataInitializerBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;

    private OrderRepository orderRepository;

    private OrderProductRepository orderProductRepository;

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
        var tvSet = new Product("10001", "TV Set", new BigDecimal(300.00), "http://placehold.it/200x100");
        var gameConsole = new Product("10002", "Game Console", new BigDecimal(200.00), "http://placehold.it/200x100");
        var sofa = new Product("10003", "Sofa", new BigDecimal(100.00), "http://placehold.it/200x100");
        var beer = new Product("10004", "Beer", new BigDecimal(5.00), "http://placehold.it/200x100");
        var iceCream = new Product("10005", "Ice Cream", new BigDecimal(3.00), "http://placehold.it/200x100");
        var phone = new Product("10006", "Phone", new BigDecimal(500.00), "http://placehold.it/200x100");
        var watch = new Product("10007", "Watch", new BigDecimal(30.00), "http://placehold.it/200x100");

        productRepository.deleteAll();
        productRepository.saveAll(List.of(tvSet,gameConsole,sofa,beer,iceCream,phone,watch));
        log.info("Product details initialized");

    }

    private void loadOrdersData() {
        var tvSet = productRepository.findById("10001").get();
        var orderProduct1 = new OrderProduct(tvSet, 1);
        var order1 = new Order(OrderStatus.PAID, List.of(orderProduct1));

        orderRepository.deleteAll();
        orderRepository.save(order1);
        log.info("Order details initialized");
    }
}
