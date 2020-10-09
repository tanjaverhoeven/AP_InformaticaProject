package edu.ap.projectteambisfits.location;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {

    public Location findByid(String locationID);

    public Location findBycampusname(String campusname);
}