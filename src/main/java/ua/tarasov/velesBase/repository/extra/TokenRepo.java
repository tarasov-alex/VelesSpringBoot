package ua.tarasov.velesBase.repository.extra;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.extra.Token;

@Repository
public interface TokenRepo extends MongoRepository<Token, String> {

 Token findByToken(String token);

}
