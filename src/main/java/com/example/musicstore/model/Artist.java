package com.example.musicstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "artists")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Artist {
    @Id
    private String id;
    private String name;
    private String image;

    public Artist(String name, String image){
        this.name = name;
        this.image = image;
    }
}
