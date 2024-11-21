package com.example.musicstore.service;

import com.example.musicstore.model.Album;
import com.example.musicstore.model.Artist;
import com.example.musicstore.model.dto.ArtistDto;
import com.example.musicstore.repository.AlbumRepository;
import com.example.musicstore.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository repository;
    private final AlbumRepository albumRepository;

    public String save(ArtistDto artistDto) {
        Artist artist = new Artist(artistDto.name, artistDto.image);
        return repository.save(artist).getId();
    }

    public Artist findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public String update(String id, ArtistDto artistDto) {
        Artist artist = repository.findById(id).orElseThrow();
        artist.setName(artistDto.name);
        artist.setImage(artistDto.image);
        return repository.save(artist).getId();
    }

    public List<Artist> findAll() {
        return repository.findAll();
    }

    public void delete(String id) {
        List<Album> albums = albumRepository.findByArtist_Id(id);
        albums.forEach(a -> albumRepository.deleteById(a.getId()));
        repository.deleteById(id);
    }

}
