package com.projects2024.flightreservation2.DTO;

public class ReservationRequest {
    private String pfirstname;
    private String plastname;
    private String pemail;
    private String pphone;
    private String cardname;
    private String cardnumber;
    private String cardexpiry;
    private String cardsecurity;
    private int flightId;

    public ReservationRequest() {
    }

    public int getFlightId() {
        return this.flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getPfirstname() {
        return this.pfirstname;
    }

    public void setPfirstname(String pfirstname) {
        this.pfirstname = pfirstname;
    }

    public String getPlastname() {
        return this.plastname;
    }

    public void setPlastname(String plastname) {
        this.plastname = plastname;
    }

    public String getPemail() {
        return this.pemail;
    }

    public void setPemail(String pemail) {
        this.pemail = pemail;
    }

    public String getPphone() {
        return this.pphone;
    }

    public void setPphone(String pphone) {
        this.pphone = pphone;
    }

    public String getCardname() {
        return this.cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardnumber() {
        return this.cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCardexpiry() {
        return this.cardexpiry;
    }

    public void setCardexpiry(String cardexpiry) {
        this.cardexpiry = cardexpiry;
    }

    public String getCardsecurity() {
        return this.cardsecurity;
    }

    public void setCardsecurity(String cardsecurity) {
        this.cardsecurity = cardsecurity;
    }

    public String toString() {
        return "ReservationRequest{pfirstname='" + this.pfirstname + "', plastname='" + this.plastname + "', pemail='" + this.pemail + "', pphone='" + this.pphone + "', cardname='" + this.cardname + "', cardnumber='" + this.cardnumber + "', cardexpiry='" + this.cardexpiry + "', cardsecurity='" + this.cardsecurity + "', flightId=" + this.flightId + "}";
    }
}
