package com.songr.songr;

import com.songr.songr.model.Album;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Test");
	}

	@Test
	void testConstructor() {
		Album album = new Album("Title","Artist",10,120,"http://assets.stickpng.com/thumbs/60394f92b6264f0004079c19.png");
		album.setTitle("Title");
		assertEquals("Title",album.getTitle());
		album.setArtist("Artist");
		assertEquals("Artist",album.getArtist());
		album.setSongCount(20);
		assertEquals(20,album.getSongCount());
		album.setLength(10000);
		assertEquals(10000 , album.getLength());

		System.out.println(album.getTitle());
		System.out.println(album.getArtist());
		System.out.println(album.getSongCount());
		System.out.println(album.getLength());

	}

}
