package com.example.bookmyshowmarch2025.repositories;

import com.example.bookmyshowmarch2025.models.SeatStatus;
import com.example.bookmyshowmarch2025.models.Show;
import com.example.bookmyshowmarch2025.models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    List<ShowSeat> findByIdInAndSeatStatusAndShow(List<Integer> showSeatIds, SeatStatus seatStatus, Show show);

}
