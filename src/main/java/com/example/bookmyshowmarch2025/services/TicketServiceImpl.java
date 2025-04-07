package com.example.bookmyshowmarch2025.services;

import com.example.bookmyshowmarch2025.exceptions.InvalidBookTicketException;
import com.example.bookmyshowmarch2025.exceptions.SeatsUnavailableException;
import com.example.bookmyshowmarch2025.models.*;
import com.example.bookmyshowmarch2025.repositories.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private UserService userService;
    private ShowService showService;
    private ShowSeatService showSeatService;

    public TicketServiceImpl(TicketRepository ticketRepository, UserService userService, ShowService showService, ShowSeatService showSeatService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.showService = showService;
        this.showSeatService = showSeatService;
    }

    @Override
    @Transactional (isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(int userId, int showId, List<Integer> showSeatIds) throws InvalidBookTicketException, SeatsUnavailableException {

        /*
        1. Validate user and show IDs (exists in DB)
        2. Validate all show IDs in showSeatIds (list of seats pertaining to a particular show)
           (must match showId, i.e. all seats belong to the same show)
           (also ensures malicious entries where show IDs have been manipulated)
           -- Skipping explicit validation of seat-show mapping since step 3 inherently ensures
              all seats belong to the same show, avoids making N queries to the DB i.e. high latency --
        3. Begin transaction, check if all selected seats are available,
           if not rollback
           {SELECT * FROM show_seats WHERE id IN (showSeatIds) AND seat_status = 'AVAILABLE
           AND show_id = (showId) FOR UPDATE}
        4. Block seats if available, commit transaction,
           generate and save ticket
         */

        // Step 1:
        Optional<User> optionalUser = this.userService.findById(userId);
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else throw new InvalidBookTicketException("Invalid user ID: No user found with ID " + userId);

        Show show = this.showService.findById(showId).orElseThrow(() -> new InvalidBookTicketException("Invalid show ID: No show found with ID " + showId));

        // Step 3:
        List<ShowSeat> showSeats = showSeatService.getAvailableSeatsForShow(showSeatIds, show);
        if(showSeats.size() != showSeatIds.size()) {
            throw new SeatsUnavailableException("Some of the requested are unavailable or do not exist.");
        }

        // Step 4:
        for (ShowSeat showSeat : showSeats) {
            showSeat.setSeatStatus(SeatStatus.BLOCKED);
            showSeat.setUser(user);
        }

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setMovie(show.getMovie());
        ticket.setShowSeats(showSeats);

        return ticketRepository.save(ticket);

    }
}
