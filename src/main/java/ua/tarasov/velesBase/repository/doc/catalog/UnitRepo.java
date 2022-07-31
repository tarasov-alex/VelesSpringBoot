package ua.tarasov.velesBase.repository.doc.catalog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.catalog.Unit;

@Repository
public interface UnitRepo extends MongoRepository<Unit, String> {

 }