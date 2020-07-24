package com.example.spotify.service;

import com.example.spotify.model.Album;
import com.example.spotify.model.Song;
import com.example.spotify.repository.AlbumRepository;
import com.example.spotify.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private AlbumRepository albumRepository;
    private SongRepository songRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository,
                        SongRepository songRepository){
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    public ResponseEntity<?> create(Album album){
        albumRepository.save(album);
        return new ResponseEntity<>(album, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> update(Album album){
        albumRepository.save(album);
        return new ResponseEntity<>(album,HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Integer id){
        Album album = albumRepository.findById(id).get();
        albumRepository.delete(album);
        return new ResponseEntity<>("Artist deleted",HttpStatus.OK);
    }

    public ResponseEntity<?> read(){
        List<Album> artists = albumRepository.findAll();
        return new ResponseEntity<>(artists,HttpStatus.OK);
    }

    public ResponseEntity<?> readById(Integer id){
        Album album = albumRepository.findById(id).get();
        return new ResponseEntity<>(album,HttpStatus.FOUND);
    }

    public ResponseEntity<?> getAlbumByIdWithSongs(Integer id){
        List<Song> songs = songRepository.findByAlbumId(id);
        return new ResponseEntity<>(songs,HttpStatus.OK);
    }
}
