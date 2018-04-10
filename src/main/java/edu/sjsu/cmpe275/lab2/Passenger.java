package edu.sjsu.cmpe275.lab2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "PASSENGER")
public class Passenger {
    @Id
    @Column(name = "id", unique=true)
    @JsonProperty("id")
    private String passengerId;

    @JsonProperty("firstname")
    @Column(name = "First_Name", nullable = false)
    private String firstname;

    @JsonProperty("lastname")
    @Column(name = "Last_Name", nullable = false)
    private String lastname;

    @Column(name = "Passenger_Age", nullable = false)
    private int age;

    @Column(name = "Gender", nullable = false)
    private String gender;


    @Column(name = "Passenger_Phone", nullable = false, unique = true)
    private String phone;//phone number must be unique

    @OneToMany(mappedBy = "passengers")
    @JsonProperty("reservations")
    private List<Reservation> reservation = new LinkedList<>();

    @ManyToMany(mappedBy = "passengers")
    @JsonProperty("flights")
    private List<Flight> flight = new LinkedList<>();

    @PrePersist
    public void setUniqueId() {
        this.passengerId = UUID.randomUUID().toString();
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Reservation> getReservation() {
        return reservation;
    }

    @JsonProperty(value = "reservations")
    public Map<String, List<Reservation>> getReservations() {
        Map<String, List<Reservation>> map = new HashMap<>();
        map.put("reservation", getReservation());
        return map;
    }

    @JsonProperty(value = "flights")
    public Map<String, List<Flight>> getFlights() {
        Map<String, List<Flight>> map = new HashMap<>();
        map.put("flight", getFlight());
        return map;
    }

    public void setReservation(List<Reservation> reservations) {
        this.reservation = reservations;
    }

    public List<Flight> getFlight() {
        return flight;
    }

    public void setFlight(List<Flight> flights) {
        this.flight = flights;
    }

}