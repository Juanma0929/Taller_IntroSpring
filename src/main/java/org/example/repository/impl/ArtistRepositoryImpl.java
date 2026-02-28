package org.example.repository.impl;

import org.example.entity.Artist;
import org.example.repository.ArtistRepository;

import java.util.ArrayList;

public class ArtistRepositoryImpl implements ArtistRepository {

    private ArrayList<Artist> artists = new ArrayList<>();

    @Override
    public void save(Artist artist) {
        artists.add(artist);
        System.out.println("Artist saved: " + artists.size());
    }

    @Override
    public void delete(Artist artist){
        artists.remove(artist);
        System.out.println("Artist deleted: " + artists.size());
    }

    @Override
    public boolean exists(Artist artist) {
        return artists.contains(artist);
    }

    @Override
    public ArrayList<Artist> getArtists() {
        return artists;
    }

    @Override
    public Artist findArtist(String name){
        Artist foundArtist = null;
        for (int i = 0; i < artists.size(); i++) {
            if(artists.get(i).getName().equals(name)){
                foundArtist = artists.get(i);
            }
        }
        return foundArtist;
    }


}
