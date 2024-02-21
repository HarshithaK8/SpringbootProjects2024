package com.projects2024.flightreservation2.Controller;

import com.projects2024.flightreservation2.DAO.ReservationDao;
import com.projects2024.flightreservation2.DTO.UpdateReservationRequest;
import com.projects2024.flightreservation2.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationRestController {
    @Autowired
    ReservationDao reservationDao;

    public ReservationRestController() {
    }

    @GetMapping({"/reservations/{id}"})
    public Reservation findReservation(@PathVariable("id") int id) {
        return (Reservation)this.reservationDao.findById(id).get();
    }

    @PostMapping({"/reservations/update"})
    public Reservation updateReservation(@RequestBody UpdateReservationRequest request) {
        Reservation reservation = (Reservation)this.reservationDao.findById(request.getId()).get();
        reservation.setCheckedIn(request.getCheckedIn());
        reservation.setNoOfBags(request.getNoOfBags());
        return (Reservation)this.reservationDao.save(reservation);
    }
}
