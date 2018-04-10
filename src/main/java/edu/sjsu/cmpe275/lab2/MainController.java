package edu.sjsu.cmpe275.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import java.text.NumberFormat;
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

    @RequestMapping(path="/passenger", method = RequestMethod.POST ) // CreatePassenger API
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

    @RequestMapping(path = "/passenger/{id}", method = RequestMethod.GET) //GetPassengerById API
    public @ResponseBody ResponseEntity getPassengerById(@PathVariable("id") String passengerId) {
        Optional<Passenger> find_result = passengerRepository.findById(passengerId);

        try {
            Passenger passenger = find_result.get();
            return new ResponseEntity<Passenger>(passenger, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Passenger id cannot be found");
            return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "passenger/{id}", method = RequestMethod.PUT)//UpdatePassenger API
    public @ResponseBody ResponseEntity updatePassenger (@PathVariable("id") String passengerId,
                                                         @RequestParam String firstname,
                                                         @RequestParam String lastname,
                                                         @RequestParam int age,
                                                         @RequestParam String gender,
                                                         @RequestParam String phone){
        Optional<Passenger> find_result = passengerRepository.findById(passengerId);
        try{

                Passenger passenger = find_result.get();
                passenger.setFirstname(firstname);
                passenger.setLastname(lastname);
                passenger.setAge(age);
                passenger.setGender(gender);
                passenger.setPhone(phone);

                passengerRepository.save(passenger);
                return new ResponseEntity<Passenger>(passenger, HttpStatus.OK);
        } catch(NumberFormatException e){
                ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "WRONG Parameter Type");
                return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
            } catch(NoSuchElementException e){
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Passenger with id XXX does not exist");
            return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);
        }
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

//    @RequestMapping(path="/reservation", method = RequestMethod.POST) // Create Reservation API
//    public @ResponseBody ResponseEntity addNewReservation(@RequestParam String passengerId
//                                                          @RequestParam("flightLists") List<Flight> flightLists){
//        Reservation reservation = new Reservation();
//        Passenger passenger = passengerRepository.findById(id).get();
//        reservation.setPassenger(passenger);
//        //reservation.setFlights(List<flight>);
//        reservationRepository.save(reservation);
//        int i = 0;
//        do{
//            FlightReservation flightReservation = new FlightReservation();
//            flightReservation.setFlightNumber(String.valueOf(i));
//            flightReservation.setReservationNumber(reservation.getReservationNumber());
//            flightReservationRepository.save(flightReservation);
//            FlightPassenger flightPassenger = new FlightPassenger();
//            flightPassenger.setFlightNumber(String.valueOf(i));
//            flightPassenger.setPassengerID(id);
//            flightPassengerRepository.save(flightPassenger);
//        }
//        while(++i < 2);
//
//        return "Saved";
//    }

    @RequestMapping(path="/flight/{flightNumber}", method = RequestMethod.POST) //Create/UpdateFlight API
    public @ResponseBody ResponseEntity addNewFlight(@PathVariable String flightNumber,
                                                     @RequestParam double price,
                                                     @RequestParam String origin,
                                                     @RequestParam String to,
                                                     @RequestParam String departureTime,
                                                     @RequestParam String arrivalTime,
                                                     @RequestParam String description,
                                                     @RequestParam int capacity,
                                                     @RequestParam String model,
                                                     @RequestParam String manufacturer,
                                                     @RequestParam int year){
        Flight flight = new Flight();

        flight.setFlightNumber(flightNumber);
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
        try{
            flightRepository.save(flight);
            return new ResponseEntity<Flight>(flight, HttpStatus.OK);
        }catch(NumberFormatException e){
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Error 400");
            return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);

        }catch(NoSuchElementException e){
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Error 404 ");
            return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/flight/{flightNumber}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getFlightByFlightNumber(@PathVariable("flightNumber") String flightNumber){
        Optional<Flight> find_result = flightRepository.findById(flightNumber);

        try {
            Flight flight = find_result.get();
            return new ResponseEntity<Flight>(flight, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Flight Number cannot be found");
            return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/flight/{flightNumber}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity deleteFlightByFlightNumber(@PathVariable("flightNumber") String flightNumber){
        Optional<Flight> find_result = flightRepository.findById(flightNumber);
        try {
            Flight flight = find_result.get();
            flightRepository.delete(flight);
            return new ResponseEntity<Object>("Flight with flightnumber XXX is deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Flight with id XXX does not exist");
            return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);
        }
    }



}