package edu.ap.projectteambisfits.user;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import edu.ap.projectteambisfits.user.User;

public interface UserRepository extends MongoRepository<User, String>{
    List<User> findByRole(String role);
    User findByLastnameAndFirstname(String firstname, String lastname);
}