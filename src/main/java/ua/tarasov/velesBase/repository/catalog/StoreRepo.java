package ua.tarasov.velesBase.repository.catalog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.catalog.Store;

@Repository
public interface StoreRepo extends MongoRepository<Store, String> {

 }
