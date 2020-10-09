package edu.ap.projectteambisfits;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.projectteambisfits.location.Location;
import edu.ap.projectteambisfits.location.LocationRepository;
import edu.ap.projectteambisfits.location.LocationServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class LocationServiceImplTests {

    @Mock
    LocationRepository l;
    @InjectMocks
    LocationServiceImpl locationService;

    @BeforeEach
    void setUp() throws Exception {

    }

    @Test
    public void shouldGetAllLocations() throws Exception {
        when(l.findAll()).thenReturn(Stream.of(new Location("TEST", "TEST")).collect(Collectors.toList()));
        assertEquals(1, locationService.findAll().size());
    }

    @Test
    public void shouldCreatelocect() throws Exception {
        Location loc = new Location("TEST", "TEST");
        when(l.save(loc)).thenReturn(loc);
        assertEquals(loc, locationService.saveLocation(loc));
    }
}
