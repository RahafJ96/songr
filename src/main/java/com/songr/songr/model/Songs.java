package com.songr.songr.model;

//import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

public class Songs {

    private String title;
    private int length;
    private String albums;


    public Songs(String title, int length, String albums) {
        this.title = title;
        this.length = length;
        this.albums = albums;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getAlbums() {
        return albums;
    }

    public void setAlbums(String albums) {
        this.albums = albums;
    }
}