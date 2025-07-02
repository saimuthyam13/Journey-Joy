package com.journeyjoy.service;

import com.journeyjoy.model.Booking;
import java.util.List;

public interface BookingService {
    Booking saveBooking(Booking booking);
    List<Booking> getAllBookings();
}
