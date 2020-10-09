package edu.ap.projectteambisfits.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.ap.projectteambisfits.MetaData;
import edu.ap.projectteambisfits.category.Category;
import edu.ap.projectteambisfits.enums.Status;

@Document(collection = "tasks")
public class Task {
    private String name;
    @Id
    private String id;
    private String description;
    private String fixerid;
    private String campuslocation;
    private String locationroom;
    private Status status;
    private LocalDateTime statuschanged;
    private Category category;
    private MetaData metadata;
    private boolean nearby;
    // this param is not used but needed to prevent an error
    private String creatorid;

    public Task(String name, String description, String campuslocation, String locationroom,
            Category category, boolean nearby, String creatorid) {
        this.name = name;
        this.description = description;
        this.fixerid = "";
        this.campuslocation = campuslocation;
        this.locationroom = locationroom;
        this.status = Status.OPEN;
        this.statuschanged = LocalDateTime.now();
        this.category = category;
        this.metadata = new MetaData(creatorid);
        this.nearby = nearby;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFixerid() {
        return fixerid;
    }

    public String getCampuslocation() {
        return campuslocation;
    }

    public String getLocationroom() {
        return locationroom;
    }

    public Status getStatus() {
        return status;
    }

    public Category getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFixerid(String fixerid) {
        this.fixerid = fixerid;
    }

    public void setStatusChanged() {
        this.statuschanged = LocalDateTime.now();
    }

    public String getStatusChanged() {
        return this.statusChangedString();
    }

    public String statusChangedString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM HH:mm");
        return dtf.format(statuschanged);
    }

    public MetaData getMetaData() {
        return metadata;
    }

    public boolean getNearby() {
        return nearby;
    }

    public void setNearby(boolean nearby) {
        this.nearby = nearby;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCampuslocation(String campuslocation) {
        this.campuslocation = campuslocation;
    }

    public void setLocationroom(String locationroom) {
        this.locationroom = locationroom;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
