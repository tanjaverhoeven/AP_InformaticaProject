package edu.ap.projectteambisfits.person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person")
public abstract class Person {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Boolean external;
    private List<String> assignedDefects;
    private List<String> assignedTasks;

    public Person(String firstname, String lastname, String email, Boolean external) {
        this.id = UUID.randomUUID().toString();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.external = false;
        this.assignedDefects = new ArrayList<String>();
        this.assignedTasks = new ArrayList<String>();
    }

    public void setId(String id) {
        this.id = id;
    }

    // #region GETTERS
    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getExternal() {
        return external;
    }
    // #endregion

    // #region ASSIGNING
    public List<String> getAssignedDefects() {
        return assignedDefects;
    }

    public List<String> getAssignedTasks() {
        return assignedTasks;
    }

    public void addAssignedDefects(String defectId) {
        this.assignedDefects.add(defectId);
    }

    public void addAssignedTask(String taskid) {
        this.assignedTasks.add(taskid);
    }

    public void deleteAssignedDefect(String defectId) {
        this.assignedDefects.remove(defectId);
    }

    public void deleteAssignedTask(String taskid) {
        this.assignedTasks.remove(taskid);
    }
    // #endregion
}