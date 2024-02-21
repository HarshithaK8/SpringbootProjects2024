package com.projects2024.flightreservation2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
        name = "flight"
)
public class Flight extends AbstractEntity {
    @Column(
            name = "flight_no"
    )
    private String flightNumber;
    @Column(
            name = "operating_airlines"
    )
    private String operatingAirlines;
    @Column(
            name = "departure_city"
    )
    private String departureCity;
    @Column(
            name = "arrival_city"
    )
    private String arrivalCity;
    @DateTimeFormat(
            pattern = "MM-dd-yyyy"
    )
    @Temporal(TemporalType.DATE)
    private Date dateOfDeparture;
    @Column(
            name = "estimated_departure_time"
    )
    private Timestamp estimatedDepartureTime;

    public Flight() {
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOperatingAirlines() {
        return this.operatingAirlines;
    }

    public void setOperatingAirlines(String operatingAirlines) {
        this.operatingAirlines = operatingAirlines;
    }

    public String getDepartureCity() {
        return this.departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return this.arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Date getDateOfDeparture() {
        return this.dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public Timestamp getEstimatedDepartureTime() {
        return this.estimatedDepartureTime;
    }

    public void setEstimatedDepartureTime(Timestamp estimatedDepartureTime) {
        this.estimatedDepartureTime = estimatedDepartureTime;
    }

    public String toString() {
        return "Flight{flightNumber='" + this.flightNumber + "', operatingAirlines='" + this.operatingAirlines + "', departureCity='" + this.departureCity + "', arrivalCity='" + this.arrivalCity + "', dateOfDeparture=" + this.dateOfDeparture + ", estimatedDepartureTime=" + this.estimatedDepartureTime + "}";
    }
}