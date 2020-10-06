package org.subhashis.simplestecommerceapp.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.subhashis.simplestecommerceapp.documents.Product;
import reactor.core.publisher.Mono;

public interface ProductReactiveRepository extends ReactiveMongoRepository<Product,String> {

    @Query("{ 'name' : ?0, 'imageUrl' : ?1 }")
    public Mono<Product> findByNameAndImageUrl(String name, String imageUrl);
}
