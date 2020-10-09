package edu.ap.projectteambisfits.photo;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
public class Photo {
    @Id
    private String id;

    private String title;

    private Binary image;

    public Photo(String title, Binary image) {
        this.title = title;
        this.image = image;
    }

    public Binary getImage() {
        return this.image;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}