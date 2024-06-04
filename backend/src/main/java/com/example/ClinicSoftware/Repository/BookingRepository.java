package com.example.ClinicSoftware.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ClinicSoftware.Entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>
{
    //Check if booking in date and time exists
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.date = :date AND b.time = :time AND b.doctor.id = :doctorId")
    int checkBookingExists(@Param("date") Date date, @Param("time") Time time, @Param("doctorId") Long doctorId);

    //Get all bookings of a doctor
    @Query("SELECT b FROM Booking b WHERE b.doctor.id = :doctorId")
    List<Booking> getDoctorBookings(@Param("doctorId") Long doctorId);
}
