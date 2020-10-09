package edu.ap.projectteambisfits.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "locations")
public class Room {

    @Id
    private String id;
    private String roomnumber;

    @JsonCreator
    public Room(@JsonProperty("roomnumber") String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getRoomNumber() {
        return roomnumber;
    }

    public String getId() {
        return id;
    }

}