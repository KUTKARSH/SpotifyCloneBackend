package com.example.spotify.service;

import com.example.spotify.model.Playlist;
import com.example.spotify.model.Song;
import com.example.spotify.repository.PlaylistRepository;
import com.example.spotify.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlaylistService {

    private PlaylistRepository playlistRepository;
    private SongRepository songRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository,
                           SongRepository songRepository){
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
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
            if(!playlistId.equals("") && st.contains(playlistId) == false)
                newIdsList.add(playlistId);
        }
        playlist.setSongIds(String.join(",",newIdsList));
        playlistRepository.save(playlist);
        return new ResponseEntity<>(playlist,HttpStatus.OK);
    }

    public ResponseEntity<?> getPlaylistByIdWithSongs(Integer id){
        Playlist playlist = playlistRepository.findById(id).get();
        String songIds = playlist.getSongIds();
        List<String> idList = Arrays.asList(songIds.split(","));
        List<Song> songs = new ArrayList<>();
        for(String songId : idList){
            songs.add(songRepository.findById(Integer.parseInt(songId)).get());
        }
        return new ResponseEntity<>(songs,HttpStatus.OK);
    }

    public ResponseEntity<?> getPlaylistsOfUser(Integer id){
        return new ResponseEntity<>(playlistRepository.findByUserId(id),HttpStatus.OK);
    }

    public ResponseEntity<?> createPlaylist(String name,String songs,String userId){
        List<String> songNameList  = Arrays.asList(songs.split(","));
        String songIds = "";
        for(String songName : songNameList){
            songIds += songRepository.findByName(songName).getId() + ",";
        }
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setUserId(Integer.parseInt(userId));
        playlist.setSongIds(songIds);
        playlistRepository.save(playlist);
        return new ResponseEntity<>(playlist,HttpStatus.OK);
    }

    public ResponseEntity<?> deletePlaylistByName(String name){
        Playlist playlist = playlistRepository.findByName(name);
        playlistRepository.delete(playlist);
        return new ResponseEntity<>(playlist,HttpStatus.OK);
    }
}
