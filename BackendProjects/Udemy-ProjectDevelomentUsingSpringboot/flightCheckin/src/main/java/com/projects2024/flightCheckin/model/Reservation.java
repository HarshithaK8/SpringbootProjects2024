package com.projects2024.flightCheckin.model;

import java.sql.Timestamp;

public class Reservation {

    private int id;
    private Boolean checkedIn;

    private int noOfBags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Passenger passenger;

    private Flight flight;

    private Timestamp created;

    public Boolean getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public int getNoOfBags() {
        return noOfBags;
    }

    public void setNoOfBags(int noOfBags) {
        this.noOfBags = noOfBags;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
