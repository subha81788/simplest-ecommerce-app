package org.subhashis.simplestecommerceapp.repositories;

import org.bson.types.Decimal128;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.subhashis.simplestecommerceapp.documents.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product,String> {

    @Query("{ 'price' : ?0 }")
    public Optional<Product> findByPrice(Decimal128 price);

    // Retrieve the product list ordered by the price
    public List<Product> findAllByOrderByPrice();

    @Query("{ 'price' : { $gt : ?0, $lt : ?1 } }")
    public List<Product> findByPriceRange(Decimal128 start, Decimal128 end);

    @Query("{ 'name' : ?0, 'imageUrl' : ?1 }")
    public Optional<Product> findByNameAndImageUrl(String name, String imageUrl);
}
