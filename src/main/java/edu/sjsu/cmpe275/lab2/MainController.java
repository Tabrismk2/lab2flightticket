package edu.sjsu.cmpe275.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.sql.Date;




@RestController    // This means that this class is a Controller
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FlightPassengerRepository flightPassengerRepository;

    @Autowired
    private FlightReservationRepository flightReservationRepository;

    @RequestMapping(path="/passenger", method = RequestMethod.POST ) // Create Passenger API
    public @ResponseBody ResponseEntity addNewPassenger (@RequestParam String firstname,
                                                 @RequestParam String lastname,
                                                 @RequestParam int age,
                                                 @RequestParam String gender,
                                                 @RequestParam String phone) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Passenger passenger = new Passenger();
        passenger.setFirstname(firstname);
        passenger.setLastname(lastname);
        passenger.setAge(age);
        passenger.setGender(gender);
        passenger.setPhone(phone);

        try {
            passengerRepository.save(passenger);
            return new ResponseEntity<Passenger>(passenger, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Another passenger with the same number already exists.");
            return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/reservation", method = RequestMethod.POST) // Create Reservation API
    public @ResponseBody String addNewReservation(@RequestParam String id){
        Reservation reservation = new Reservation();
        Passenger passenger = passengerRepository.findById(id).get();
        reservation.setPassenger(passenger);
        reservationRepository.save(reservation);
        int i = 0;
        do{
            FlightReservation flightReservation = new FlightReservation();
            flightReservation.setFlightNumber(String.valueOf(i));
            flightReservation.setReservationNumber(reservation.getReservationNumber());
            flightReservationRepository.save(flightReservation);
            FlightPassenger flightPassenger = new FlightPassenger();
            flightPassenger.setFlightNumber(String.valueOf(i));
            flightPassenger.setPassengerID(id);
            flightPassengerRepository.save(flightPassenger);
        }
        while(++i < 2);

        return "Saved";
    }

    @RequestMapping(path="/flight/flightnumber", method = RequestMethod.POST)
    public @ResponseBody String addNewFlight(@RequestParam double price,
                                             @RequestParam String origin,
                                             @RequestParam String to,
                                             @RequestParam String description,
                                             @RequestParam int capacity,
                                             @RequestParam String model,
                                             @RequestParam String manufacturer,
                                             @RequestParam int year){
        Flight flight = new Flight();
        Random rand = new Random();
        int randomNum = rand.nextInt(5000)+1;

        flight.setFlightNumber(Integer.toString(randomNum));
        flight.setArrivalTime(Date.valueOf("1990-03-09"));
        flight.setDepartureTime(Date.valueOf("1990-03-09"));
        flight.setDescription(description);
        flight.setOrigin(origin);
        flight.setTo(to);
        flight.setPrice(price);
        Plane plane = new Plane();
        plane.setCapacity(capacity);
        plane.setManufacturer(manufacturer);
        plane.setModel(model);
        plane.setYear(year);
        flight.setPlane(plane);
        flightRepository.save(flight);
        return "Saved";
    }

    @GetMapping(path = "/passenger/all")
    public @ResponseBody Iterable<Passenger> getAllPassenger(){
        return passengerRepository.findAll();
    }

    @GetMapping(path = "/passenger/{id}")
    public @ResponseBody Passenger findById(@RequestParam String id) {
        Optional<Passenger> find_result = passengerRepository.findById(id);

        try {
            return find_result.get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Passenger ID cannot be found.");
        }
    }

    @GetMapping(path = "/reservation/{reservationNumber}")
    public @ResponseBody Optional<Reservation> getReservation(@RequestParam String reservationNumber){
        return reservationRepository.findById(reservationNumber);
    }

    @RequestMapping(value = "passenger/{id}", method = RequestMethod.DELETE)//Delete Passenger
    public @ResponseBody ResponseEntity deletePassenger (@PathVariable("id") String passengerId) {
        Optional<Passenger> find_result = passengerRepository.findById(passengerId);

        try {
            Passenger passenger = find_result.get();
            passengerRepository.delete(passenger);
            return new ResponseEntity<Object>("Passenger with id XXX is deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Passenger with id XXX does not exist");
            return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);
        }
    }


}