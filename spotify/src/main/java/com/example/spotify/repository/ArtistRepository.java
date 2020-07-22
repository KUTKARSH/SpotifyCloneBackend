package com.example.spotify.repository;

import com.example.spotify.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ArtistRepository extends JpaRepository<Artist,Integer> {
}
