package berfin.songlib.repositories;

import berfin.songlib.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepo extends JpaRepository<Song, Long> {
    Song findByArtistAndTitle(String artist, String title);
}
