package com.songr.songr.controller;

import com.songr.songr.model.Albums;
import com.songr.songr.model.Song;
import com.songr.songr.repository.AlbumRepo;
import com.songr.songr.repository.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {


    @Autowired
    AlbumRepo albumRepo;

    @Autowired
    SongRepo songRepo;

    // http://localhost:8080/hello
    @GetMapping("/hello")
    public String helloWorld(@RequestParam(name="name", required = false, defaultValue = "World")String name, Model model){
        model.addAttribute("name", name);
        return "welcome";
    }

    @GetMapping("/capitalize/{word}")
    public String showCapital(@PathVariable String word, Model model){
        model.addAttribute("word",word.toUpperCase());
        return "capital";
    }

    @GetMapping("/getAllalbums")
    public String albums(Model model){
        ArrayList<Albums> albums= new ArrayList<>();

        Albums Song01 = new Albums("Minefields","John Legend & Faouzia",25,10,"https://i1.sndcdn.com/artworks-lsKMQmWxQ9O4agYL-33Fe1Q-t500x500.jpg");
        Albums Song02 = new Albums("Hurt","Christina Aguilera",15,150,"https://i1.sndcdn.com/artworks-lsKMQmWxQ9O4agYL-33Fe1Q-t500x500.jpg");
        Albums Song03 = new Albums("All I Ask","Adele",41,205,"https://i1.sndcdn.com/artworks-000440501640-fbzxgs-t500x500.jpg");

        albums.add(Song01);
        albums.add(Song02);
        albums.add(Song03);

        model.addAttribute("album",albums);
        return "staticAlbum";
    }

    // Add to database
    @PostMapping("/albums")
    public RedirectView createNewAlbum(@ModelAttribute Albums albums){
        albumRepo.save(albums);
        return new RedirectView("/albums");
    }

    // read from database on the same page
    @GetMapping("/albums")
    String getAllAlbums(Model model) {
        model.addAttribute("albums" , albumRepo.findAll());
        return "newAlbums";
    }

    @GetMapping("/songs")
    public String getSongs(Model model){
        List<Song> songs= songRepo.findAll();
        model.addAttribute("song",songs);
        return "songs";
    }
    @GetMapping("/addSong")
    public String Form(){
        return "addSong";
    }

    @PostMapping("/songs")
    public RedirectView addAlbum(@RequestParam(value = "albumID") Long albums_id,Model song,
                                 @RequestParam(value= "title") String title,
                                 @RequestParam(value="length") int length,
                                 @RequestParam(value="trackNumber") int trackNumber){
        Albums albums = albumRepo.findById(albums_id).get();
        Song songs = new Song(albums,title,length,trackNumber);
        songRepo.save(songs);
        Albums albums1 = albumRepo.findById(albums.getId()).get();
        song.addAttribute("song", albums1.getSongs());

        return new RedirectView("/songs");
    }
}
