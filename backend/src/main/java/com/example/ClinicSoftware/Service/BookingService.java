package com.example.ClinicSoftware.Service;

import java.util.List;

import com.example.ClinicSoftware.Entity.Booking;


public interface BookingService
{
    Booking saveBooking(Booking booking);
    List<Booking> getBookings(Long doctorID);
}
