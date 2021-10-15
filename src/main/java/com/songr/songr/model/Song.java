package com.songr.songr.model;

//import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "albums_id")
    private Albums albums;
    private String title;
    private int length;
    private int trackNumber;


    public Song() {
    }

    public Song(Albums albums, String title, int length, int trackNumber) {
        this.albums = albums;
        this.title = title;
        this.length = length;
        this.trackNumber = trackNumber;
    }

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(Albums albums) {
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

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
