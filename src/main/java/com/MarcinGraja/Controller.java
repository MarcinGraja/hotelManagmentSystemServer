package com.MarcinGraja;

import com.MarcinGraja.hotel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @PostMapping("/addroom")
    public @ResponseBody Room addRoom(@RequestParam Integer beds, @RequestParam String number){
        Room room = new Room();
        room.setBedsCount(beds);
        room.setNumber(number);
        roomRepository.save(room);
        return roomRepository.save(room);
    }
    @PostMapping("addguest")
    public @ResponseBody Guest addGuest(@RequestParam(name = "firstname") String firstName,
                                        @RequestParam(name = "lastname") String lastName,
                                        @RequestParam(required = false) String email,
                                        @RequestParam(name = "phonenumber", required = false) String phoneNumber,
                                        @RequestParam(name = "roomid") Integer roomID){
        System.err.println(firstName + "\n" + lastName + "\n" + email + "\n" + phoneNumber + "\n" + roomID + "\n");
        Guest guest = new Guest(firstName, lastName, email, phoneNumber);
        Room room = roomRepository.findById(roomID).get();
        room.getGuests().add(guest);
        guestRepository.save(guest);
        roomRepository.save(room);
        return  guest;
    }
    @GetMapping("/rooms")
    public Iterable<Room>  getAllRooms(){
        return roomRepository.findAll();
    }
    @GetMapping("/guests")
    public Iterable<Guest> getAllGuests(){
        return guestRepository.findAll();
    }
    @GetMapping("/reservations")
    public Iterable<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }
}
