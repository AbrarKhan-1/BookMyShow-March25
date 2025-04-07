package com.example.bookmyshowmarch2025.dtos;

import com.example.bookmyshowmarch2025.models.Ticket;
import lombok.Data;

@Data
public class BookTicketResponseDTO {

    private Response response;
    private Ticket ticket;
}
