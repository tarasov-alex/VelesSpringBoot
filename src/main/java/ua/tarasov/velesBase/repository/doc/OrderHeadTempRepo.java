package ua.tarasov.velesBase.repository.doc;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.doc.OrderHeadTemp;

@Repository
public interface OrderHeadTempRepo extends MongoRepository<OrderHeadTemp, String> {

    Iterable<OrderHeadTemp> findByIdAgent(String idAgent);

    void deleteByIdAgent(String id_agent);

}
