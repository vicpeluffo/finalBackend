package com.example.catalogservice.client;

import com.example.catalogservice.config.ProjectLoadBalancerConfiguration;
import com.example.catalogservice.models.Serie;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "serie-service")
@LoadBalancerClient(name = "serie-service", configuration= ProjectLoadBalancerConfiguration.class)
public interface ISerieClient {

    @GetMapping("/api/v1/series")
    List<Serie> getAll();

    @GetMapping("/api/v1/series/{genre}")
    List<Serie> getSerieByGenre(@PathVariable String genre);

    @PostMapping("/api/v1/series")
    String create(@RequestBody Serie serie);
}
