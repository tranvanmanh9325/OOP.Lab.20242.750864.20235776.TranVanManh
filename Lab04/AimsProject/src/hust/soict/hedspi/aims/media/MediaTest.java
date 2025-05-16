package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class MediaTest {

	public static void main(String[] args) {
        ArrayList<Media> mediaList = new ArrayList<>();

        // Them cac doi tuong vao danh sach
        Media book = new Book(1, "Java Programming", "Education", 15.5f);
        Media dvd = new DigitalVideoDisc("The Matrix", "Action", "Wachowski", 136, 19.99f);
        Media anotherDvd = new DigitalVideoDisc("Inception", "Sci-fi", "Christopher Nolan", 148, 21.99f);

        mediaList.add(book);
        mediaList.add(dvd);
        mediaList.add(anotherDvd);

        // Duyet va in thong tin su dung toString()
        for (Media media : mediaList) {
            System.out.println(media.toString());
        }
    }
}
