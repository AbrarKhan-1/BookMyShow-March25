package com.example.bookmyshowmarch2025.services;

import com.example.bookmyshowmarch2025.models.Show;
import com.example.bookmyshowmarch2025.repositories.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {

    private ShowRepository showRepository;

    public ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Override
    public Optional<Show> findById(int showId) {
        return this.showRepository.findById(showId);
    }

}
