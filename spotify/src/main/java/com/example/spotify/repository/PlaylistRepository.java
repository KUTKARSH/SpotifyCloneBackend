package com.example.spotify.repository;

import com.example.spotify.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PlaylistRepository extends JpaRepository<Playlist,Integer> {
    List<Playlist> findByUserId(Integer id);
}
