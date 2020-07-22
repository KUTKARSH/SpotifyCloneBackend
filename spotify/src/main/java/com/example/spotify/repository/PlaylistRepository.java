package com.example.spotify.repository;

import com.example.spotify.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PlaylistRepository extends JpaRepository<Playlist,Integer> {
}
