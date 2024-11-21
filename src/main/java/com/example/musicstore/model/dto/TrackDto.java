package com.example.musicstore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackDto {
    public String title;
    public int minutes;
    public int seconds;
    public String albumId;
}
