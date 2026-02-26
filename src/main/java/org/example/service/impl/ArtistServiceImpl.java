package org.example.service.impl;

import org.example.entity.Artist;
import org.example.repository.ArtistRepository;
import org.example.service.ArtistService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {


    private ArtistRepository artistRepository;

    public ArtistServiceImpl(@Qualifier("artistRepositoryImpl")
                             ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;


    }

    @Override
    public void saveArtist(Artist artist) {
        if (!artistRepository.exists(artist)) {
            artistRepository.save(artist);
        }
    }

    @Override
    public void deleteArtist(Artist artist) {
        if (artistRepository.exists(artist)){
            artistRepository.delete(artist);
        }
    }

    @Override
    public List<Artist> getArtistList() {
        return artistRepository.getArtists();
    }

    @Override
    public Artist findByName(String name) {
        return artistRepository.findArtist(name);
    }
}
