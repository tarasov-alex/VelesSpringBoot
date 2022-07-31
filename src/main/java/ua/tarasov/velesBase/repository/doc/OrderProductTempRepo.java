package ua.tarasov.velesBase.repository.doc;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.doc.OrderProduct;
import ua.tarasov.velesBase.document.doc.OrderProductTemp;

@Repository
public interface OrderProductTempRepo extends MongoRepository<OrderProductTemp, String> {

    Iterable<OrderProductTemp> findByIdAgent(String idAgent);
}
