package com.example.bookmyshowmarch2025.repositories;

import com.example.bookmyshowmarch2025.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
