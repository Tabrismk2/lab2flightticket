package edu.sjsu.cmpe275.lab2;

import javax.persistence.*;

@Entity
@Table(name = "FLIGHT_RESERVATION")

public class FlightReservation {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "Reservation_Number")
    private String reservationNumber;

    @Column(name = "Flight_Number")
    private String flightNumber;

    public String getFlightNumber(){
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber){
        this.flightNumber = flightNumber;
    }

    public String getReservationNumber(){
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber){
        this.reservationNumber = reservationNumber;
    }

}
