package com.example.musicstore.repository;

import com.example.musicstore.model.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtistRepository extends MongoRepository<Artist, String> {
}
