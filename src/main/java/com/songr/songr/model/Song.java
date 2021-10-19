package com.songr.songr.model;

import javax.persistence.*;


@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_number", nullable = false)
    private Long trackNumber;

    private String title;
    private int length;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Albums albums;

    public Song() {
    }

    public Song(String title, int length, Albums albums) {
        this.title = title;
        this.length = length;
        this.albums = albums;
    }

    public Long getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Long trackNumber) {
        this.trackNumber = trackNumber;
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

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
    }
}