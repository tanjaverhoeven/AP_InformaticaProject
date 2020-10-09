package edu.ap.projectteambisfits.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location findById(String locationID) {
        return locationRepository.findByid(locationID);
    }

    @Override
    public Location findByCampusName(String campusname) {
        return locationRepository.findBycampusname(campusname);
    }

}