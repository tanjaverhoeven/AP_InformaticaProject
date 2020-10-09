package edu.ap.projectteambisfits;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ap.projectteambisfits.user.*;
import edu.ap.projectteambisfits.user.User.Genders;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepo;
    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp() throws Exception {

    }

    @Test
    public void shouldGetAllUsers() throws Exception {
        when(userRepo.findAll()).thenReturn(
                Stream.of(new User("TEST", "TEST", "TEST", Genders.FEMALE, "TEST", "TEST")).collect(Collectors.toList()));
        assertEquals(1, userService.findAll().size());
    }

    @Test
    public void shouldGetAllUsersByRole() throws Exception {
        User testUser1 = new User("TEST", "TEST", "TEST", Genders.FEMALE, "TEST", "TEST");
        User testUser2 = new User("TEST", "TEST", "TEST", Genders.FEMALE, "TEST", "TEST");
        when(userRepo.findByRole("TEST")).thenReturn(Stream.of(testUser1, testUser2).collect(Collectors.toList()));
        assertEquals(2, userService.findByRole("TEST").size());
    }

    @Test
    public void shouldCreateUser() throws Exception {
        User user = new User("TEST", "TEST", "TEST", Genders.FEMALE, "TEST", "TEST");
        when(userRepo.save(user)).thenReturn(user);
        assertEquals(user, userService.saveUser(user));
    }

    @Test
    public void shouldFindUserByFirstAndLastName() throws Exception {
        User user = new User("TEST", "TEST", "TEST", Genders.FEMALE, "TEST", "TEST");
        when(userRepo.findByLastnameAndFirstname("TEST", "TEST")).thenReturn(user);
        assertEquals(user, userService.findByFirstNameAndLastName("TEST", "TEST"));
    }

    @Test
    public void shouldFindUserById() throws Exception { 
        User user = new User("TEST", "TEST", "TEST", Genders.FEMALE, "TEST", "TEST");
        userRepo.save(user);
        when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
        assertEquals(user, userService.findById(user.getId()));
    }
}
