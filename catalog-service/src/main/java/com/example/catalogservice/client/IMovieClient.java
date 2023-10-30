package com.example.catalogservice.client;

import com.example.catalogservice.config.CustomLoadBalancerConfiguration;
import com.example.catalogservice.models.Movie;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "movie-service")
@LoadBalancerClient(name = "movie-service", configuration= CustomLoadBalancerConfiguration.class)
public interface IMovieClient {
    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre);

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie);
}
