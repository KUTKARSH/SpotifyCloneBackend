package com.example.spotify.service;

import com.example.spotify.model.Song;
import com.example.spotify.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository){
        this.songRepository = songRepository;
    }

    public ResponseEntity<?> create(Song song){
        songRepository.save(song);
        return new ResponseEntity<>(song, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> view(){
        List<Song> songs = songRepository.findAll();
        return new ResponseEntity<>(songs,HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Integer id){
        songRepository.delete(songRepository.findById(id).get());
        return new ResponseEntity<>("Song deleted", HttpStatus.OK);
    }

    public ResponseEntity<?> viewById(Integer id){
        Song song = songRepository.findById(id).get();
        return new ResponseEntity<>(song,HttpStatus.FOUND);
    }

    public ResponseEntity<?> searchByName(String name){
        return new ResponseEntity<>(songRepository.findByName(name),HttpStatus.OK);
    }
}
