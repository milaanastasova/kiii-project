package com.example.musicstore.service;

import com.example.musicstore.model.Album;
import com.example.musicstore.model.Track;
import com.example.musicstore.model.dto.TrackDto;
import com.example.musicstore.repository.AlbumRepository;
import com.example.musicstore.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;
    private final AlbumRepository albumRepository;

    public String save(TrackDto trackDto) {
        Album album = albumRepository.findById(trackDto.albumId).orElseThrow();
        Track track = new Track(trackDto.title, trackDto.minutes, trackDto.seconds, album);
        return trackRepository.save(track).getId();
    }

    public String update(String id, TrackDto trackDto) {
        Album album = albumRepository.findById(trackDto.albumId).orElseThrow();
        Track track = trackRepository.findById(id).orElseThrow();
        track.setTitle(trackDto.title);
        track.setMinutes(trackDto.minutes);
        track.setSeconds(trackDto.seconds);
        track.setAlbum(album);

        return trackRepository.save(track).getId();
    }

    public Track findById(String id) {
        return trackRepository.findById(id).orElse(null);
    }

    public List<Track> findAll() {
        return trackRepository.findAll();
    }

    public void delete(String id) {
        trackRepository.deleteById(id);
    }

    public List<Track> findByAlbumId(String albumId) {
        return trackRepository.findByAlbum_Id(albumId);
    }

}
