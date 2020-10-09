package edu.ap.projectteambisfits.category;

import java.util.List;

public interface PrimaryCategoryService {

    List<PrimaryCategory> findAll();

    void deleteCategory(String id);

    PrimaryCategory saveCategory(PrimaryCategory defect);

    PrimaryCategory findById(String primcatid);

}