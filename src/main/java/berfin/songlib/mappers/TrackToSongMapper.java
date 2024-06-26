package berfin.songlib.mappers;

import berfin.songlib.entities.Song;

public class TrackToSongMapper {

    public static Song mapToSongEntity(LastFmResponse.Track track) {
        Song song = new Song();
        song.setTitle(track.getName());
        song.setArtist(track.getArtist().getName());
        song.setAlbum(track.getAlbum() != null ? track.getAlbum().getTitle() : "Unknown Album");
        song.setGenre(track.getToptags() != null && !track.getToptags().getTag().isEmpty()
                ? track.getToptags().getTag().get(0).getName()
                : "Unknown Genre");

        if (track.getWiki() != null && track.getWiki().getPublished() != null) {
            song.setReleaseYear(track.getWiki().getPublished());
        } else {
            song.setReleaseYear("Unknown Release Year");
        }

        return song;
    }
}
