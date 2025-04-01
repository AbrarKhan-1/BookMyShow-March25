package com.example.bookmyshowmarch2025.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity (name = "tickets")
public class Ticket extends BaseModel {
    @ManyToOne
    private User user;
    @ManyToOne
    private Show show;
    @ManyToOne
    private Movie movie;
    @OneToMany
    private List<ShowSeat> showSeats;
}
