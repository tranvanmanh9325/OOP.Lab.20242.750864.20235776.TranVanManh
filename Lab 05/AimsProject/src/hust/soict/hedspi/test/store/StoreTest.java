package hust.soict.hedspi.test.store;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

import javax.naming.LimitExceededException;

public class StoreTest {
    public static void main(String[] args) throws LimitExceededException {
        // Create a new store
        Store store = new Store();

        // Create new DVD objects and add them to the store
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1,"The Lion King",
                "Animation", 19.95f, "Roger Allers", 87 );
        store.addMedia(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2,"Star Wars",
                "Science Fiction", 24.95f, "George Lucas", 87);
        store.addMedia(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Aladin",
                "Animation",18.99f );
        store   .addMedia(dvd3);

        // Remove a DVD object from the store
        store.removeMedia(dvd2);
    }
}
