package com.example.spotify.repository;

import com.example.spotify.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SongRepository extends JpaRepository<Song,Integer> {
}
