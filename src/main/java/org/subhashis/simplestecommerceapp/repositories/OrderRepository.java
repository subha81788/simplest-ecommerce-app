package org.subhashis.simplestecommerceapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.subhashis.simplestecommerceapp.documents.Order;

public interface OrderRepository extends MongoRepository<Order,String> {
}
