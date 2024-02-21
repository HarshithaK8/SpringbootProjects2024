package com.projects2024.flightreservation2.DAO;

import com.projects2024.flightreservation2.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerDao extends JpaRepository<Passenger, Integer> {
}
