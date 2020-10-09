package edu.ap.projectteambisfits.category;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrimaryCategoryRepository extends MongoRepository<PrimaryCategory, String> {

    public PrimaryCategory findByid(String primcatid);

}