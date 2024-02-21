package com.projects2024.flightreservation2.Service;

import com.projects2024.flightreservation2.DAO.FlightDao;
import com.projects2024.flightreservation2.DAO.PassengerDao;
import com.projects2024.flightreservation2.DAO.ReservationDao;
import com.projects2024.flightreservation2.DTO.ReservationRequest;
import com.projects2024.flightreservation2.model.Flight;
import com.projects2024.flightreservation2.model.Passenger;
import com.projects2024.flightreservation2.model.Reservation;
import com.projects2024.flightreservation2.util.EmailUtil;
import com.projects2024.flightreservation2.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    FlightDao flightDao;
    @Autowired
    PassengerDao passengerDao;
    @Autowired
    ReservationDao reservationDao;
    @Autowired
    EmailUtil emailUtil;
    @Autowired
    PDFGenerator pdfGenerator;
    @Value("${reservationservice.filepath.pdf}")
    String filePathForPDF;

    public ReservationServiceImpl() {
    }

    @Transactional
    public Reservation bookFlight(ReservationRequest reservationRequest) {
        Flight flight = (Flight)this.flightDao.findById(reservationRequest.getFlightId()).get();
        Passenger passenger = new Passenger();
        passenger.setFirstName(reservationRequest.getPfirstname());
        passenger.setLastName(reservationRequest.getPlastname());
        passenger.setEmail(reservationRequest.getPemail());
        passenger.setPhone(reservationRequest.getPphone());
        this.passengerDao.save(passenger);
        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);
        reservation.setCheckedIn(false);
        Reservation updatedReservation = (Reservation)this.reservationDao.save(reservation);
        String var10000 = this.filePathForPDF;
        String filePath = var10000 + updatedReservation.getId();
        this.pdfGenerator.generateItinerary(updatedReservation, filePath);
        this.emailUtil.sendItinerary(passenger.getEmail(), filePath);
        return reservation;
    }
}
