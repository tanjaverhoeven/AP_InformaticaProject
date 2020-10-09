package edu.ap.projectteambisfits.external;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExternalRepository extends MongoRepository<External, String> {

}