package org.subhashis.simplestecommerceapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.subhashis.simplestecommerceapp.documents.OrderProduct;

public interface OrderProductRepository extends MongoRepository<OrderProduct,String> {
}
