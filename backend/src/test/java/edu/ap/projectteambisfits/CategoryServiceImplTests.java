package edu.ap.projectteambisfits;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.projectteambisfits.category.PrimaryCategory;
import edu.ap.projectteambisfits.category.PrimaryCategoryRepository;
import edu.ap.projectteambisfits.category.PrimaryCategoryServiceImpl;

@SpringBootTest
class CategoryServiceImplTests {

    @Mock
    PrimaryCategoryRepository categoryRepo;
    @InjectMocks
    PrimaryCategoryServiceImpl categoryService;;

    @BeforeEach
    void setUp() throws Exception {

    }

    @Test
    public void shouldGetAllCategories() throws Exception {
        when(categoryRepo.findAll())
                .thenReturn(Stream.of(new PrimaryCategory("TEST", "TEST@email.com")).collect(Collectors.toList()));
        assertEquals(1, categoryService.findAll().size());
    }

    @Test
    public void shouldCreateCategory() throws Exception {
        PrimaryCategory cat = new PrimaryCategory("TEST", "TEST@Email.com");
        when(categoryRepo.save(cat)).thenReturn(cat);
        assertEquals(cat, categoryService.saveCategory(cat));
    }
}
