package berfin.songlib.controllers;

import berfin.songlib.services.LastFmService;
import berfin.songlib.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class MusicController {

    private LastFmService lastFmService;

    @Autowired
    public MusicController(LastFmService lastFmService) {
        this.lastFmService = lastFmService;
    }

    @GetMapping("/get-track-info")
    public String getTrackInfo(@RequestParam String artist, @RequestParam String track) throws URISyntaxException {
        return lastFmService.getTrackInfo(artist, track);
    }

    @PostMapping("/save-track")
    public String saveSong(@RequestParam String artist, @RequestParam String track) throws URISyntaxException {
        return lastFmService.saveSong(artist, track);
    }


}