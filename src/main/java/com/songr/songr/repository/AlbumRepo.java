package com.songr.songr.repository;

import com.songr.songr.model.Albums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepo extends JpaRepository<Albums,Long> {
    Albums findByTitle(String title);
}
