package com.example.bookmyshowmarch2025.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity (name = "show_seat_types")
public class ShowSeatType extends BaseModel {

    @ManyToOne
    private Show show;

    private SeatType seatType;

    private double price;
}
