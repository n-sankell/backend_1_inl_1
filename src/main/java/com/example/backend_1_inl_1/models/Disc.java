package com.example.backend_1_inl_1.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Disc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String albumName;
    private String artist;
    private LocalDate releaseDate;
    private String genre;
    private int albumLength;

    public Disc() {
    }

    public Disc(long id, String albumName, String artist, LocalDate releaseDate, String genre, int albumLength) {
        this.id = id;
        this.albumName = albumName;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.albumLength = albumLength;
    }



}
