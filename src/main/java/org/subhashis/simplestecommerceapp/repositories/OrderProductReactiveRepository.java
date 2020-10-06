package org.subhashis.simplestecommerceapp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.subhashis.simplestecommerceapp.documents.OrderProduct;

public interface OrderProductReactiveRepository extends ReactiveMongoRepository<OrderProduct,String> {
}
