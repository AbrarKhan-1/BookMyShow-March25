package com.example.bookmyshowmarch2025.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity (name = "movies")
public class Movie extends BaseModel {

    private String title;

    private Genre genre;
}
