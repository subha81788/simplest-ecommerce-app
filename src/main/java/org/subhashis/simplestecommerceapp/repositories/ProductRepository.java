package org.subhashis.simplestecommerceapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.subhashis.simplestecommerceapp.documents.Product;

public interface ProductRepository extends MongoRepository<Product,String> {

    @Query("{ 'name' : ?0, 'imageUrl' : ?1 }")
    public Product findByNameAndImageUrl(String name, String imageUrl);
}
