package com.example.catalogservice.service;

import com.example.catalogservice.models.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMovieService {
    String saveMovie(Movie movie);

    ResponseEntity<List<Movie>> getMovieByGenre(String genre);
}
