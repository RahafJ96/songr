package com.songr.songr.controller;

import com.songr.songr.model.Album;
import com.songr.songr.model.AlbumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
public class MainController {


    @Autowired
    private AlbumRepo albumRepo;

    // http://localhost:8080/hello
    @GetMapping("/hello")
    public String helloWorld(){
        return "hello";
    }

    // http://localhost:8080/capitalize/hello
    // http://localhost:8080/hello/capitalize/name=rahaf
    @GetMapping("/capitalize/{word}")
    public String showCapital(Model m, @PathVariable("word") String word){
        m.addAttribute("word", word);
        return "capital";
    }

    @GetMapping("/getalbums")
    public String albums(Model model){
        ArrayList<Album> albums= new ArrayList<>();

        Album Song01 = new Album("Minefields","John Legend & Faouzia",25,10,"https://i1.sndcdn.com/artworks-lsKMQmWxQ9O4agYL-33Fe1Q-t500x500.jpg");
        Album Song02 = new Album("Hurt","Christina Aguilera",15,150,"https://i1.sndcdn.com/artworks-lsKMQmWxQ9O4agYL-33Fe1Q-t500x500.jpg");
        Album Song03 = new Album("All I Ask","Adele",41,205,"https://i1.sndcdn.com/artworks-000440501640-fbzxgs-t500x500.jpg");

        albums.add(Song01);
        albums.add(Song02);
        albums.add(Song03);

        model.addAttribute("album",albums);
        return "album";
    }

    // Add to database
    @PostMapping("/albums")
    public RedirectView createNewAlbum(@ModelAttribute Album album){
        albumRepo.save(album);
        return new RedirectView("addAlbum");
    }

    // read from database on the same page
    // http://localhost:8080/albums
    @GetMapping("/albums")
    String getAlbum(Model model) {
        model.addAttribute("album" , albumRepo.findAll());
        return "addAlbum";
    }

    @GetMapping("/existAlbums")
    public String getExistAlbum(Model model){
        model.addAttribute("album",albumRepo.findAll());
        return "album";
    }

    @PostMapping("/addAlbum")
    public String getAddAlbum(){
        return "addAlbum.html";
    }

    @PostMapping("/albums")
    public RedirectView addAlbum(@RequestParam(value = "title") String title,
                                 @RequestParam(value= "artist") String artist,
                                 @RequestParam(value="songCount") int songCount,
                                 @RequestParam(value="length") long length,
                                 @RequestParam(value="imageUrl") String imageUrl){
        Album album = new Album(title,artist,songCount,length,imageUrl);
 //       albumPackage.save(album);
        return  new RedirectView("/albums");
    }
}
