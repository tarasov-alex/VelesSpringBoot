package ua.tarasov.velesBase.repository.extra;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.extra.Token;
import ua.tarasov.velesBase.document.extra.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

 Optional<User> findByImei(String imei);

}
