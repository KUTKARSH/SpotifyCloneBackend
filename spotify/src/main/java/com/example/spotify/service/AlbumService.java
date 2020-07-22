package com.example.spotify.service;

import com.example.spotify.model.Album;
import com.example.spotify.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
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
}
