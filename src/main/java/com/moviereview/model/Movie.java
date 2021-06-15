package com.moviereview.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Movie {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int releaseYear;

    @Getter
    @Setter
    private int rating;

    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    private String description;

    @Getter
    @Setter
    @ManyToOne
    private Genre genre;


    public Movie(String name, int releaseYear, int rating, String description, Genre genre) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.description = description;
        this.genre = genre;
    }
}
