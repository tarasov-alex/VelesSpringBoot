package ua.tarasov.velesBase.repository.doc.catalog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.catalog.Shop;

@Repository
public interface ShopRepo extends MongoRepository<Shop, String> {

 }
