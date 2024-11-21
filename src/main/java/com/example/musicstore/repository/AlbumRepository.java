package com.example.musicstore.repository;

import com.example.musicstore.model.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AlbumRepository extends MongoRepository<Album, String> {
    List<Album> findByArtist_Id(String id);
}
