package com.example.musicstore.web.api;

import com.example.musicstore.model.Artist;
import com.example.musicstore.model.dto.ArtistDto;
import com.example.musicstore.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistRestController {

    private final ArtistService artistService;

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody ArtistDto artist
    ) {
        return ResponseEntity.ok(artistService.save(artist));
    }

    @GetMapping
    public ResponseEntity<List<Artist>> findAll() {
        return ResponseEntity.ok(artistService.findAll());
    }

    @GetMapping("/{artist-id}")
    public ResponseEntity<Artist> findById(
            @PathVariable("artist-id") String artistId
    ) {
        return ResponseEntity.ok(artistService.findById(artistId));
    }

    @DeleteMapping("/{artist-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("artist-id") String artistId
    ) {
        artistService.delete(artistId);
        return ResponseEntity.accepted().build();
    }
}
