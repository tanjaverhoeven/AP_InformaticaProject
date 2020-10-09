package edu.ap.projectteambisfits.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private PrimaryCategoryService primaryCategoryService;

    @GetMapping(path = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PrimaryCategory> getAllCategories() {
        return primaryCategoryService.findAll();
    }

    @PutMapping(path = "/categories/{primcatid}")
    public ResponseEntity<?> updatePrimaryCategory(@RequestBody PrimaryCategory category,
            @PathVariable String primcatid) {
        PrimaryCategory primcatold = primaryCategoryService.findById(primcatid);
        category.setId(primcatold.getId());
        primaryCategoryService.saveCategory(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/category")
    public ResponseEntity<?> newPrimaryCategory(@RequestBody PrimaryCategory category) {
        primaryCategoryService.saveCategory(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/category/{primcatid}/{categoryid}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable String primcatid, @PathVariable String categoryid) {
        PrimaryCategory primcatfound = primaryCategoryService.findById(primcatid);
        primcatfound.setId(primcatid);
        primcatfound.removeCategoryFromList(categoryid);
        primaryCategoryService.saveCategory(primcatfound);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/category/{primcatid}")
    public ResponseEntity<?> newCategory(@PathVariable("primcatid") String primcatid, @RequestBody Category cat) {
        PrimaryCategory primcat = primaryCategoryService.findById(primcatid);
        primcat.addCategoryToList(cat);
        primaryCategoryService.saveCategory(primcat);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}