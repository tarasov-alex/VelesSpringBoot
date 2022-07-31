package ua.tarasov.velesBase.repository.doc.catalog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.tarasov.velesBase.document.catalog.Brand;

@Repository
public interface BrandRepo extends MongoRepository<Brand, String> {

 }
