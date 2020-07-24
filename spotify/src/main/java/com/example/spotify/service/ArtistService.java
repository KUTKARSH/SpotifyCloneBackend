package com.example.spotify.service;

import com.example.spotify.model.Artist;
import com.example.spotify.model.Song;
import com.example.spotify.repository.ArtistRepository;
import com.example.spotify.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArtistService {

    private ArtistRepository artistRepository;
    private SongRepository songRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository,
                         SongRepository songRepository){
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
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

    public List<Song> parseQueryData(List<Song> songs,Integer id){
        List<Song> result = new ArrayList<>();
        for(Song song : songs){
            List<String> artistIds = Arrays.asList(song.getArtistIds().split(","));
            Set<String> artistSet = new HashSet<>();
            artistSet.addAll(artistIds);
            if(artistSet.contains(id.toString()))
                result.add(song);
        }
        return result;
    }

    public ResponseEntity<?> getSongsByArtistId(Integer id){
        List<Song> songs =  songRepository.findAll();
        return new ResponseEntity<>(parseQueryData(songs,id),HttpStatus.OK);
    }

    public ResponseEntity<?> follow(Integer artistId,Integer userId){
        Artist artist = artistRepository.findById(artistId).get();
        String followers = artist.getFollowers();
        followers = followers + "," + userId.toString();
        artist.setFollowers(followers);
        artistRepository.save(artist);
        return new ResponseEntity<>(artist,HttpStatus.OK);
    }

    public ResponseEntity<?> unfollow(Integer artistId,Integer userId){
        Artist artist = artistRepository.findById(artistId).get();
        List<String> followers = Arrays.asList(artist.getFollowers().split(","));
        String newFollowers = "";
        for(String user : followers){
            if(!user.equals("") && Integer.parseInt(user) != userId)
                newFollowers = newFollowers + "," + user;
        }
        artist.setFollowers(newFollowers);
        artistRepository.save(artist);
        return new ResponseEntity<>(artist,HttpStatus.OK);
    }

    private List<Artist> parse(List<Artist> artists,Integer id){
        List<Artist> result = new ArrayList<>();
        for(Artist artist : artists){
            List<String> followers = Arrays.asList(artist.getFollowers().split(","));
            Set<String> st = new HashSet<>();
            st.addAll(followers);
            if(st.contains(id.toString()))
                result.add(artist);
        }
        return result;
    }

    public ResponseEntity<?> getFollowedArtistByUserId(Integer id){
        List<Artist> artists = artistRepository.findAll();
        return new ResponseEntity<>(parse(artists,id),HttpStatus.OK);
    }

    public ResponseEntity<?> getUnfollowedArtistByUserId(Integer id){
        List<Artist> artists = artistRepository.findAll();
        List<Artist> result = new ArrayList<>();
        for(Artist artist : artists){
            List<String> followers = Arrays.asList(artist.getFollowers().split(","));
            Set<String> st = new HashSet<>();
            st.addAll(followers);
            if(!st.contains(id.toString()))
                result.add(artist);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
