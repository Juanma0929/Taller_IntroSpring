package org.example.entity;

import java.util.ArrayList;

public class Artist {
    private int id;
    private String name;
    private String nationality;

    private ArrayList<Track> tracksList = new ArrayList<>();

    public ArrayList<Track> getTracks() {
        return tracksList;
    }

    public void addTrack(Track track) {
        tracksList.add(track);
    }
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Artist) {
            Artist other = (Artist) obj;
            return other.getId() == this.getId();
        }
        return false;
    }



}
