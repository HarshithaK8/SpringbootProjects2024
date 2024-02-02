package com.locationproject.location.Service;

import com.locationproject.location.model.Location;

import java.util.List;

public interface LocationService {

    Location saveLocation(Location location);
    Location updateLocation(Location location);
    Location getLocationById(int id);
    List<Location> getAllLocations();
    void deleteLocation(Location location);

}
