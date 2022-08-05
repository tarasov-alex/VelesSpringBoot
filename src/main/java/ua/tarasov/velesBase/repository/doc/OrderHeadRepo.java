package ua.tarasov.velesBase.repository.doc;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.doc.OrderHead;
import ua.tarasov.velesBase.model.UpdateObj;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Repository
public interface OrderHeadRepo extends MongoRepository<OrderHead, String> {

  Iterable<OrderHead> findByIdAgent(String idAgent);

  Optional<OrderHead> findByIdDocAndDateUpdateGreaterThan(String idDoc, Date dateUpdate);

  @Query(fields = "idDoc,dateUpdate")
  Iterable<UpdateObj> findIdByIdDoc(Arrays idDocs);
}
