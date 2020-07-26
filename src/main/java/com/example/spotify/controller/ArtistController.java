package com.example.spotify.controller;

import com.example.spotify.model.Artist;
import com.example.spotify.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artist")
@CrossOrigin(origins = "https://silly-pare-01c9f2.netlify.app",allowedHeaders = "*")
public class ArtistController {

    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService){
        this.artistService = artistService;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Artist artist){
        return artistService.create(artist);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<?> view(){
        return artistService.read();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return artistService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> viewById(@PathVariable Integer id){
        return artistService.readById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Artist artist){
        return artistService.update(artist);
    }

    @RequestMapping(value = "/songs/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getSongsByArtistId(@PathVariable Integer id){
        return artistService.getSongsByArtistId(id);
    }

    @RequestMapping(value = "/follow/{artistId}/{userId}",method = RequestMethod.GET)
    public ResponseEntity<?> follow(@PathVariable Integer artistId,@PathVariable Integer userId){
        return artistService.follow(artistId,userId);
    }

    @RequestMapping(value = "/unfollow/{artistId}/{userId}",method = RequestMethod.GET)
    public ResponseEntity<?> unfollow(@PathVariable Integer artistId,@PathVariable Integer userId){
        return artistService.unfollow(artistId,userId);
    }

    @RequestMapping(value = "/userFollowed/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getFollowedArtistByUserId(@PathVariable Integer id){
        return artistService.getFollowedArtistByUserId(id);
    }

    @RequestMapping(value = "/userUnfollowed/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getUnfollowedArtistByUserId(@PathVariable Integer id){
        return artistService.getUnfollowedArtistByUserId(id);
    }
}

