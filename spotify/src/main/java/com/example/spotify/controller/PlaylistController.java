package com.example.spotify.controller;

import com.example.spotify.model.Playlist;
import com.example.spotify.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    private PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Playlist playlist){
        return playlistService.create(playlist);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<?> view(){
        return playlistService.read();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return playlistService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> viewById(@PathVariable Integer id){
        return playlistService.readById(id);
    }

    @RequestMapping(value = "/add/{id}/{ids}",method = RequestMethod.GET)
    public ResponseEntity<?> addToPlaylist(@PathVariable Integer id,@PathVariable String ids){
        return playlistService.addToPlaylist(id,ids);
    }

    @RequestMapping(value = "/remove/{id}/{ids}",method = RequestMethod.GET)
    public ResponseEntity<?> removeFromPlaylist(@PathVariable Integer id,@PathVariable String ids){
        return playlistService.removeFromPlaylist(id,ids);
    }
}
