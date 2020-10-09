package edu.ap.projectteambisfits.defect;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DefectRepository extends MongoRepository<Defect, String> {
    List<Defect> findByStatus(String status);
}