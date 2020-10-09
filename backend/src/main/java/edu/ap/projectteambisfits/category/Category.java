package edu.ap.projectteambisfits.category;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;

public class Category {
    private String name;
    @Id
    private String id;

    @JsonCreator
    public Category(@JsonProperty("name") String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}