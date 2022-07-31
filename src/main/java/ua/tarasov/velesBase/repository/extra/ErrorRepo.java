package ua.tarasov.velesBase.repository.extra;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.extra.Error;

@Repository
public interface ErrorRepo extends MongoRepository<Error, String> {

 }
