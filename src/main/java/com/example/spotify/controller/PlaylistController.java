package com.example.spotify.controller;

import com.example.spotify.model.Playlist;
import com.example.spotify.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
@CrossOrigin(origins = "https://clever-mahavira-2dffb7.netlify.app")
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

    @RequestMapping(value = "/songs/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getPlaylistByIdWithSongs(@PathVariable Integer id){
        return playlistService.getPlaylistByIdWithSongs(id);
    }

    @RequestMapping(value = "/byUser/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getPlaylistsOfUser(@PathVariable Integer id){
        return playlistService.getPlaylistsOfUser(id);
    }

    @RequestMapping(value = "/userCreate/{name}/{songs}/{userId}",method = RequestMethod.GET)
    public ResponseEntity<?> userCreate(@PathVariable String name,@PathVariable String songs,
    @PathVariable String userId){
        return playlistService.createPlaylist(name,songs,userId);
    }

    @RequestMapping(value = "/deletePlaylist/{name}")
    public ResponseEntity<?> userDeletePlaylist(@PathVariable String name){
        return playlistService.deletePlaylistByName(name);
    }
}
