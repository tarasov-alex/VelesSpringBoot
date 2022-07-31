package ua.tarasov.velesBase.repository.doc;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.doc.OrderHead;
import ua.tarasov.velesBase.document.doc.OrderHeadTemp;
import ua.tarasov.velesBase.model.UpdateObj;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Repository
public interface OrderHeadTempRepo extends MongoRepository<OrderHeadTemp, String> {
    Iterable<OrderHeadTemp> findByIdAgent(String id_agent);

}
