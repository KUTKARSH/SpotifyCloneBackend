package com.example.spotify.repository;

import com.example.spotify.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SongRepository extends JpaRepository<Song,Integer> {
    public List<Song> findByAlbumId(Integer id);
    public Song findByName(String name);

}
