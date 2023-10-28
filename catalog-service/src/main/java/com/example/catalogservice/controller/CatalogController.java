package com.example.catalogservice.controller;

import com.example.catalogservice.client.IMovieClient;
import com.example.catalogservice.client.ISerieClient;
import com.example.catalogservice.models.Movie;
import com.example.catalogservice.models.Serie;
import com.example.catalogservice.queue.MovieListener;
import com.example.catalogservice.queue.SerieListener;
import com.example.catalogservice.service.IMovieService;
import com.example.catalogservice.service.ISerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping
public class CatalogController {

    private static java.util.logging.Logger log = Logger.getLogger(CatalogController.class.getName());

    @Autowired
    private IMovieClient iMovieClient;
    private RestTemplate restTemplate;

    private final ISerieClient serieClient;

    private final MovieListener movieListener;
    private final SerieListener serieListener;

    private final IMovieService iMovieService;
    private final ISerieService iSerieService;


    public CatalogController(IMovieClient iMovieClient, RestTemplate restTemplate, ISerieClient serieClient, MovieListener movieListener, SerieListener serieListener, IMovieService iMovieService, ISerieService iSerieService) {
        this.iMovieClient = iMovieClient;
        this.restTemplate = restTemplate;
        this.serieClient = serieClient;
        this.movieListener = movieListener;
        this.serieListener = serieListener;
        this.iMovieService = iMovieService;
        this.iSerieService = iSerieService;
    }

    @GetMapping("catalog/{genre}")
    public ResponseEntity<List<Movie>> getCatalogByGenre(@PathVariable String genre) {
        return iMovieService.getMovieByGenre(genre);
    }

    @GetMapping("/series")
    public List<Serie> getAll() {

        return this.serieClient.getAll();
    }

    @GetMapping("/series/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) {

        return this.serieClient.getSerieByGenre(genre);
    }

    @PostMapping("/series")
    public String create(@RequestBody Serie serie) {

        return this.serieClient.create(serie);
    }

    @PostMapping("movie/{genre}")
    public ResponseEntity<String> obtenerGenero(@RequestBody String genre) {
        log.info("Este es tu genero: " + genre);

        restTemplate.exchange("http://localhost:9092/movie/genero" + genre, HttpMethod.POST,
                new HttpEntity<String>(genre), String.class);

        return ResponseEntity.ok("Consulta exitosa");
    }

    @PostMapping("/movie/save")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
        movieListener.receive(movie);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/serie/save")
    public ResponseEntity<?> saveSerie(@RequestBody Serie serie) {
        serieListener.receive(serie);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/catalog/movie")
    public ResponseEntity<?> saveCatalagoMovie(@RequestBody Movie movie) {
        iMovieService.saveMovie(movie);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/catalog/serie")
    public ResponseEntity<?> saveCatalogSerie(@RequestBody Serie serie) {
        iSerieService.saveSerie(serie);
        return ResponseEntity.noContent().build();
    }
}
