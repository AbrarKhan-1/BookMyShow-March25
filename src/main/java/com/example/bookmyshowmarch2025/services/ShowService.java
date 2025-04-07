package com.example.bookmyshowmarch2025.services;

import com.example.bookmyshowmarch2025.models.Show;

import java.util.Optional;

public interface ShowService {

    public Optional<Show> findById(int showId);

}
