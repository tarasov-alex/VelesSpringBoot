package ua.tarasov.velesBase.repository.catalog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.catalog.Product;

@Repository
public interface ProductRepo extends MongoRepository<Product, String> {

 Iterable<Product> findByHeritageLike(String heritage);

 }
