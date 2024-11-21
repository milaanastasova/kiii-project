package com.example.musicstore.web;

import com.example.musicstore.model.Album;
import com.example.musicstore.model.Artist;
import com.example.musicstore.model.Track;
import com.example.musicstore.model.dto.AlbumDto;
import com.example.musicstore.model.dto.TrackDto;
import com.example.musicstore.service.AlbumService;
import com.example.musicstore.service.ArtistService;
import com.example.musicstore.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final TrackService trackService;

    @GetMapping("/albums")
    public String getAllAlbums(Model model) {
        List<Album> albums = albumService.findAll();
        model.addAttribute("albums", albums);

        model.addAttribute("body", "albums");
        return "layout";
    }

    @GetMapping("/add-album")
    public String showAddAlbumForm(Model model) {
        List<Artist> artists = artistService.findAll();
        model.addAttribute("albumDto", new AlbumDto());
        model.addAttribute("artists", artists);

        model.addAttribute("body", "album-form");
        return "layout";
    }

    @PostMapping("/add-album")
    public String addAlbum(@ModelAttribute AlbumDto albumDto) {
        albumService.save(albumDto);
        return "redirect:/albums";
    }

    @GetMapping("/edit-album/{id}")
    public String showEditAlbumForm(@PathVariable String id, Model model) {
        Album album = albumService.findById(id);
        if (album == null) {
            return "redirect:/albums";
        }

        AlbumDto albumDto = new AlbumDto();
        albumDto.setTitle(album.getTitle());
        albumDto.setImage(album.getImage());
        albumDto.setArtistId(album.getArtist().getId());

        List<Artist> artists = artistService.findAll();
        model.addAttribute("albumDto", albumDto);
        model.addAttribute("albumId", id);
        model.addAttribute("artists", artists);

        model.addAttribute("body", "album-form");
        return "layout";
    }

    @PostMapping("/edit-album/{id}")
    public String editAlbum(@PathVariable String id, @ModelAttribute AlbumDto albumDto) {
        albumService.update(id, albumDto);
        return "redirect:/albums";
    }

    @GetMapping("/delete-album/{id}")
    public String deleteAlbum(@PathVariable String id) {
        albumService.delete(id);
        return "redirect:/albums";
    }

    @GetMapping("/album/{id}/tracks")
    public String getAlbumTracks(@PathVariable String id, Model model) {
        Album album = albumService.findById(id);
        if (album == null) {
            return "redirect:/albums";
        }
        model.addAttribute("album", album);
        model.addAttribute("tracks", trackService.findByAlbumId(id));

        model.addAttribute("body", "album-tracks");
        return "layout";
    }

    @GetMapping("/album/{albumId}/addTrack")
    public String addTrack(@PathVariable String albumId, Model model){
        TrackDto track = new TrackDto();
        track.albumId = albumId;
        model.addAttribute("track", track);

        model.addAttribute("body", "track-form");
        return "layout";
    }

    @GetMapping("/album/editTrack/{trackId}")
    public String editTrack(@PathVariable String trackId, Model model){
        Track track = trackService.findById(trackId);
        TrackDto trackDto = new TrackDto(track.getTitle(), track.getMinutes(),
                track.getSeconds(), track.getAlbum().getId());
        model.addAttribute("track", trackDto);
        model.addAttribute("trackId", trackId);

        model.addAttribute("body", "track-form");
        return "layout";
    }

    @PostMapping("/album/track/")
    public String saveTrack(@RequestParam String title,
                            @RequestParam int minutes,
                            @RequestParam int seconds,
                            @RequestParam String albumId
                            ){
        trackService.save(new TrackDto(title, minutes, seconds, albumId));
        return "redirect:/album/" + albumId + "/tracks";
    }

    @PostMapping("/album/track/{trackId}")
    public String updateTrack(@PathVariable String trackId,
                            @RequestParam String title,
                            @RequestParam int minutes,
                            @RequestParam int seconds,
                            @RequestParam String albumId
    ){
        trackService.update(trackId, new TrackDto(title, minutes, seconds, albumId));
        return "redirect:/album/" + albumId + "/tracks";
    }

    @GetMapping("/album/{albumId}/tracks/{trackId}/delete")
    public String deleteTrack(@PathVariable String albumId, @PathVariable String trackId) {
        trackService.delete(trackId);
        return "redirect:/album/" + albumId + "/tracks";
    }
}
