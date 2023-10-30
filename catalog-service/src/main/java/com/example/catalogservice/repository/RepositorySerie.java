package com.example.catalogservice.repository;

import com.example.catalogservice.models.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorySerie extends MongoRepository<Serie, Long> {
}
