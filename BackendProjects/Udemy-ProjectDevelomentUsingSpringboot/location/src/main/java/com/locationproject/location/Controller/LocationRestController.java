package com.locationproject.location.Controller;

import com.locationproject.location.DAO.LocationDAO;
import com.locationproject.location.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationRestController {

    @Autowired
    LocationDAO locationDAO;

    @GetMapping
    public List<Location> getAllLocations() {
        return locationDAO.findAll();
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location){
        return locationDAO.save(location);
    }

    @PutMapping
    public Location updateLocation(@RequestBody Location location){
        return locationDAO.save(location);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable ("id") int id){
        locationDAO.deleteById(id);
    }

    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable("id") int id){
        return locationDAO.findById(id).get();
    }
}
