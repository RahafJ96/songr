package com.songr.songr.repository;

import com.songr.songr.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepo extends JpaRepository<Song,Long> {
}
