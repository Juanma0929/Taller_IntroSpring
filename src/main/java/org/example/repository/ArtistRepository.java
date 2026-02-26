package org.example.repository;

import org.example.entity.Artist;

import java.util.ArrayList;

public interface ArtistRepository {
    void save(Artist artist);
    void delete(Artist artist);
    boolean exists(Artist artist);
    ArrayList<Artist> getArtists();
    Artist findArtist(String name);
}
