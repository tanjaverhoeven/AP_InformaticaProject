package edu.ap.projectteambisfits.location;

import java.util.List;

public interface LocationService {

    List<Location> findAll();

    Location saveLocation(Location location);

    Location findById(String locationID);

    Location findByCampusName(String campusname);
}