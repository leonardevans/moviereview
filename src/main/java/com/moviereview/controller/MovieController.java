package com.moviereview.controller;

import com.moviereview.dto.MovieRequest;
import com.moviereview.model.Genre;
import com.moviereview.model.Movie;
import com.moviereview.repo.GenreRepo;
import com.moviereview.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Controller
public class MovieController {
    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private GenreRepo genreRepo;


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("movies", movieRepo.findAll());
        model.addAttribute("genres", genreRepo.findAll());
        return"index";
    }

    @GetMapping("/genre/{genreId}")
    public String filterMoviesByGenre(Model model, @PathVariable("genreId") int genreId){
        model.addAttribute("genres", genreRepo.findAll());

        Optional<Genre> optionalGenre = genreRepo.findById(genreId);
        if (optionalGenre.isEmpty())
            return "redirect:/?genre_not_found";

        model.addAttribute("mainGenre", optionalGenre.get());
        model.addAttribute("movies", movieRepo.findByGenre(optionalGenre.get()));
        return "index";
    }

    @GetMapping("/rating/{rating}")
    public String filterMoviesByRating(Model model, @PathVariable("rating") int rating){
        model.addAttribute("genres", genreRepo.findAll());

        model.addAttribute("rating", rating);
        model.addAttribute("movies", movieRepo.findByRatingGreaterThanEqual(rating));
        return "index";
    }

    @GetMapping("/admin/add-movie")
    public String showAddMoviePage(Model model){
        model.addAttribute("genres", genreRepo.findAll());
        model.addAttribute("movieRequest", new MovieRequest());
        return "addMovie";
    }

    @GetMapping("/admin/edit-movie/{movieId}")
    public String showEditMoviePage(Model model, @PathVariable("movieId") int movieId){
        model.addAttribute("genres", genreRepo.findAll());
        //get movie from db
        Optional<Movie> optionalMovie = movieRepo.findById(movieId);

        //check if movie with this id exists
        if(optionalMovie.isEmpty())
            return "redirect:/?movieNotFound";

        //get the movie
        Movie movie = optionalMovie.get();

        MovieRequest movieRequest = new MovieRequest();
        movieRequest.setId(movie.getId());
        movieRequest.setName(movie.getName());
        movieRequest.setDescription(movie.getDescription());
        movieRequest.setGenre(movie.getGenre());
        movieRequest.setRating(movie.getRating());
        movieRequest.setReleaseYear(movie.getReleaseYear());

        model.addAttribute("movieRequest", movieRequest);
        return "editMovie";
    }

    @PostMapping("/admin/add-movie")
    public String addMovie(@ModelAttribute("movieRequest") MovieRequest movieRequest) throws IOException {
        //create new Movie
        Movie movie = new Movie();

        movie.setName(movieRequest.getName());
        movie.setRating(movieRequest.getRating());
        movie.setReleaseYear(movieRequest.getReleaseYear());
        movie.setGenre(movieRequest.getGenre());
        movie.setDescription(movieRequest.getDescription());


        //save the movie
        movieRepo.save(movie);

        return "redirect:/admin/add-movie?added";
    }


    @PostMapping("/admin/update-movie")
    public String updateMovie(@ModelAttribute("movieRequest") MovieRequest movieRequest) throws IOException {
        //create new Movie
        Movie movie = new Movie();

        movie.setId(movieRequest.getId());
        movie.setName(movieRequest.getName());
        movie.setRating(movieRequest.getRating());
        movie.setReleaseYear(movieRequest.getReleaseYear());
        movie.setGenre(movieRequest.getGenre());
        movie.setDescription(movieRequest.getDescription());


        //save the movie
        movieRepo.save(movie);

        return "redirect:/admin/edit-movie/" + movie.getId() +"?updated";
    }


    @GetMapping("/admin/delete-movie/{movieId}")
    public String deleteMovie(@PathVariable("movieId") int movieId) throws IOException {
        //get movie from db
        Optional<Movie> optionalMovie = movieRepo.findById(movieId);

        //check if movie with this id exists
        if(optionalMovie.isEmpty())
            return "redirect:/?movieNotFound";

        //get the movie
        Movie movie = optionalMovie.get();


        //delete the movie
        movieRepo.delete(movie);

        return  "redirect:/?deleted";
    }


}
