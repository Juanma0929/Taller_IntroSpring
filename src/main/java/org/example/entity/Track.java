package org.example.entity;

import java.util.ArrayList;

public class Track  {

    private int id;
    private String title;
    private String genre;
    private int duration;
    private String albumTitle;

    private ArrayList<Artist> artistsList = new ArrayList<>();

    public ArrayList<Artist> getArtists() {
        return artistsList;
    }

    public void addArtists(Artist artist) {
        artistsList.add(artist);
        if(!artist.getTracks().contains(this)) {
            artist.getTracks().add(this);
        }
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Track) {
            Track other = (Track) obj;
            return other.getId() == this.getId();
        }
        return false;
    }
}

