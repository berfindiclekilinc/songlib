package berfin.songlib.services;

import berfin.songlib.entities.Playlist;
import berfin.songlib.entities.Song;
import berfin.songlib.mappers.LastFmResponse;
import berfin.songlib.mappers.TrackToSongMapper;
import berfin.songlib.repositories.PlaylistRepo;
import berfin.songlib.repositories.SongRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class PlaylistService {

    @Value("${lastfm.baseurl}")
    private String baseUrl;

    @Value("${lastfm.apikey}")
    private String apiKey;

    private final SongRepo songRepo;
    private final PlaylistRepo playlistRepo;


    public PlaylistService(SongRepo songRepo, PlaylistRepo playlistRepo) {
        this.songRepo = songRepo;
        this.playlistRepo = playlistRepo;
    }

    public String createPlaylist(String name){
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlistRepo.save(playlist);
        return ("Playlist created.");
    }

    public String saveToPlaylist(String artist, String track, String playlist) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                    .queryParam("method", "track.getInfo")
                    .queryParam("api_key", apiKey)
                    .queryParam("artist", artist)
                    .queryParam("track", track)
                    .queryParam("format", "json")
                    .build()
                    .toUri();

            String response = restTemplate.getForObject(uri, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            LastFmResponse lastFmResponse = objectMapper.readValue(response, LastFmResponse.class);
            Song song = TrackToSongMapper.mapToSongEntity(lastFmResponse.getTrack());

            songRepo.save(song);

            Playlist existingPlaylist = playlistRepo.findByName(playlist);

            if (existingPlaylist != null) {
                existingPlaylist.getSongs().add(song);
                playlistRepo.save(existingPlaylist);
                return "Song added to the playlist.";
            } else {
                return "Playlist not found.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String removeFromPlaylist(String artist, String track, String playlist) {
        try {
            Playlist existingPlaylist = playlistRepo.findByName(playlist);
            if (existingPlaylist != null) {
                Song songToRemove = songRepo.findByArtistAndTitle(artist, track);
                if (songToRemove != null) {
                    existingPlaylist.getSongs().remove(songToRemove);
                    playlistRepo.save(existingPlaylist);
                    return "Song removed from the playlist.";
                } else {
                    return "Song not found in the playlist.";
                }
            } else {
                return "Playlist not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPlaylistSongs(String playlistName) {
        Playlist playlist = playlistRepo.findByName(playlistName);
        if (playlist != null) {
            return (playlist.getSongs().toString());
        } else {
            throw new RuntimeException("Playlist not found");
        }
    }
}

