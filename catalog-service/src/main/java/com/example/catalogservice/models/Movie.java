package com.example.catalogservice.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
@RequiredArgsConstructor
public class Movie {

    @MongoId
    private String id;
    private String name;
    private String genre;
    private String urlStream;
}
