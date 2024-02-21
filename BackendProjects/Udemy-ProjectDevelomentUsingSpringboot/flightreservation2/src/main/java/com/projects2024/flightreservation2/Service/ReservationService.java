package com.projects2024.flightreservation2.Service;

import com.projects2024.flightreservation2.DTO.ReservationRequest;
import com.projects2024.flightreservation2.model.Reservation;

public interface ReservationService {
    Reservation bookFlight(ReservationRequest reservationRequest);
}