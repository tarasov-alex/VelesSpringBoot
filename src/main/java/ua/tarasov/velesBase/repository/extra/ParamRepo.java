package ua.tarasov.velesBase.repository.extra;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.extra.Param;

@Repository
public interface ParamRepo extends MongoRepository<Param, String> {

    Param findByNameParam(String nameParam);

}
