package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.screen.customer.ViewCustomerScreen;
import hust.soict.hedspi.aims.screen.manager.StoreManagerScreen;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Platform;

import javax.naming.LimitExceededException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Aims {
    private static final Store store = new Store();
    private static final Cart cart = new Cart();
    static Scanner sc = new Scanner(System.in);
    public static String title;

    public static void main(String[] args) throws LimitExceededException {
        loadStoreTest();

        String[] options = {"Manager Screen", "Customer Screen"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose an interface to open:",
                "AIMS System",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {
            new StoreManagerScreen(store, cart);
        } else if (choice == 1) {
            ViewCustomerScreen.sharedStore = store;
            ViewCustomerScreen.sharedCart = cart;
            Platform.setImplicitExit(false);
            ViewCustomerScreen.launchScreen();
        };
    }

    public static void loadStoreTest() throws LimitExceededException {
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
    }

    public static void showMenu() throws PlayerException, LimitExceededException {
        System.out.println("    AIMS: ");
        System.out.println("    --------------------------------");
        System.out.println("    1. View store");
        System.out.println("    2. Update store");
        System.out.println("    3. See current cart");
        System.out.println("    0. Exit");
        System.out.println("    --------------------------------");
        System.out.println("    Please choose a number: 0-1-2-3");
       while(true) {
           int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 0:
                    return;
                case 1:
                    store.print();
                    storeMenu();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    cart.print();
                    cartMenu();
                    break;
                default:
                    System.out.println("Invalid input");
            }
            System.out.print("Enter option:");
        }
    }

    public static void storeMenu() throws PlayerException {
        System.out.println("    Options: ");
        System.out.println("    --------------------------------");
        System.out.println("    1. See a media’s details");
        System.out.println("    2. Add a media to cart");
        System.out.println("    3. Play a media");
        System.out.println("    4. See current cart");
        System.out.println("    0. Back");
        System.out.println("    --------------------------------");
        System.out.println("    Please choose a number: 0-1-2-3-4");
      while (true) {
          int input = sc.nextInt();
            sc.nextLine();
            switch (input){
                case 0:
                    return;
                case 1:
                    seeMediaDetails();
                    break;
                case 2:
                    addMediaToCart();
                    break;
                case 3:
                    playMediaFromStore();
                    break;
                case 4:
                    cart.print();
                    cartMenu();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            System.out.print("Enter option:");
        }
    }

    public static void mediaDetailsMenu(Media product) throws PlayerException {
        System.out.println("    Options: ");
        System.out.println("    --------------------------------");
        System.out.println("    1. Add to cart");
        System.out.println("    2. Play");
        System.out.println("    0. Back");
        System.out.println("    --------------------------------");
        System.out.println("    Please choose a number: 0-1-2");
        while (true) {
            int input = sc.nextInt();
            sc.nextLine();
            switch (input) {
                case 0:
                    return;
                case 1:
                    cart.addMedia(product);
                    break;
                case 2:
                    playMedia(product);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            System.out.print("Enter option:");
        }
    }

    public static void cartMenu() throws PlayerException {
        System.out.println("    Options: ");
        System.out.println("    --------------------------------");
        System.out.println("    1. Filter media in cart");
        System.out.println("    2. Sort media in cart");
        System.out.println("    3. Remove media from cart");
        System.out.println("    4. Play a media");
        System.out.println("    5. Place order");
        System.out.println("    0. Back");
        System.out.println("    --------------------------------");
        System.out.println("    Please choose a number: 0-1-2-3-4-5");

        while (true) {
            int input = sc.nextInt();
            sc.nextLine();
            switch (input){
                case 0:
                    return;
                case 1:
                    filterMediaInCart();
                    break;
                case 2:
                    sortMediaInCart();
                    break;
                case 3:
                    removeMediaFromCart();
                    break;
                case 4:
                    playMediaFromStore();
                    break;
                case 5:
                    cart.print();
                    cart.placeAnOrder();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            System.out.print("Enter option:");
        }
    }

    public static void updateStore() throws LimitExceededException {
        System.out.println("    Choose 1 to add book");
        System.out.println("    Choose 2 to add DVD");
        System.out.println("    Choose 3 to add CDs");
        System.out.println("    Choose 4 to remove media");
        System.out.println("    Press other key to exit");
        char ch = sc.nextLine().charAt(0);

        if (ch == '1' || ch == '2' || ch == '3') {

            System.out.print("Enter title: ");
            String title = sc.nextLine();
            System.out.print("Enter category: ");
            String category = sc.nextLine();
            System.out.print("Enter price: ");
            float price = Float.parseFloat(sc.nextLine());

            if (ch == '1') {
                List<String> authors = new ArrayList<>();
                System.out.print("Enter number of authors: ");
                int numAuthors = Integer.parseInt(sc.nextLine());
                System.out.print("Enter authors: ");
                for (int i = 0; i < numAuthors; i++) {
                    authors.add(sc.nextLine());
                }
                Book book = new Book(store.getNumberOfMedia()+1, title, category, price, authors);
                store.addMedia(book);
            } else {
                System.out.print("Enter length: ");
                int length = Integer.parseInt(sc.nextLine());
                System.out.print("Enter director: ");
                String director = sc.nextLine();

                if (ch == '3') {
                    System.out.print("Enter artist: ");
                    String artist = sc.nextLine();
                    System.out.print("Enter number of tracks:");
                    int numTracks = Integer.parseInt(sc.nextLine());
                    List<Track> tracks = new ArrayList<>();
                    for (int i = 0; i < numTracks; i++) {
                        System.out.print("Enter track title: ");
                        String trackTitle = sc.nextLine();
                        System.out.print("Enter track length: ");
                        int trackLength = Integer.parseInt(sc.nextLine());
                        Track newTrack = new Track(trackTitle, trackLength);
                        tracks.add(newTrack);
                    }
                    CompactDisc compactDisc = new CompactDisc(store.getNumberOfMedia()+1, title, category, price, tracks);
                    store.addMedia(compactDisc);
                } else {
                    DigitalVideoDisc dvd = new DigitalVideoDisc(store.getNumberOfMedia()+1, title, category, price, director, length);
                    store.addMedia(dvd);
                }
            }
        }
    }

    public static void seeMediaDetails() throws PlayerException {
        title = "";
        while(true) {
            System.out.print("Enter title: ");
            title = sc.nextLine();
            Media product = store.searchByTitle(title);
            if(product!=null) {
                System.out.println(product.toString());
                mediaDetailsMenu(product);
                break;
            }
        }
    }

    public static void addMediaToCart() {
        title = "";
        while(true) {
            System.out.print("Enter title: ");
            title = sc.nextLine();
            Media product = store.searchByTitle(title);
            if(product!=null) {
                cart.addMedia(product);
                break;
            }
        }
    }

    public static void playMediaFromStore() throws PlayerException {
        title = "";
        while(true) {
            System.out.print("Enter title: ");
            title = sc.nextLine();
            Media product = store.searchByTitle(title);
            if(product != null) {
                playMedia(product);
                break;
            }
        }
    }

    public static void playMedia(Media product) throws PlayerException {
        if (product instanceof Playable) {
            ((Playable) product).play();
        } else {
            System.out.println("Cannot play this media type (only DVDs and CDs are playable).");
        }
    }

    public static void filterMediaInCart() {
        System.out.println("    Options: ");
        System.out.println("    --------------------------------");
        System.out.println("    1. Filter by ID");
        System.out.println("    2. Filter by Title");
        System.out.println("    0. Back");
        System.out.println("    --------------------------------");
        System.out.println("    Please choose a number: 0-1-2");
        switch (sc.nextInt()) {
            case 0:
                return;
            case 1:
                System.out.println("Enter the ID: ");
                System.out.println(store.searchById(sc.nextInt()));
                break;
            case 2:
                System.out.println("Enter the title: ");
                System.out.println(store.searchByTitle(sc.nextLine()));
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    public static void sortMediaInCart() {
        System.out.println("    Options: ");
        System.out.println("    --------------------------------");
        System.out.println("    1. Sort by Title");
        System.out.println("    2. Sort by Price");
        System.out.println("    0. Back");
        System.out.println("    --------------------------------");
        System.out.println("    Please choose a number: 0-1-2");
        switch (sc.nextInt()) {
            case 0:
                break;
            case 1:
                cart.sortByTitle();
                System.out.println("Sorted by Title!");
            case 2:
                cart.sortByPrice();
                break;
        }
    }

    public static void removeMediaFromCart(){
        title = "";
        while(true) {
            System.out.print("Enter title: ");
            title = sc.nextLine();
            Media product = cart.searchByTitle(title);
            if(product != null) {
                cart.removeMedia(product);
                break;
            }
        }
    }
}
