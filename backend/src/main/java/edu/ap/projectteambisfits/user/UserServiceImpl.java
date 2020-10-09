package edu.ap.projectteambisfits.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByRole(String role) {

        return userRepository.findByRole(role);
    }

    @Override
    public User findByFirstNameAndLastName(String firstname, String lastname) {
        return userRepository.findByLastnameAndFirstname(firstname, lastname);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}