package hust.soict.hedspi.test;

import hust.soict.hedspi.aims.media.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Media> mediaList = new ArrayList<>();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Matrix", "Science Fiction", 14.99f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Inception", "Action", 19.99f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Interstellar", "Science Fiction", 24.99f, "Christopher Nolan", 169);
        DigitalVideoDisc dvd4 = new DigitalVideoDisc(4, "The Godfather", "Crime", 17.50f, "Francis Ford Coppola", 175);
        mediaList.add(dvd1);
        mediaList.add(dvd2);
        mediaList.add(dvd3);
        mediaList.add(dvd4);

        CompactDisc cd1 = new CompactDisc(5, "Thriller", "Pop", 12.99f);
        CompactDisc cd2 = new CompactDisc(6, "The Wall", "Rock", 15.50f);
        List<Track> chillTracks = new ArrayList<>();
        chillTracks.add(new Track("Intro", 90));
        chillTracks.add(new Track("Smooth Vibes", 210));
        chillTracks.add(new Track("Sunset Groove", 180));
        CompactDisc cd3 = new CompactDisc(7, "Chill Beats", "Lo-fi", 9.99f, chillTracks);
        List<Track> epicTracks = Arrays.asList(
                new Track("Overture", 120),
                new Track("Battle Theme", 300),
                new Track("Finale", 240)
        );
        CompactDisc cd4 = new CompactDisc(8, "Epic Soundtrack", "Orchestral", 14.99f, epicTracks);
        mediaList.add(cd1);
        mediaList.add(cd2);
        mediaList.add(cd3);
        mediaList.add(cd4);

        Book book1 = new Book(12, "To Kill a Mockingbird", "Fiction", 9.99f);
        Book book2 = new Book(13, "1984", "Dystopian", 14.50f);
        List<String> authors1 = List.of("J.K. Rowling");
        Book book3 = new Book(14, "Harry Potter and the Sorcerer's Stone", "Fantasy", 19.99f, authors1);
        List<String> authors2 = List.of("George R.R. Martin");
        Book book4 = new Book(15, "A Game of Thrones", "Fantasy", 22.50f, authors2);
        mediaList.add(book1);
        mediaList.add(book2);
        mediaList.add(book3);
        mediaList.add(book4);

        for(Media media : mediaList) {
            System.out.println(media.toString());
        }

    }
}
