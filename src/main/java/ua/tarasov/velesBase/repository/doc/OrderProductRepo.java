package ua.tarasov.velesBase.repository.doc;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.doc.OrderProduct;

@Repository
public interface OrderProductRepo extends MongoRepository<OrderProduct, String> {

    Iterable<OrderProduct> findByIdAgent(String idAgent);
}
