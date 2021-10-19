package com.songr.songr.controller;

import com.songr.songr.model.Albums;
import com.songr.songr.model.Song;
import com.songr.songr.model.Songs;
import com.songr.songr.repository.AlbumRepo;
import com.songr.songr.repository.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
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

    @GetMapping("/hello")
    public String hello(@RequestParam(name="name", required = false, defaultValue = "hello")String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/capitalize/{word}")
    public String toUpperCase(@PathVariable String word, Model model){
        model.addAttribute("word",word.toUpperCase());
        return "capital";
    }

    @GetMapping("/albums")
    public String albumsAvailable(Model model){
        List<Albums> list = albumRepo.findAll();
        ArrayList<Albums> albums= new ArrayList<>();

        Albums Song01 = new Albums("Minefields","John Legend & Faouzia",25,10,"https://i1.sndcdn.com/artworks-lsKMQmWxQ9O4agYL-33Fe1Q-t500x500.jpg");
        Albums Song02 = new Albums("Hurt","Christina Aguilera",15,150,"https://images-na.ssl-images-amazon.com/images/I/71uhWKE4EOL._SL1500_.jpg");
        Albums Song03 = new Albums("All I Ask","Adele",41,205,"https://i1.sndcdn.com/artworks-000440501640-fbzxgs-t500x500.jpg");

        albums.add(Song01);
        albums.add(Song02);
        albums.add(Song03);

        model.addAttribute("albums",list);
        //model.addAttribute("album",albums);
        return "albums";
    }
    @GetMapping("/addAlbum")
    public String addAlbums(){
        return "addAlbum";
    }

    // Add to database
    @PostMapping("/albums")
    public RedirectView addAlbum(Albums albums){
        System.out.println(albums);
        albumRepo.save(albums);
        return new RedirectView("/albums");
    }


    @GetMapping("/userInfo")
    public String userInfo(@RequestHeader MultiValueMap<String,String> headers, Model model){
        headers.forEach((key,value) -> System.out.println(String.format("Header '%s' = %s", key, String.join("|", value))));
        model.addAttribute("headerData",headers.get("user-agent"));
        return "userInfo";
    }

    @GetMapping("/songs")
    public String getSongs(Model song){
       song.addAttribute("songs",songRepo.findAll());
        return "songs";
    }

    @PostMapping("/songs")
    public RedirectView addSong(Songs song){
        Albums newAlbum = albumRepo.findByTitle(song.getAlbums());
        Song newSong = new Song(song.getTitle(),song.getLength(),newAlbum);
        songRepo.save(newSong);
        return new RedirectView("/songs");
    }
}
