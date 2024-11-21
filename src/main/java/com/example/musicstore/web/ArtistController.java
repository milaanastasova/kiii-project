package com.example.musicstore.web;


import com.example.musicstore.model.Artist;
import com.example.musicstore.model.dto.ArtistDto;
import com.example.musicstore.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping({"/artists", "/"})
    public String getArtists(Model model) {
        List<Artist> artists = artistService.findAll();
        model.addAttribute("artists", artists);
        model.addAttribute("body", "artists");
        return "layout";
    }

    @GetMapping("/add-artist")
    public String showAddArtistForm(Model model) {
        model.addAttribute("artistDto", new ArtistDto());
        model.addAttribute("formAction", "/add-artist");

        model.addAttribute("body", "artist-form");
        return "layout";
    }

    @PostMapping("/add-artist")
    public String addArtist(@ModelAttribute ArtistDto artistDto) {
        artistService.save(artistDto);
        return "redirect:/artists";
    }

    @GetMapping("/edit-artist/{id}")
    public String showEditArtistForm(@PathVariable String id, Model model) {
        Artist artist = artistService.findById(id);
        ArtistDto artistDto = new ArtistDto(artist.getName(), artist.getImage());
        model.addAttribute("artistDto", artistDto);
        model.addAttribute("formAction", "/edit-artist/" + id);

        model.addAttribute("body", "artist-form");
        return "layout";
    }

    @PostMapping("/edit-artist/{id}")
    public String editArtist(@PathVariable String id, @ModelAttribute ArtistDto artistDto) {
        artistService.update(id, artistDto);
        return "redirect:/artists";
    }


    @GetMapping("/delete-artist/{id}")
    public String deleteArtist(@PathVariable String id) {
        artistService.delete(id);
        return "redirect:/artists";
    }

}
