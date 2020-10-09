package edu.ap.projectteambisfits.role;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role")
public class Role {
    private String rolename;
    @Id
    private String id;

    public String getRolename() {
        return this.rolename;
    }

    public String getId() {
        return this.id;
    }

    @JsonCreator
    public Role(@JsonProperty("rolename") String rolename) {
        this.rolename = rolename;
    }

}