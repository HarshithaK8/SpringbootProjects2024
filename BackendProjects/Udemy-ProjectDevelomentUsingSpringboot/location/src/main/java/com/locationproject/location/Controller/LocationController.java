package com.locationproject.location.Controller;

import com.locationproject.location.DAO.LocationDAO;
import com.locationproject.location.Service.LocationService;
import com.locationproject.location.Util.EmailUtil;
import com.locationproject.location.Util.ReportUtil;
import com.locationproject.location.model.Location;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LocationController {

    @Autowired
    LocationService locationService;

    @Autowired
    LocationDAO locationDAO;

    @Autowired
    ReportUtil reportUtil;

    @Autowired
    EmailUtil util;

    @Autowired
    ServletContext sc;

    @RequestMapping("/showcreate")
    public String showCreate(){
        return "createlocation";
    }

    @RequestMapping("/saveLoc")
    public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap){
        Location locationsaved = locationService.saveLocation(location);
        String msg = "The location saved successfully with id" + locationsaved.getId();
        modelMap.addAttribute("msg",msg);
        util.sendEmail("springharshita@gmail.com","Location saved",
                "Email sent successfully");
        return "createlocation";
    }

    @RequestMapping("/displayLocations")
    public String displayLocations(ModelMap modelMap){
        List<Location> list = locationService.getAllLocations();
        modelMap.addAttribute("locations",list);
        return ("displayLocations");
    }

    @RequestMapping("/deleteLocation")
    public String deleteAndDisplay(@RequestParam("id")int id, ModelMap modelMap){
//        Location location = locationService.getLocationById(id);
        Location location = new Location();
        location.setId(id);
        locationService.deleteLocation(location);
        List<Location> list = locationService.getAllLocations();
        modelMap.addAttribute("locations",list);
        return ("displayLocations");
    }

    @RequestMapping("/showUpdate")
    public String showUpdate(@RequestParam("id") int id, ModelMap modelMap){
        Location location = locationService.getLocationById(id);
        modelMap.addAttribute("location",location);
        return ("updateLocation");
    }

    @RequestMapping("/updateLoc")
    public String updateAndDisplay(@ModelAttribute("location") Location location,ModelMap modelMap){
        locationService.updateLocation(location);
        List<Location> list = locationService.getAllLocations();
        modelMap.addAttribute("locations",list);
        return ("displayLocations");
    }

    @RequestMapping("/generateReport")
    public String generateReport() {
        List<Object[]> data = locationDAO.findByTypeAndTypeCount();
        String path = sc.getRealPath("/");
        //web app will store the pie chart in the root path
        reportUtil.generatePieCharts(path,data);
        return("report");
    }
}
