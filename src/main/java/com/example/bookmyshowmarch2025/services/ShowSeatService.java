package com.example.bookmyshowmarch2025.services;

import com.example.bookmyshowmarch2025.models.Show;
import com.example.bookmyshowmarch2025.models.ShowSeat;

import java.util.List;

public interface ShowSeatService {

    List<ShowSeat> getAvailableSeatsForShow(List<Integer> showSeatIds, Show show);

    void updateShowSeats(List<ShowSeat> showSeats);
}
