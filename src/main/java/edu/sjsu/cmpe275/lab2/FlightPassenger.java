package edu.sjsu.cmpe275.lab2;

import javax.persistence.*;

@Entity
@Table(name = "FLIGHT_PASSENGER")

public class FlightPassenger {



    @Column(name = "Flight_Number")
    private String flightnumber;

    @Id
    @Column(name = "id")
    private Long id;

    public void setFlightNumber(String flightnumber){
        this.flightnumber = flightnumber;
    }

    public String getFlightnumber(){
        return flightnumber;
    }

    public void setPassengerID(Long id){
        this.id = id;
    }

    public Long getPassengerID(){
        return id;
    }
}