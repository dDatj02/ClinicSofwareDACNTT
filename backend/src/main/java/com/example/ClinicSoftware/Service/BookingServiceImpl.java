package com.example.ClinicSoftware.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ClinicSoftware.Entity.Booking;
import com.example.ClinicSoftware.Entity.User;
import com.example.ClinicSoftware.Repository.BookingRepository;
import com.example.ClinicSoftware.Repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional

public class BookingServiceImpl implements BookingService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking)
    {
        String user_role = userRepository.findRoleNameByUserId(booking.getUser().getId());
        String doctor_role = userRepository.findRoleNameByUserId(booking.getDoctor().getId());

        if(user_role != null && doctor_role != null)
        {
            if(user_role.equals("ROLE_USER") && doctor_role.equals("ROLE_MANAGER"))
            {
                if(bookingRepository.checkBookingExists(booking.getDate(), booking.getTime(), booking.getDoctor().getId()) > 0)
                {
                    return null;
                }
                else
                {
                    return bookingRepository.save(booking);
                }
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<Booking> getBookings(Long doctorID) 
    {
        return bookingRepository.getDoctorBookings(doctorID);
    }
}
