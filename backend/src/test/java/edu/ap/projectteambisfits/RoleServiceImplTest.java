package edu.ap.projectteambisfits;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ap.projectteambisfits.role.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class RoleServiceImplTest {

    @Mock
    RoleRepository roleRepo;
    @InjectMocks
    RoleServiceImpl roleService;

    @BeforeEach
    void setUp() throws Exception {

    }

    @Test
    public void shouldGetAllRoles() throws Exception {
        when(roleRepo.findAll()).thenReturn(Stream.of(new Role("TEST")).collect(Collectors.toList()));
        assertEquals(1, roleService.findAll().size());
    }

    @Test
    public void shouldCreateRoles() throws Exception {
        Role role = new Role("TEST");
        when(roleRepo.save(role)).thenReturn(role);
        assertEquals(role, roleService.saveRole(role));
    }

}
