package com.example.catalogservice.service.impl;

import com.example.catalogservice.client.IMovieClient;
import com.example.catalogservice.models.Movie;
import com.example.catalogservice.repository.RepositoryMovie;
import com.example.catalogservice.service.IMovieService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MovieImpl implements IMovieService {

    private final IMovieClient iMovieClient;
    private final RepositoryMovie repositoryMovie;
    private CircuitBreakerRegistry circuitBreakerRegistry;

    public MovieImpl(IMovieClient iMovieClient, RepositoryMovie repositoryMovie) {
        this.iMovieClient = iMovieClient;
        this.repositoryMovie = repositoryMovie;
    }

    @Override
    public String saveMovie(Movie movie) {
        repositoryMovie.save(movie);
        return "Hola soy carola";
    }

    @CircuitBreaker(name = "movies", fallbackMethod = "getMovieEmptyFallback")
    @Retry(name = "movies")
    @Override
    public ResponseEntity<List<Movie>> getMovieByGenre(String genre) {
        log.info("Calling genre...");
        return iMovieClient.getMovieByGenre(genre);
    }

    private List<Movie> getMovieEmptyFallback(CallNotPermittedException exception) {
        return new ArrayList<>();
    }

    public String save(Movie movie) {
        iMovieClient.saveMovie(movie);
        return movie.getName();
    }
}
