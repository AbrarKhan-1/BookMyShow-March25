package com.example.bookmyshowmarch2025.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity (name = "show_seats")
public class ShowSeat extends BaseModel {

    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    private SeatStatus seatStatus;

    @ManyToOne
    private User user;

    @ManyToOne
    private Ticket ticket;

}
