package com.example.bookmyshowmarch2025.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity (name = "shows")
public class Show extends BaseModel {

    @ManyToOne
    private Screen screen;

    @ManyToOne
    private Movie movie;

    private Date startTime;
}
