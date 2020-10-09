package edu.ap.projectteambisfits;

import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import edu.ap.projectteambisfits.category.Category;
import edu.ap.projectteambisfits.category.CategoryController;
import edu.ap.projectteambisfits.category.PrimaryCategoryService;
import edu.ap.projectteambisfits.defect.DefectController;
import edu.ap.projectteambisfits.defect.DefectService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class DefectIntegrationTest {

        private static final String BASE_URL = "http://localhost:8080/api";
        private static final String POST_ENDPOINT = "/defect";
        private static final String GET_ENDPOINT = "/defects";

        private static final String TEST_DEFECTNAME = "test-defectname";
        private static final String TEST_DEFECTDESCRIPTION = "test-defectdescription";
        private static final String TEST_DEFECTCAMPUSLOCATION = "test-campuslocation";
        private static final String TEST_DEFECTLOCATIONROOM = "test-defectlocationroom";
        private static final Boolean TEST_DEFECTNEARBY = false;
        private static final UUID TEST_USER_UUID = UUID.randomUUID();
        private String firstCategoryId;
        private String firstCategoryName;
        private String deleteEndpointFirstDefect;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private PrimaryCategoryService categoryService;

        @Autowired
        private DefectService defectService;

        @MockBean
        private DefectController defectController;

        @Before
        void retrieveFirstCategoryData() {
                firstCategoryId = categoryService.findAll().get(0).getId();
                firstCategoryName = categoryService.findAll().get(0).getName();
                deleteEndpointFirstDefect = "/defect/" + defectService.findAll().get(0).getId();
        }

        @Test
        void tryGetAllDefects() throws Exception {
                mockMvc.perform(get(BASE_URL + GET_ENDPOINT).accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());
        }

        @Test
        void tryGetDeleteFirstDefect() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + deleteEndpointFirstDefect))
                                .andExpect(status().isNotFound());
        }

        @Test
        void tryPostDefect() throws Exception {
                final String defectJson = formatDefectJSON(TEST_DEFECTNAME, TEST_DEFECTDESCRIPTION,
                                TEST_DEFECTCAMPUSLOCATION, TEST_DEFECTLOCATIONROOM, TEST_DEFECTNEARBY, firstCategoryId,
                                firstCategoryName, TEST_USER_UUID.toString());

                mockMvc.perform(post(BASE_URL + POST_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                .content(defectJson)).andExpect(status().isOk());
        }

        private String formatDefectJSON(String name, String description, String campuslocation, String locationroom,
                        Boolean nearby, String categoryid, String categoryname, String creatorid) {
                return "{\n" + "\t\"name\": \"" + name + "\",\n" + "\t\"description\": \"" + description + "\",\n"
                                + "\t\"campuslocation\": \"" + campuslocation + "\",\n" + "\t\"locationroom\": \""
                                + locationroom + "\",\n" + "\t\"nearby\": \"" + nearby + "\",\n" + "\t\"category\": "
                                + "{\"id\": \"" + categoryid + "\",\"name\": \"" + categoryname + "\"}" + ",\n"
                                + "\t\"photoid\": \"" + "" + "\",\n" + "\t\"creatorid\": \"" + creatorid + "\"\n" + "}";
        }
}
