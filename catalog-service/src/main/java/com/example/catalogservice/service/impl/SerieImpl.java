package com.example.catalogservice.service.impl;

import com.example.catalogservice.client.ISerieClient;
import com.example.catalogservice.models.Serie;
import com.example.catalogservice.repository.RepositorySerie;
import com.example.catalogservice.service.ISerieService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class SerieImpl implements ISerieService {

    private final ISerieClient iSerieClient;
    private final RepositorySerie repositorySerie;

    public SerieImpl(ISerieClient iSerieClient, RepositorySerie repositorySerie) {
        this.iSerieClient = iSerieClient;
        this.repositorySerie = repositorySerie;
    }

    @Override
    public List<Serie> findCatalogSerieByGenre(String serie) {
        return null;
    }

    @Override
    public String saveSerie(Serie serie) {
        repositorySerie.save(serie);
        return "Hola soy carola";
    }

    @PostMapping
    public String save(Serie serie) {
        iSerieClient.create(serie);
        return serie.getName();
    }
}
