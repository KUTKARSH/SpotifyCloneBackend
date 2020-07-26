package com.example.spotify.controller;

import com.example.spotify.model.Song;
import com.example.spotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
@CrossOrigin(origins = "https://silly-pare-01c9f2.netlify.app/")
public class SongController {
    private SongService songService;

    @Autowired
    public SongController(SongService songService){
        this.songService = songService;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Song song){
        return songService.create(song);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<?> view(){
        return songService.view();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return songService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> viewById(@PathVariable Integer id){
        return songService.viewById(id);
    }

    @RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> searchByName(@PathVariable String name){
        return songService.searchByName(name);
    }
}
