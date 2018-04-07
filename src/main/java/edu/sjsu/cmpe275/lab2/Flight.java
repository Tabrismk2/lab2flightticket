package edu.sjsu.cmpe275.lab2;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "FLIGHT")
public class Flight {
    @Id
    @Column(name = "Flight_Number", unique = true)
    private String flightNumber;//unique

    @Column(name = "Price")
    private double price;

    @Column(name = "Origin")
    private String origin;

    @Column(name = "Destination")
    private String to;

    @Column(name = "DepartureTime")
    private Date departureTime;

    @Column(name = "ArrivalTime")
    private Date arrivalTime;

    @Column(name = "SeatsLeft")
    private int seatsLeft;

    @Column(name = "Description")
    private String description;

    @Embedded
    private Plane plane;

    @ManyToMany
    @JoinTable(
            name = "Flight_Passenger",
            joinColumns = {@JoinColumn(name = "Flight_Number", referencedColumnName="Flight_Number")},
            inverseJoinColumns = {@JoinColumn(name = "Passenger_ID", referencedColumnName = "Passenger_ID")}
    )
    private List<Passenger> passengers = new LinkedList<>();

    @ManyToMany
    @JoinTable(
            name = "Flight_Reservation",
            joinColumns = {@JoinColumn(name = "Flight_Number", referencedColumnName = "Flight_Number")},
            inverseJoinColumns = {@JoinColumn(name = "Reservation_Number", referencedColumnName = "Reservation_Number")}

    )
    private List<Reservation> reservations = new LinkedList<>();


    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime){
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public int getSeatsLeft(){
        return seatsLeft;
    }

    public void setSeatsLeft(int seatsLeft){
        this.seatsLeft = seatsLeft;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Plane getPlane(){
        return plane;
    }

    public void setPlane(Plane plane){
        this.plane = plane;
    }

    public List<Passenger> getPassengers(){
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers){
        this.passengers = passengers;
    }

    public List<Reservation> getReservations() { return reservations;}

    public void setReservations(List<Reservation> reservations ) {
        this.reservations = reservations;
    }

}