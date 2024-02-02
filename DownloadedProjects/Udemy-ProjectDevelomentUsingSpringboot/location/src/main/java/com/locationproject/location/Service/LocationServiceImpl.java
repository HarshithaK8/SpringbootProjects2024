package com.locationproject.location.Service;

import com.locationproject.location.DAO.LocationDAO;
import com.locationproject.location.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    private LocationDAO locationDAO;

    @Override
    public Location saveLocation(Location location) {
        return locationDAO.save(location);
    }

    @Override
    public Location updateLocation(Location location) {
        return locationDAO.save(location);
    }

    @Override
    public Location getLocationById(int id) {
        return locationDAO.findById(id).get();
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDAO.findAll();
    }

    @Override
    public void deleteLocation(Location location) {
        locationDAO.delete(location);
    }
}
