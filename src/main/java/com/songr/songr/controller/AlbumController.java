package com.songr.songr.controller;

import com.songr.songr.model.Album;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AlbumController {
    @Autowired
    Album albumRepository;
    private MultiValueMap<String, String> createData;

    @GetMapping("/albums")
    public String getAlbum(Model model){
        model.addAttribute("albums", albumRepository.findAll());
        return "albums.html";
    }

    @GetMapping("/albums/{id}")
    public void getAuthorById(@PathVariable(value = "id") Integer id, Model m){
        Album album=albumRepository.findById(id).get();
        m.addAttribute("album",album);

    }

    @PostMapping("/addAlbums")
    public RedirectView addAlbum(@RequestBody MultiValueMap <String, String> createData){
        Album album=new Album(createData.get("title").get(0),createData.get("artist").get(0),createData.get("songCount").get(0),createData.get("length").get(0),createData.get("imageUrl").get(0));
        albumRepository.save(album);
        return new RedirectView("/albums");
    }
}
