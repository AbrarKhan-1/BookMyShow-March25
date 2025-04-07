package com.example.bookmyshowmarch2025.controllers;

import com.example.bookmyshowmarch2025.dtos.BookTicketRequestDTO;
import com.example.bookmyshowmarch2025.dtos.BookTicketResponseDTO;
import com.example.bookmyshowmarch2025.dtos.Response;
import com.example.bookmyshowmarch2025.exceptions.InvalidBookTicketException;
import com.example.bookmyshowmarch2025.models.Ticket;
import com.example.bookmyshowmarch2025.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketController {

    TicketService ticketService;

    @Autowired
    public TicketController (TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(path = "/bookTicket")
    public BookTicketResponseDTO bookTicket(BookTicketRequestDTO bookTicketRequestDTO) {
        BookTicketResponseDTO bookTicketResponseDTO = new BookTicketResponseDTO();

        try {
            validateBookTicketRequest(bookTicketRequestDTO);
            Ticket ticket = ticketService.bookTicket(
                    bookTicketRequestDTO.getUserId(),
                    bookTicketRequestDTO.getShowId(),
                    bookTicketRequestDTO.getShowSeatIds());

            Response response = Response.setSuccessResponse();
            bookTicketResponseDTO.setTicket(ticket);
            bookTicketResponseDTO.setResponse(response);

        } catch (Exception e) {
            Response response = Response.setFailureResponse(e.getMessage());
            bookTicketResponseDTO.setResponse(response);
        }

        return bookTicketResponseDTO;
    }

    private void validateBookTicketRequest(BookTicketRequestDTO bookTicketRequestDTO) throws InvalidBookTicketException {
        if (bookTicketRequestDTO == null) {
            throw new InvalidBookTicketException("Request cannot be null.");
        }
        if (bookTicketRequestDTO.getUserId() <= 0) {
            throw new InvalidBookTicketException("Invalid user ID: must be a positive number.");
        }
        if (bookTicketRequestDTO.getShowId() <= 0) {
            throw new InvalidBookTicketException("Invalid show ID: must be a positive number.");
        }
        if (bookTicketRequestDTO.getShowSeatIds() == null || bookTicketRequestDTO.getShowSeatIds().isEmpty()) {
            throw new InvalidBookTicketException("At least one seat must be selected");
        }
    }
}
