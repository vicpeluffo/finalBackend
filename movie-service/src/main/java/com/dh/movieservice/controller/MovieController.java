package com.dh.movieservice.controller;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.queue.MovieSender;
import com.dh.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;
    private final MovieSender movieSender;

    public static java.util.logging.Logger log = Logger.getLogger(MovieController.class.getName());

    @Value("${idRandom}")
    private String serverPort;

    public MovieController(MovieService movieService, MovieSender movieSender) {
        this.movieService = movieService;
        this.movieSender = movieSender;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre, HttpServletResponse response) {
        response.addHeader("port", String.valueOf(serverPort));
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        movieSender.send(movie);
        return ResponseEntity.ok().body(movieService.save(movie));
    }

    @PostMapping("validar/{genre}")
    public ResponseEntity<String> validarGenero(@RequestBody String genre){
        log.info("Validando: " +genre);
        return ResponseEntity.ok("Genero validado");
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        log.info("TEST");
        return ResponseEntity.ok("Consulta exitosa");
    }

    @GetMapping("/findAll")
    public List<Movie> findAll(@RequestParam(defaultValue = "false") Boolean throwError) {
        return movieService.findAll(throwError);
    }
}