package edu.ap.projectteambisfits;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.projectteambisfits.category.Category;
import edu.ap.projectteambisfits.defect.Defect;
import edu.ap.projectteambisfits.defect.DefectRepository;
import edu.ap.projectteambisfits.defect.DefectServiceImpl;


@SpringBootTest
class DefectServiceImplTests {

    @Mock
    DefectRepository defectRepo;
    @InjectMocks
    DefectServiceImpl defectService;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    @Ignore
    public void shouldGetAllDefects() throws Exception {
        Category category = new Category("TEST");
        when(defectRepo.findAll()).thenReturn(Stream.of(new Defect("TEST", "TEST",
        "TEST", "TEST", category, "TEST", true, "TEST")).collect(Collectors.toList()));
        assertEquals(1, defectService.findAll().size());
    }

    @Test
    @Ignore
    public void shouldCreateDefect() throws Exception {
        Category category = new Category("TEST");
        Defect def = new Defect("TEST", "TEST",
        "TEST", "TEST", category, "TEST", true, "TEST"); when(defectRepo.save(def)).thenReturn(def); assertEquals(def,
        defectService.saveDefect(def));      
    }

    @Test
    public void shouldDeleteDefectById() throws Exception {
        Category category = new Category("Test");
        Defect defect = new Defect("TEST", "TEST",
        "TEST", "TEST", category, "TEST", true, "TEST");
        when(defectRepo.save(defect)).thenReturn(defect);
        assertEquals(defect, defectService.saveDefect(defect));
        defectService.deleteDefect(defect.getId());
        assertNotEquals(defect, defectRepo.findById(defect.getId()));
    }
}
