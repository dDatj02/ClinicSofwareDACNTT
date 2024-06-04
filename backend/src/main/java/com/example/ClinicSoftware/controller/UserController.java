package com.example.ClinicSoftware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ClinicSoftware.Entity.Booking;
import com.example.ClinicSoftware.Entity.BookingTemp;
import com.example.ClinicSoftware.Entity.User;
import com.example.ClinicSoftware.Repository.UserRepository;
import com.example.ClinicSoftware.Service.BookingService;
import com.example.ClinicSoftware.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController 
{
    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register") // http://localhost:8080/users/register (Use by register page)
    ResponseEntity<String> addUser(@RequestBody User user)
    {
        var userRegister = userService.saveUser(user);
        if(userRegister == null)
            return ResponseEntity.badRequest().body("User is already registered");
        userService.addRoleToUser(user.getEmail(), "ROLE_USER");
        return ResponseEntity.ok("User added successfully");
    }

    @PostMapping("/addDoctor") // http://localhost:8080/users/addDoctor (Use by admin page)
    ResponseEntity<String> addDoctor(@RequestBody User user)
    {
        var addDoctorStatus = userService.saveUser(user);
        if(addDoctorStatus == null)
            return ResponseEntity.badRequest().body("Doctor addition failed");
        userService.addRoleToUser(user.getEmail(), "ROLE_MANAGER");
        return ResponseEntity.ok("Doctor added successfully");
    }

    @PostMapping("/userBooking") // http://localhost:8080/users/userBooking (Use by user page)
    ResponseEntity<String> userBooking(@RequestBody BookingTemp bookingTemp)
    {
        User user = userRepository.findById(bookingTemp.getUser_id()).orElseThrow(() -> new RuntimeException("User not found"));
        User doctor = userRepository.findById(bookingTemp.getDoctor_id()).orElseThrow(() -> new RuntimeException("Doctor not found"));
        Booking booking = new Booking(bookingTemp.getDate(), bookingTemp.getTime(), user, doctor);
        var bookingStatus = bookingService.saveBooking(booking);
        if(bookingStatus == null)
            return ResponseEntity.badRequest().body("Booking failed");
        return ResponseEntity.ok("Booking successful");
    }

    @GetMapping("/getDoctors") // http://localhost:8080/users/getDoctors
    List<User> getDoctors()
    {
        return userService.getUsersDoctor();
    }

    @GetMapping("/getBookingDoctor/{doctorID}") // http://localhost:8080/users/getBookingDoctor/{doctorID}
    List<Booking> getBookingDoctors(@PathVariable Long doctorID)
    {
        return bookingService.getBookings(doctorID);
    }

    @GetMapping("/{userID}")
    User getUser(@PathVariable String userID) // http://localhost:8080/users/{userID}
    {
        return userService.getUser(userID);
    }

}
