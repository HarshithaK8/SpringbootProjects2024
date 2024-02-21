package com.projects2024.flightCheckin.Controller;

import com.projects2024.flightCheckin.DTO.UpdateReservationRequest;
import com.projects2024.flightCheckin.Integration.ReservationRestClient;
import com.projects2024.flightCheckin.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightCheckinController {

    @Autowired
    ReservationRestClient reservationRestClient;

    @RequestMapping("/showCheckin")
    public String show(){
        return "startCheckin";
    }

    @RequestMapping("/startCheckin")
    public String startCheckin(@RequestParam("id")int id, ModelMap modelMap){
        Reservation reservation = reservationRestClient.findReservation(id);
        modelMap.addAttribute("reservation",reservation);
        return "showAndUpdate";
    }

    @RequestMapping("/completeCheckin")
    public String completeCheckin(@RequestParam("noOfBags")int noOfBags,
                                  @RequestParam("reservationId")int reservationID,
                                  ModelMap modelMap){
        UpdateReservationRequest request = new UpdateReservationRequest();
        request.setId(reservationID);
        request.setCheckedIn(true);
        request.setNoOfBags(noOfBags);
        reservationRestClient.updateReservation(request);

        modelMap.addAttribute("msg","CheckIn completed! Have a great flight!");
        return "confirmationMessage";
    }
}
