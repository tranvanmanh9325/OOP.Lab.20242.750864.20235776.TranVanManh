package hust.soict.hedspi.test.cart;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class TestStore {
    public static void main(String[] args) {
        // Tao doi tuong store
        Store store = new Store();

        // Tao cac doi tuong DVD
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99f);

        // Them DVD vao store
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        // In danh sach DVD trong store
        System.out.println("\nDanh sach DVD sau khi them:");
        store.print();

        // Xoa 1 DVD khoi store
        store.removeMedia(dvd2);

        // In lai danh sach DVD trong store sau khi xoa
        System.out.println("\nDanh sach DVD sau khi xoa Star Wars:");
        store.print();
    }
}

