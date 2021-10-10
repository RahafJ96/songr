package com.songr.songr;

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
            new Album("sehert allail",
                    "George wassouf" ,
                    15 ,
                    1500 , "https://layalina.awicdn.com/site-images/sites/default/files/prod/article/180918/%D8%B5%D9%88%D8%B1%D8%A9-%D8%AC%D9%88%D8%B1%D8%AC-%D9%88%D8%B3%D9%88%D9%81-%D8%A7%D9%84%D8%AA%D9%8A-%D8%A3%D8%AB%D8%A7%D8%B1%D8%AA-%D8%A7%D8%B3%D8%AA%D8%BA%D8%B1%D8%A7%D8%A8-%D8%AC%D9%85%D9%87%D9%88%D8%B1%D9%87-1348334.gif?preset=v4.0_770X577&save-png=1&rnd=0519151220214-OLD&animation=1")
            ,
            new Album("Love of my life"
                    , "Queen",
                    26 ,
                    15002 ,
                    "https://www.nme.com/wp-content/uploads/2020/03/queen-bohemianrhapsody-2000x1270-1-696x442.jpg")
            ,
            new Album("Hello World"
                    , "Mohammed Mohiesen" ,
                    10 ,
                    2600 ,
                    "https://www.rollingstone.com/wp-content/uploads/2018/06/rs-13220-johnnycash-624-1383147488.jpg?resize=1800,1200&w=1200"        )
    };

    @GetMapping("/albums")
    String getAlbum(Model album) {
        album.addAttribute("album" , albums);
        return "albums";
    }
}
