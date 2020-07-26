package com.example.spotify.controller;

import com.example.spotify.model.Album;
import com.example.spotify.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
@CrossOrigin(origins = "http://localhost:4200")
public class AlbumController {
    private AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService){
        this.albumService = albumService;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Album album){
        return albumService.create(album);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<?> view(){
        return albumService.read();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return albumService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> viewById(@PathVariable Integer id){
        return albumService.readById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Album album){
        return albumService.update(album);
    }

    @RequestMapping(value = "/songs/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getAlbumByIdWithSongs(@PathVariable Integer id){
        return albumService.getAlbumByIdWithSongs(id);
    }
}
