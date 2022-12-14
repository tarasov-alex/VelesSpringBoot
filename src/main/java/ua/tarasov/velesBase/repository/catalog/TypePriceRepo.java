package ua.tarasov.velesBase.repository.catalog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.catalog.TypePrice;

@Repository
public interface TypePriceRepo extends MongoRepository<TypePrice, String> {

    Iterable<TypePrice> findAllByIdOrganisation(String idOrganisation);
}
