package ua.tarasov.velesBase.repository.calculus;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.calculus.Price;

@Repository
public interface PriceRepo extends MongoRepository<Price, String> {

 }
