package berfin.songlib.repositories;

import berfin.songlib.entities.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryUserRepo extends JpaRepository<LibraryUser, Long> {
}
