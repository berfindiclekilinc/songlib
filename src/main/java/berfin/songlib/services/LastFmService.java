package berfin.songlib.services;

import berfin.songlib.entities.Song;
import berfin.songlib.mappers.LastFmResponse;
import berfin.songlib.mappers.TrackToSongMapper;
import berfin.songlib.repositories.SongRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class LastFmService {

    @Value("${lastfm.baseurl}")
    private String baseUrl;

    @Value("${lastfm.apikey}")
    private String apiKey;

    private SongRepo songRepo;

    public String getTrackInfo(String artist, String track) {
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

            return formatResponse(lastFmResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String formatResponse(LastFmResponse response) {
        LastFmResponse.Track track = response.getTrack();
        StringBuilder formattedResponse = new StringBuilder();
        formattedResponse.append("Track Name: ").append(track.getName()).append("\n");
        formattedResponse.append("URL: ").append(track.getUrl()).append("\n");
        formattedResponse.append("Duration: ").append(track.getDuration()).append("\n");
        formattedResponse.append("Listeners: ").append(track.getListeners()).append("\n");
        formattedResponse.append("Playcount: ").append(track.getPlaycount()).append("\n");
        formattedResponse.append("Artist Name: ").append(track.getArtist().getName()).append("\n");
        formattedResponse.append("Artist URL: ").append(track.getArtist().getUrl()).append("\n");
        formattedResponse.append("Album Title: ").append(track.getAlbum().getTitle()).append("\n");
        formattedResponse.append("Album URL: ").append(track.getAlbum().getUrl()).append("\n");

        if (track.getWiki() != null){
            formattedResponse.append("Release date: ").append(track.getWiki().getPublished()).append(("\n"));
            formattedResponse.append("Wiki Summary: ").append(track.getWiki().getSummary()).append("\n");
        }


        formattedResponse.append("Tags: ");
        if (track.getToptags().getTag().isEmpty()) {
            formattedResponse.append("None\n");
        } else {
            for (LastFmResponse.Track.TopTags.Tag tag : track.getToptags().getTag()) {
                formattedResponse.append(tag.getName()).append(" ");
            }
            formattedResponse.append("\n");
        }

        return formattedResponse.toString();
    }


    public String saveSong(String artist, String track) {
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
            return ("Song added to the database.") ;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
