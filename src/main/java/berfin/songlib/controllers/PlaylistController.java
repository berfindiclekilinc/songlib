package berfin.songlib.controllers;

import berfin.songlib.services.LastFmService;
import berfin.songlib.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
public class PlaylistController {

    private PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping("/create-playlist")
    public String createPlaylist(String name){
        return playlistService.createPlaylist(name);
    }

    @PostMapping("/save-to-playlist")
    public String saveToPlaylist(@RequestParam String artist, @RequestParam String track, @RequestParam String playlist) throws URISyntaxException {
        return playlistService.saveToPlaylist(artist,track,playlist);
    }

    @DeleteMapping("/remove-track")
    public String removeSong(@RequestParam String artist, @RequestParam String track, @RequestParam String playlist) {
        return playlistService.removeFromPlaylist(artist, track, playlist);
    }

    @GetMapping("/list-playlist")
    public String getSongs(String playlist){
        return playlistService.getPlaylistSongs(playlist);
    }
}
