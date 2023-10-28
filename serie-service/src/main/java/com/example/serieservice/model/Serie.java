package com.example.serieservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;


@Data
@Document(collection = "Series")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Serie {

    @Id
    @MongoId
    private String id;
    private String name;
    private String genre;
    private List<Season> seasons = new ArrayList<>();

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Season {

        private Integer seasonNumber;
        private List<Chapter> chapters = new ArrayList<>();

        @AllArgsConstructor
        @NoArgsConstructor
        @Setter
        @Getter
        public static class Chapter {

            private String name;
            private Integer number;
            private String urlStream;

        }
    }
}
