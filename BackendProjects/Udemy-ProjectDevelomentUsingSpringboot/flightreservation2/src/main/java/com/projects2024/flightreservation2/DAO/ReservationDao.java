package com.projects2024.flightreservation2.DAO;

import com.projects2024.flightreservation2.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDao extends JpaRepository<Reservation, Integer> {
}
