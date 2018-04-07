package edu.sjsu.cmpe275.lab2;

import javax.persistence.*;
import java.util.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "PASSENGER")
public class Passenger {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Passenger_ID")
    private String id;

    @Column(name = "First_Name")
    private String firstname;

    @Column(name = "Last_Name")
    private String lastname;

    @Column(name = "Passenger_Age")
    private int age;

    @Column(name = "Gender")
    private String gender;


    @Column(name = "Passenger_Phone", unique = true)
    private String phone;//phone number must be unique

    @OneToMany(mappedBy = "passengers")
    private List<Reservation> reservations = new LinkedList<>();

    @ManyToMany(mappedBy = "passengers")
    private List<Flight> flights = new LinkedList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String lastname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations){
        this.reservations = reservations;
    }

    public List<Flight> getFlights(){
        return flights;
    }

    public void getFlights(List<Flight> flights){
        this.flights = flights;
    }

}