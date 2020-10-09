package edu.ap.projectteambisfits.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrimaryCategoryServiceImpl implements PrimaryCategoryService {

    @Autowired
    private PrimaryCategoryRepository categoryRepository;

    @Override
    public List<PrimaryCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public PrimaryCategory saveCategory(PrimaryCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    public PrimaryCategory findById(String primcatid) {
        return categoryRepository.findByid(primcatid);
    }
}