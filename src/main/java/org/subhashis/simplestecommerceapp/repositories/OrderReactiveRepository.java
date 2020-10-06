package org.subhashis.simplestecommerceapp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.subhashis.simplestecommerceapp.documents.Order;

public interface OrderReactiveRepository extends ReactiveMongoRepository<Order,String> {
}
