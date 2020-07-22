package com.example.spotify.service;

import com.example.spotify.model.Playlist;
import com.example.spotify.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlaylistService {

    private PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
    }

    public ResponseEntity<?> create(Playlist playlist){
        playlistRepository.save(playlist);
        return new ResponseEntity<>(playlist, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> update(Playlist playlist){
        playlistRepository.save(playlist);
        return new ResponseEntity<>(playlist,HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Integer id){
        Playlist playlist = playlistRepository.findById(id).get();
        playlistRepository.delete(playlist);
        return new ResponseEntity<>("Artist deleted",HttpStatus.OK);
    }

    public ResponseEntity<?> read(){
        List<Playlist> playlists = playlistRepository.findAll();
        return new ResponseEntity<>(playlists,HttpStatus.OK);
    }

    public ResponseEntity<?> readById(Integer id){
        Playlist playlist = playlistRepository.findById(id).get();
        return new ResponseEntity<>(playlist,HttpStatus.FOUND);
    }

    public ResponseEntity<?> addToPlaylist(Integer id,String ids){
        Playlist playlist = playlistRepository.findById(id).get();
        playlist.setSongIds(playlist.getSongIds().concat("," + ids));
        playlistRepository.save(playlist);
        return new ResponseEntity<>(playlist,HttpStatus.OK);
    }

    public ResponseEntity<?> removeFromPlaylist(Integer id,String ids){
        Playlist playlist = playlistRepository.findById(id).get();
        List<String> newIdsList = new ArrayList<>();
        Set<String> st = new HashSet<>();
        st.addAll(Arrays.asList(ids.split(",")));
        List<String> oldIdsList = Arrays.asList(playlist.getSongIds().split(","));
        for(String playlistId : oldIdsList){
            if(st.contains(playlistId) == false)
                newIdsList.add(playlistId);
        }
        playlist.setSongIds(String.join(",",newIdsList));
        playlistRepository.save(playlist);
        return new ResponseEntity<>(playlist,HttpStatus.OK);
    }

}
