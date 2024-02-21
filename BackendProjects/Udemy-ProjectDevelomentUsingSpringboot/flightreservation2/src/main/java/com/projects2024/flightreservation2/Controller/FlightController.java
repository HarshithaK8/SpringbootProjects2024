package com.projects2024.flightreservation2.Controller;

import com.projects2024.flightreservation2.DAO.FlightDao;
import com.projects2024.flightreservation2.model.Flight;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightController {
    @Autowired
    FlightDao flightDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

    public FlightController() {
    }

    @RequestMapping({"/findflights"})
    public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate, ModelMap modelMap) {
        LOGGER.info("Inside the findFlights(), from: " + from + " to: " + to + " departure-date: " + departureDate);
        List<Flight> flights = this.flightDao.findFlights(from, to, departureDate);
        modelMap.addAttribute("flights", flights);
        LOGGER.info("The flights sent back are : " + flights);
        return "displayFlights";
    }

    @RequestMapping({"admin/showAddFlight"})
    public String addFlight() {
        return "addFlight";
    }
}