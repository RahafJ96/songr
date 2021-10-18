package com.songr.songr.controller;

import com.songr.songr.model.SongCC;
import com.songr.songr.model.Albums;
import com.songr.songr.model.Song;
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
    public String hello(@RequestParam(name="name", required = false, defaultValue = "World")String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/capitalize/{word}")
    public String toUpperCase(@PathVariable String word, Model model){
        model.addAttribute("word",word.toUpperCase());
        return "capital";
    }

    @GetMapping("/getAllAlbums")
    public String albums(Model model){
        ArrayList<Albums> albums= new ArrayList<>();

        Albums Song01 = new Albums("Minefields","John Legend & Faouzia",25,10,"https://i1.sndcdn.com/artworks-lsKMQmWxQ9O4agYL-33Fe1Q-t500x500.jpg");
        Albums Song02 = new Albums("Hurt","Christina Aguilera",15,150,"https://images-na.ssl-images-amazon.com/images/I/71uhWKE4EOL._SL1500_.jpg");
        Albums Song03 = new Albums("All I Ask","Adele",41,205,"https://i1.sndcdn.com/artworks-000440501640-fbzxgs-t500x500.jpg");

        albums.add(Song01);
        albums.add(Song02);
        albums.add(Song03);

        model.addAttribute("album",albums);
        return "allAlbums";
    }

    // Add to database
    @PostMapping("/albums")
    public RedirectView createNewAlbum(@ModelAttribute Albums albums){
        //albumRepo.save(albums);
        //return new RedirectView("/albums");
        //Albums newAlbums = albumRepo.save(albums);
        albumRepo.save(albums);
        return new RedirectView("/albums");
    }

    @GetMapping("/albums")
    public String getAllAlbums(Model model){
        model.addAttribute("albums",albumRepo.findAll());
        return "newAlbums";
    }
    @GetMapping("/userInfo")
    public String userInfo(@RequestHeader MultiValueMap<String,String> headers, Model model){
        headers.forEach((key,value) -> System.out.println(String.format("Header '%s' = %s", key, String.join("|", value))));
        model.addAttribute("headerData",headers.get("user-agent"));
        return "userInfo";
    }

    // read from database on the same page
    //@GetMapping("/albums")
//    @GetMapping("addAlbum")
//    String addAlbum() {
//        return "addAlbum";
//    }
//    @PostMapping("/albums")
//    public RedirectView addAlbums(Albums album){
//        System.out.println(album);
//        albumRepo.save(album);
//        return new RedirectView("/albums");
//    }

    @GetMapping("/songs")
    public String getSongs(Model song){
       List<Song> songs=songRepo.findAll();
       song.addAttribute("song",songs);
        return "songs";
    }
    @GetMapping("/addSong")
    public String Form(){
        return "addSong";
    }

    @PostMapping("/songs")
    public RedirectView addSong(@RequestParam (value = "albumID")Long albums_id,Model song,
                                @RequestParam (value = "title")String title,
                                @RequestParam (value = "length")int length,
                                @RequestParam (value = "trackNumber")int trackNumber){
        Albums albums = albumRepo.findById(albums_id).get();
        Song songs = new Song(albums,title,length,trackNumber);
        songRepo.save(songs);
        Albums albums1 = albumRepo.findById(albums.getId()).get();
        song.addAttribute("song", albums1.getSongs());

        return new RedirectView("/songs");
    }
}
