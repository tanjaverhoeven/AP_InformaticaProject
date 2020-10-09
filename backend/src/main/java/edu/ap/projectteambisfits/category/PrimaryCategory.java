package edu.ap.projectteambisfits.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class PrimaryCategory {
    private String name;
    private List<Category> categoryList;
    private String emailadress;
    @Id
    private String id;

    public PrimaryCategory(String name, String emailadress) {
        this.name = name;
        this.categoryList = new ArrayList<>();
        this.emailadress = emailadress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void addCategoryToList(Category cat) {
        this.categoryList.add(cat);
    }

    public void removeCategoryFromList(String categoryid) {
        for (int i = 0; i < this.categoryList.size(); i++) {
            if (this.categoryList.get(i).getId().equals(categoryid)) {
                this.categoryList.remove(i);
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailadress() {
        return emailadress;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
    }

}