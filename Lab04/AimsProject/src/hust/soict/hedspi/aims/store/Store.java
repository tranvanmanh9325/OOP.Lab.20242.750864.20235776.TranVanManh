package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;

public class Store {
    // Khai báo số lượng tối đa các media trong store
    public static final int MAX_NUMBERS = 100;
    // Sử dụng ArrayList để lưu các đối tượng Media (có thể là DVD, Book, CD, ...)
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();
    // Phương thức thêm media vào store
    public void addMedia(Media media) {
        if (itemsInStore.size() < MAX_NUMBERS) {
            itemsInStore.add(media);
            System.out.println(media.getTitle() + " has been added to the store.");
        } else {
            System.out.println("Store is full! Cannot add more items.");
        }
    }
    // Phương thức xóa media khỏi store
    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println(media.getTitle() + " has been removed from the store.");
        } else {
            System.out.println("Item not found in the store.");
        }
    }
    // Phương thức in danh sách media trong store
    public void print() {
        System.out.println("********************************STORE********************************");
        for (int i = 0; i < itemsInStore.size(); i++) {
            System.out.println((i + 1) + ". " + itemsInStore.get(i).toString());
        }
        System.out.println("********************************************************************");
    }
    // Getter cho danh sách media nếu cần dùng từ bên ngoài
    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }

    public void showMediaDetails(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Details: " + media.toString());
            }
        }
    	
    }
}
