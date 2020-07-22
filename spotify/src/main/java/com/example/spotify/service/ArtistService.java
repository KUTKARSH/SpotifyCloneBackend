package com.example.spotify.service;

import com.example.spotify.model.Artist;
import com.example.spotify.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public ResponseEntity<?> create(Artist artist){
        artistRepository.save(artist);
        return new ResponseEntity<>(artist, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> update(Artist artist){
        artistRepository.save(artist);
        return new ResponseEntity<>(artist,HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Integer id){
        Artist artist = artistRepository.findById(id).get();
        artistRepository.delete(artist);
        return new ResponseEntity<>("Artist deleted",HttpStatus.OK);
    }

    public ResponseEntity<?> read(){
        List<Artist> artists = artistRepository.findAll();
        return new ResponseEntity<>(artists,HttpStatus.OK);
    }

    public ResponseEntity<?> readById(Integer id){
        Artist artist = artistRepository.findById(id).get();
        return new ResponseEntity<>(artist,HttpStatus.FOUND);
    }
}
