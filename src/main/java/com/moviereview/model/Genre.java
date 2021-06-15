package com.moviereview.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor

public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "genre")
    @Getter
    @Setter
    private List<Movie> movies = new ArrayList<>();

    public Genre(String name) {
        this.name = name;
    }
}
