package org.example.repository.impl;

import org.example.entity.Track;
import org.example.repository.TrackRepository;

import java.util.ArrayList;

public class TrackRepositoryImpl implements TrackRepository {

    ArrayList<Track> tracks = new ArrayList<>();

    @Override
    public void save(Track track) {
        tracks.add(track);
        System.out.println("Tracks: " + tracks.size());
    }

    @Override
    public void delete(Track track) {
        tracks.remove(track);
    }

    @Override
    public boolean exists(Track track) {
        return tracks.contains(track);
    }

    @Override
    public ArrayList<Track> getTracks() {
        return tracks;
    }


}
