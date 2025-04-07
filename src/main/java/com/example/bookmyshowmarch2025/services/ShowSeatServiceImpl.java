package com.example.bookmyshowmarch2025.services;

import com.example.bookmyshowmarch2025.models.SeatStatus;
import com.example.bookmyshowmarch2025.models.Show;
import com.example.bookmyshowmarch2025.models.ShowSeat;
import com.example.bookmyshowmarch2025.repositories.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSeatServiceImpl implements ShowSeatService {

    private ShowSeatRepository showSeatRepository;

    public ShowSeatServiceImpl(ShowSeatRepository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    public List<ShowSeat> getAvailableSeatsForShow(List<Integer> showSeatIds, Show show) {
        return showSeatRepository.findByIdInAndSeatStatusAndShow(showSeatIds, SeatStatus.AVAILABLE, show);
    }

    @Override
    public void updateShowSeats(List<ShowSeat> showSeats) {
        showSeatRepository.saveAll(showSeats);
    }

}
