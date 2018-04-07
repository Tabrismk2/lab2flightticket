package edu.sjsu.cmpe275.lab2;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @Column(name = "Reservation_Number")
    private String reservationNumber;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "Passenger_ID")
    private Passenger passengers;

    @Column(name = "Price")
    private double price;

    @ManyToMany(mappedBy = "reservations")
    private List<Flight> flights = new LinkedList<>();

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Passenger getPassenger() {
        return passengers;
    }

    public void setPassenger(Passenger passengers) {
        this.passengers = passengers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

}