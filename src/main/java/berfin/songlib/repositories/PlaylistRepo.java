package berfin.songlib.repositories;

import berfin.songlib.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepo extends JpaRepository<Playlist, Long> {
    Playlist findByName(String name);
}
