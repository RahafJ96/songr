package com.songr.songr.controller;

import com.songr.songr.model.Album;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @GetMapping("/hello")
    String getGreeting(@RequestParam(name="name", required = false, defaultValue = "World") String name , Model userName) {
        userName.addAttribute("name" , name);
        return "welcome";
    }

    @GetMapping("/capitalize/{word}")
    public String showCapital(Model m, @PathVariable("word") String word){
        m.addAttribute("word", word);
        return "capital";
    }

    Album albums[] = {
            new Album("Minefields",
                    "John Legend & Faouzia" ,
                    15 ,
                    1500 , "https://i1.sndcdn.com/artworks-lsKMQmWxQ9O4agYL-33Fe1Q-t500x500.jpg")
            ,
            new Album("Hurt"
                    , "Christina Aguilera",
                    26 ,
                    15002 ,
                    "https://upload.wikimedia.org/wikipedia/en/7/77/Christina_Aguilera_-_Hurt_%28single%29.png")
            ,
            new Album("All I Ask"
                    , "Adele" ,
                    10 ,
                    2600 ,
                    "https://i1.sndcdn.com/artworks-000440501640-fbzxgs-t500x500.jpg")
    };

    @GetMapping("/albums")
    String getAlbum(Model album) {
        album.addAttribute("album" , albums);
        return "albums";
    }
}
