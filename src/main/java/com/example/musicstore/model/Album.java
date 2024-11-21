package com.example.musicstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "albums")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Album {
    @Id
    private String id;
    private String title;
    private String image;

    @DBRef
    private Artist artist;

    public Album(String title, String image, Artist artist){
        this.title = title;
        this.image = image;
        this.artist = artist;
    }


}
