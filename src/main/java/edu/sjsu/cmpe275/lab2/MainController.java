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

    @RequestMapping(path="/passenger", method = RequestMethod.POST)
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

	@RequestMapping(value = "passenger/{id}", method = RequestMethod.DELETE)
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

//    @GetMapping(path = "/reservation/add")
//    public @ResponseBody String addReservation(@RequestParam Long id){
//        Reservation reservation = new Reservation();
//        Passenger passenger = passengerRepository.findByPassengerId(id);
//        reservation.setPassenger(passenger);
//        reservationRepository.save(reservation);
//        for(int i = 0; i < 2; ++i){
//            //should add reservation to flight/repository && flight to passenger repository)
//        }
//
//        return "Saved";
//    }

    @GetMapping(path = "/passenger/all")
    public @ResponseBody Iterable<Passenger> getAllPassenger(){
        return passengerRepository.findAll();
    }

    @GetMapping(path = "/get")
    public @ResponseBody Passenger findById(@RequestParam String id) {
        Optional<Passenger> find_result = passengerRepository.findById(id);

        try {
            return find_result.get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Passenger ID cannot be found.");
        }
    }


}