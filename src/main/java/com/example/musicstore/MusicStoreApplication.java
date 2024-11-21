package com.example.musicstore;

import com.example.musicstore.model.Artist;
import com.example.musicstore.repository.ArtistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusicStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicStoreApplication.class, args);
    }
}
