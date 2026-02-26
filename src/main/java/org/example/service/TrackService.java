package org.example.service;

import org.example.entity.Track;

import java.util.List;

public interface TrackService {
    void saveTrack(Track track);
    List<Track> getTracksList();
    void deleteTrack(Track track);
}
