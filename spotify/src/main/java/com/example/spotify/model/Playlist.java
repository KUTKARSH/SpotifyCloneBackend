package com.example.spotify.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Playlist {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer userId;
    private String songIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSongIds() {
        return songIds;
    }

    public void setSongIds(String songIds) {
        this.songIds = songIds;
    }
}
