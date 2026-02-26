package org.example.service;

import org.example.entity.Artist;

import java.util.List;

public interface ArtistService {
    void saveArtist(Artist artist);
    List<Artist> getArtistList();
    void deleteArtist(Artist artist);
    Artist findByName(String name);
}
