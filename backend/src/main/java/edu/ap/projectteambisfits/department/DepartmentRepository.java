package edu.ap.projectteambisfits.department;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepository extends MongoRepository<Department, String> {

    public Department findByid(String contactId);
}