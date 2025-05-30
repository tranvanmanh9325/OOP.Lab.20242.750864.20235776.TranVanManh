package hust.soict.hedspi.test.screen.customer.store;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.naming.LimitExceededException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestViewStoreScreen extends Application {
    private static Store store = new Store();
    private static final Cart cart = new Cart();

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/src/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        ViewStoreController viewStoreController = new ViewStoreController(store,cart);
        fxmlLoader.setController(viewStoreController);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root, 1150, 800));
        primaryStage.show();
    }

    public static void main(String[] args) throws LimitExceededException {
        store = new Store();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Matrix", "Science Fiction", 14.99f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Inception", "Action", 19.99f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Interstellar", "Science Fiction", 24.99f, "Christopher Nolan", 169);
        DigitalVideoDisc dvd4 = new DigitalVideoDisc(4, "The Godfather", "Crime", 17.50f, "Francis Ford Coppola", 175);
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);
        store.addMedia(dvd4);

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
        store.addMedia(cd1);
        store.addMedia(cd2);
        store.addMedia(cd3);
        store.addMedia(cd4);

        Book book1 = new Book(12, "To Kill a Mockingbird", "Fiction", 9.99f);
        Book book2 = new Book(13, "1984", "Dystopian", 14.50f);
        List<String> authors1 = List.of("J.K. Rowling");
        Book book3 = new Book(14, "Harry Potter and the Sorcerer's Stone", "Fantasy", 19.99f, authors1);
        List<String> authors2 = List.of("George R.R. Martin");
        Book book4 = new Book(15, "A Game of Thrones", "Fantasy", 22.50f, authors2);
        store.addMedia(book1);
        store.addMedia(book2);
        store.addMedia(book3);
        store.addMedia(book4);
        launch(args);
    }
}
