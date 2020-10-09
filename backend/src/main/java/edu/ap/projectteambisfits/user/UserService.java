package edu.ap.projectteambisfits.user;

import java.util.List;

public interface UserService {
    List<User> findAll();

    List<User> findByRole(String role);

    User findByFirstNameAndLastName(String firstname, String lastname);

    User findById(String id);

    void deleteUser(User user);

    User saveUser(User user);

}