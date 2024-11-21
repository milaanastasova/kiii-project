package com.example.musicstore.service;

import com.example.musicstore.model.Album;
import com.example.musicstore.model.Artist;
import com.example.musicstore.model.Track;
import com.example.musicstore.model.dto.AlbumDto;
import com.example.musicstore.repository.AlbumRepository;
import com.example.musicstore.repository.ArtistRepository;
import com.example.musicstore.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final TrackRepository trackRepository;

    public String save(AlbumDto albumDto) {
        Artist artist = artistRepository.findById(albumDto.artistId).orElseThrow();
        Album album = new Album(albumDto.title, albumDto.image, artist);
        return albumRepository.save(album).getId();
    }

    public String update(String id, AlbumDto albumDto) {
        Artist artist = artistRepository.findById(albumDto.artistId).orElseThrow();
        Album album = albumRepository.findById(id).orElseThrow();
        album.setTitle(albumDto.title);
        album.setImage(albumDto.image);
        album.setArtist(artist);

        return albumRepository.save(album).getId();
    }

    public Album findById(String id) {
        return albumRepository.findById(id).orElse(null);
    }

    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    public void delete(String id) {
        List<Track> tracks = trackRepository.findByAlbum_Id(id);
        tracks.forEach(t -> trackRepository.deleteById(t.getId()));
        albumRepository.deleteById(id);
    }

}
