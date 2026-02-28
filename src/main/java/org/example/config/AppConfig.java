package org.example.config;

import org.example.entity.Artist;
import org.example.entity.Track;
import org.example.service.ArtistService;
import org.example.service.TrackService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example")
public class AppConfig {

    @Bean
    public String dataInitializer(ArtistService artistService, TrackService trackService) {
        String[] artistNames = {
            "The Beatles", "Led Zeppelin", "Pink Floyd", "Queen", "Metallica",
            "Nirvana", "AC/DC", "The Rolling Stones", "David Bowie", "Radiohead"
        };
        String[] nationalities = {
            "British", "British", "British", "British", "American",
            "American", "Australian", "British", "British", "British"
        };

        String[] genres = {"Rock", "Pop", "Jazz", "Electronic", "Classical", "Blues", "Metal", "Reggae", "Hip-Hop", "Country"};
        String[] albums = {"Album A", "Album B", "Album C", "Album D", "Album E"};

        Artist[] artists = new Artist[10];
        for (int i = 0; i < 10; i++) {
            Artist a = new Artist();
            a.setId(i + 1);
            a.setName(artistNames[i]);
            a.setNationality(nationalities[i]);
            artistService.saveArtist(a);
            artists[i] = a;
        }

        Track[] tracks = new Track[50];
        for (int i = 0; i < 50; i++) {
            Track t = new Track();
            t.setId(i + 1);
            t.setTitle("Track " + (i + 1));
            t.setGenre(genres[i % genres.length]);
            t.setDuration(180 + (i * 5));
            t.setAlbumTitle(albums[i % albums.length]);
            trackService.saveTrack(t);
            tracks[i] = t;
        }

        // Asignar 5 tracks a cada artista (tracks 0-4 -> artista 0, tracks 5-9 -> artista 1, etc.)
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                tracks[i * 5 + j].addArtists(artists[i]);
            }
        }

        return "data-initialized";
    }
}
