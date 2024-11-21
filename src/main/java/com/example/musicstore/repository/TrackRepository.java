package com.example.musicstore.repository;

import com.example.musicstore.model.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TrackRepository extends MongoRepository<Track, String> {
    List<Track> findByAlbum_Id(String albumId);
}
