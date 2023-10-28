package com.example.catalogservice.service;

import com.example.catalogservice.models.Serie;

import java.util.List;

public interface ISerieService {

    List<Serie> findCatalogSerieByGenre(String serie);

    String saveSerie(Serie serie);
}
