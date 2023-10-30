package com.example.catalogservice.repository;

import com.example.catalogservice.models.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryMovie extends MongoRepository<Movie, Long> {
}
