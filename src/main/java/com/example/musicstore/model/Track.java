package com.example.musicstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "songs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Track {
    @Id
    private String id;
    private String title;
    private int minutes;
    private int seconds;

    @DBRef
    private Album album;

    public Track(String title, int minutes, int seconds, Album album){
        this.title = title;
        this.minutes = minutes;
        this.seconds = seconds;
        this.album = album;
    }
}
