package edu.ap.projectteambisfits.location;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "locations")
public class Location {
    private String streetname;
    @Id
    private String id;
    private String campusname;
    private List<Room> roomlist;

    public Location(String streetname, String campusname) {
        this.roomlist = new ArrayList<>();
        this.campusname = campusname;
        this.streetname = streetname;
    }

    public String getStreetName() {
        return streetname;
    }

    public String getCampusName() {
        return campusname;
    }

    public void addRoomToList(Room room) {
        this.roomlist.add(room);
    }

    public void removeRoomFromList(String roomnumber) {
        for (int i = 0; i < this.roomlist.size(); i++) {
            if (this.roomlist.get(i).getRoomNumber().equals(roomnumber)) {
                this.roomlist.remove(i);
            }
        }
    }

    public String getId() {
        return id;
    }

    public List<Room> getRoomlist() {
        return roomlist;
    }

    public void setId(String id) {
        this.id = id;
    }

}