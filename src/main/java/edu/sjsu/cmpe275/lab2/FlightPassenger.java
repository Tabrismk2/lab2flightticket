package edu.sjsu.cmpe275.lab2;

import javax.persistence.*;

@Entity
@Table(name = "FLIGHT_PASSENGER")

public class FlightPassenger {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "Flight_Number")
    private String flightnumber;

    @Column(name = "Passenger_ID")
    private Long passengerId;

    public void setFlightNumber(String flightnumber){
        this.flightnumber = flightnumber;
    }

    public String getFlightnumber(){
        return flightnumber;
    }

    public void setPassengerID(Long id){
        this.passengerId = passengerId;
    }

    public Long getPassengerID(){
        return passengerId;
    }
}
