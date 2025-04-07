package com.example.bookmyshowmarch2025.controllers;

import com.example.bookmyshowmarch2025.dtos.BookTicketRequestDTO;
import com.example.bookmyshowmarch2025.dtos.BookTicketResponseDTO;
import com.example.bookmyshowmarch2025.dtos.Response;
import com.example.bookmyshowmarch2025.exceptions.InvalidBookTicketException;
import com.example.bookmyshowmarch2025.models.Ticket;
import com.example.bookmyshowmarch2025.services.TicketService;

public class TicketController {

    TicketService ticketService;

    public TicketController (TicketService ticketService) {
        this.ticketService = ticketService;
    }

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
