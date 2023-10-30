package com.example.catalogservice.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@RequiredArgsConstructor
public class Serie {

    @MongoId
    private String id;
    private String name;
    private String genre;
//    private List<Serie> seasons = new ArrayList<>();
}
