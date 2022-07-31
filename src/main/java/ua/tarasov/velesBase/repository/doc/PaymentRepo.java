package ua.tarasov.velesBase.repository.doc;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.doc.Credit;
import ua.tarasov.velesBase.document.doc.Payment;

@Repository
public interface PaymentRepo extends MongoRepository<Payment, String> {

    Iterable<Payment> findByIdAgent(String id);

}
