package edu.ap.projectteambisfits.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping(path = "/locations", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Location> getAllLocations() {
        return locationService.findAll();
    }

    @GetMapping(path = "/location/{campusname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Location getLocationByName(@PathVariable String campusname) {
        Location location = locationService.findByCampusName(campusname);
        return location;
    }

    @PostMapping(path = "/location")
    public ResponseEntity<?> newLocation(@RequestBody Location location) {
        locationService.saveLocation(location);
        return new ResponseEntity<>("Location added succesfully", HttpStatus.OK);
    }

    @PostMapping(path = "/addroom/{locationID}")
    public ResponseEntity<?> newRoom(@PathVariable("locationID") String locationID, @RequestBody Room room) {
        Location l = locationService.findById(locationID);
        l.addRoomToList(room);
        locationService.saveLocation(l);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/location/{id}/room/{roomnumber}")
    public ResponseEntity<?> deleteRoomFromCampus(@PathVariable String id, @PathVariable String roomnumber) {
        Location foundloc = locationService.findById(id);
        foundloc.setId(id);
        foundloc.removeRoomFromList(roomnumber);
        locationService.saveLocation(foundloc);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}