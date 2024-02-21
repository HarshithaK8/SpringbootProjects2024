package com.projects2024.flightreservation2.Controller;

import com.projects2024.flightreservation2.DAO.FlightDao;
import com.projects2024.flightreservation2.DTO.ReservationRequest;
import com.projects2024.flightreservation2.Service.ReservationService;
import com.projects2024.flightreservation2.model.Flight;
import com.projects2024.flightreservation2.model.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {
    @Autowired
    FlightDao flightDao;
    @Autowired
    ReservationService reservationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    public ReservationController() {
    }

    @RequestMapping({"/showCompleteReservation"})
    public String showCompleteReservation(@RequestParam("flightId") int id, ModelMap modelMap) {
        LOGGER.info("Inside the showCompleteReservation() and the flight id we got from UI Is: %d".formatted(id));
        Flight flight = (Flight)this.flightDao.findById(id).get();
        modelMap.addAttribute("flight", flight);
        LOGGER.info("Inside the showCompleteReservation() and the flight is : %s".formatted(flight));
        return "displayCompleteReservation";
    }

    @RequestMapping({"/confirmReservation"})
    public String confirmReservation(ReservationRequest reservationRequest, ModelMap modelMap) {
        LOGGER.info("Inside the confirmReservation() and the reservationReq is : " + reservationRequest);
        Reservation reservation = this.reservationService.bookFlight(reservationRequest);
        modelMap.addAttribute("msg", "The reservation is successful with reservation id : " + reservation.getId());
        LOGGER.info("Inside the confirmReservation() and the resrvation id sent back is : " + reservation.getId());
        return "displayConfirmation";
    }
}