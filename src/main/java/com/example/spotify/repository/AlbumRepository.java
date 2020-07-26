package com.example.spotify.repository;

import com.example.spotify.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;



@Component
public interface AlbumRepository extends JpaRepository<Album,Integer> {
}
