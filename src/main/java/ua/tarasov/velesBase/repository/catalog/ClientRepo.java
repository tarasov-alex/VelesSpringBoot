package ua.tarasov.velesBase.repository.catalog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.catalog.Client;

@Repository
public interface ClientRepo extends MongoRepository<Client, String> {

 }
