package com.projects2024.flightCheckin.Integration;

import com.projects2024.flightCheckin.DTO.UpdateReservationRequest;
import com.projects2024.flightCheckin.model.Reservation;

public interface ReservationRestClient {
    Reservation findReservation(int id);
    Reservation updateReservation(UpdateReservationRequest request);
}
