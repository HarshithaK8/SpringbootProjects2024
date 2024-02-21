package com.projects2024.flightCheckin.Integration;

import com.projects2024.flightCheckin.DTO.UpdateReservationRequest;
import com.projects2024.flightCheckin.model.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReservationRestClientImpl implements ReservationRestClient{

    @Value("${reservation.rest.url}")
    private String RESERVATION_REST_URL;

    @Override
    public Reservation findReservation(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(RESERVATION_REST_URL+id,
                Reservation.class);
    }

    @Override
    public Reservation updateReservation(UpdateReservationRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8080/flightreservationweb/reservations/update",
                request,Reservation.class);
    }
}
