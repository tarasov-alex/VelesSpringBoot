package ua.tarasov.velesBase.repository.doc;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.doc.Credit;

@Repository
public interface CreditRepo extends MongoRepository<Credit, String> {

    Iterable<Credit> findByIdAgent(String id);

}
