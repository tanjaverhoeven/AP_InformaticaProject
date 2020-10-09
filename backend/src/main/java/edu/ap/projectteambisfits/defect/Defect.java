package edu.ap.projectteambisfits.defect;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.ap.projectteambisfits.MetaData;
import edu.ap.projectteambisfits.category.Category;
import edu.ap.projectteambisfits.enums.Priority;
import edu.ap.projectteambisfits.enums.Status;

@Document(collection = "defects")
public class Defect {

    private String name;
    @Id
    private String id;
    private String description;
    private int upvotes;
    private String fixerid;
    private String campuslocation;
    private String locationroom;
    private Priority priority;
    private Status status;
    private LocalDateTime statuschanged;
    private Category category;
    private String photoid;
    private List<String> observers;
    private MetaData metadata;
    private boolean nearby;
    // this param is not used but needed to prevent an error
    private String creatorid;

    public Defect(String name, String description, String campuslocation, String locationroom,
            Category category, String photoid, boolean nearby, String creatorid) {
        this.name = name;
        this.description = description;
        this.upvotes = 0;
        this.fixerid = "";
        this.campuslocation = campuslocation;
        this.locationroom = locationroom;
        this.priority = Priority.LOW;
        this.status = Status.OPEN;
        this.statuschanged = LocalDateTime.now();
        this.category = category;
        this.photoid = photoid;
        this.metadata = new MetaData(creatorid);
        this.observers = new ArrayList<String>();
        this.observers.add(creatorid);
        this.nearby = nearby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUpvotes() {
        if (upvotes < 1) {
            return 0;
        } else {
            return upvotes;
        }

    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public String getFixerid() {
        return fixerid;
    }

    public void setFixerid(String fixerid) {
        this.fixerid = fixerid;
    }

    public String getCampuslocation() {
        return campuslocation;
    }

    public void setCampuslocation(String campuslocation) {
        this.campuslocation = campuslocation;
    }

    public String getLocationroom() {
        return locationroom;
    }

    public void setLocationroom(String locationroom) {
        this.locationroom = locationroom;
    }

    public Status getStatus() {
        return status;
    }

    public String getStatusChanged() {
        return this.statusChangedString();
    }

    public int getPriority() {
        return priority.getPriorityNumber();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPhotoid() {
        return photoid;
    }

    public void setPhotoid(String photoid) {
        this.photoid = photoid;
    }

    public boolean getNearby() {
        return nearby;
    }

    public void setNearby(boolean nearby) {
        this.nearby = nearby;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStatusChanged() {
        this.statuschanged = LocalDateTime.now();
    }

    public MetaData getMetaData() {
        return metadata;
    }

    public List<String> getObservers() {
        return observers;
    }

    public void subscribe(String observer) {
        observers.add(observer);
    }

    public void unsubscribe(String observer) {
        observers.remove(observer);
    }

    public String statusChangedString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM HH:mm");
        return dtf.format(statuschanged);
    }

    public void setMetadata(MetaData metadata) {
        this.metadata = metadata;
    }

}
