package com.example.bookmyshowmarch2025.services;

import com.example.bookmyshowmarch2025.models.Ticket;

import java.util.List;

public interface TicketService {

    public Ticket bookTicket(int userId, int showId, List<Integer> showSeatIds) throws Exception;

}
