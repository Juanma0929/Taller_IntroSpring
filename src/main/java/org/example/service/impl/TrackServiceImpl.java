package org.example.service.impl;

import org.example.entity.Artist;
import org.example.entity.Track;
import org.example.repository.ArtistRepository;
import org.example.repository.TrackRepository;
import org.example.service.TrackService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;
    private ArtistRepository artistRepository;


    public TrackServiceImpl(@Qualifier("trackRepositoryImpl")
                            TrackRepository trackRepository,
                            @Qualifier("artistRepositoryImpl")
                            ArtistRepository artistRepository) {
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public void saveTrack(Track track) {
        if(!trackRepository.exists(track)) {
            trackRepository.save(track);
        }
    }

    @Override
    public void deleteTrack(Track track) {
        if (trackRepository.exists(track)){
            trackRepository.delete(track);
        }
    }

    @Override
    public List<Track> getTracksList() {
        return trackRepository.getTracks();
    }
}
