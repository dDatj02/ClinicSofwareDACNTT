package com.example.ClinicSoftware.Entity;

import java.sql.Date;
import java.sql.Time;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingTemp 
{
    private Date date;
    private Time time;
    private Long user_id;
    private Long doctor_id;

    public BookingTemp(Date date, Time time, Long user_id, Long doctor_id) 
    {
        this.date = date;
        this.time = time;
        this.user_id = user_id;
        this.doctor_id = doctor_id;
    }
}
