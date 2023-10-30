package com.example.serieservice.service;

import com.example.serieservice.model.Serie;
import com.example.serieservice.queue.SerieSender;
import com.example.serieservice.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    private final SerieRepository repository;

    public SerieService(SerieRepository repository, SerieSender serieSender) {
        this.repository = repository;
    }

    public List<Serie> getAll() {
        return repository.findAll();
    }

    public List<Serie> getSeriesBygGenre(String genre) {
        return repository.findAllByGenre(genre);
    }

    public String create(Serie serie) {
        repository.save(serie);
        return serie.getId();
    }
}
