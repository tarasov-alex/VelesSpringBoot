package ua.tarasov.velesBase.repository.calculus;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.calculus.Balance;

@Repository
public interface BalanceRepo extends MongoRepository<Balance, String> {

    Iterable<Balance> findAllByIdOrganisation(String idOrganisation);
}
