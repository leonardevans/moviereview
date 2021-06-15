package com.moviereview.repo;

import com.moviereview.model.Genre;
import com.moviereview.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {
    List<Movie> findByGenre(Genre genre);
    List<Movie> findByRatingGreaterThanEqual(int rating);
}
