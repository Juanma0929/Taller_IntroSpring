package org.example.repository;

import org.example.entity.Track;

import java.util.ArrayList;

public interface TrackRepository {
    void save(Track track);
    boolean exists(Track track);
    void delete(Track track);
    ArrayList<Track> getTracks();
}
