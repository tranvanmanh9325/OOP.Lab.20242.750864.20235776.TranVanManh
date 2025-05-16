package hust.soict.hedspi.aims.cart;

import java.util.ArrayList;
import java.util.Collections; // Them import Collections

import hust.soict.hedspi.aims.media.Media;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    public static int items = 0;
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    // Them media vao gio hang
    public void addMedia(Media media) {
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
        	itemsOrdered.add(media);
            items++;
            System.out.println("Added: " + media.getTitle());
            System.out.println("Numbers of Media Ordered: " + items);
        } else {
            System.out.println("The cart is full!");
        }
    }
    // Xoa media khoi gio hang
    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
        	items--;
            System.out.println("Removed: " + media.getTitle());
        } else {
            System.out.println("Item not found in the cart.");
        }
    }
    // Hien thi cac muc trong gio hang
    public void displayCart() {
        if (itemsOrdered.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            System.out.println("Current items in the cart:");
            for (Media media : itemsOrdered) {
                System.out.println(media.getTitle() + " - " + media.getCost() + "$");
            }
        }
    }
    // Tinh tong chi phi cua gio hang
    public float totalCost() {
        float res = 0;
        for (Media m : itemsOrdered) {
            res += m.getCost();
        }
        return res;
    }
    // In thong tin gio hang
    public void print() {
        System.out.println("********************************CART********************************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost() + "$");
        System.out.println("********************************************************************");
    }
    // Tim kiem media theo ID
    public void searchById(int id) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found: " + media.toString());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No media found with ID: " + id);
        }
    }
    // Tim kiem media theo title
    public void searchByTitle(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.isMatch(title)) {
                System.out.println("Found: " + media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No media found with title: " + title);
        }
    }
    // Sap xep gio hang theo tieu de va gia (giam dan)
    public void sortByTitleAndCost() {
        System.out.println("Sorting cart by title and cost:");
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        displayCart();
    }
    // Sap xep gio hang theo gia va tieu de
    public void sortByCostAndTitle() {
        System.out.println("Sorting cart by cost and title:");
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        displayCart();
    }
    // Getter cho danh sach media neu can dung tu ben ngoai
    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }
}
