package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

public class Cart {
    private final ObservableList<Media> itemsOrdered
            = FXCollections.observableArrayList();

    public void addMedia(Media media) {
        itemsOrdered.add(media);
        StringBuilder sb = new StringBuilder();
        sb.append("Item ").append(media.getTitle()).append(" have been added to cart");
        System.out.println(sb);
    }

    public void removeMedia(Media media) {
        itemsOrdered.remove(media);
        StringBuilder sb = new StringBuilder();
        sb.append("Item ").append(media.getTitle()).append(" have been removed from cart");
        System.out.println(sb);
    }

    public float totalCost(){
        float total = 0;
        for( Media item : itemsOrdered){
            total+=item.getCost();
        }
        return total;
    }

    public void print(){
        System.out.println("**************************CART****************************");
        System.out.println("Ordered Items:");
        int counter = 0;
        for( Media item : itemsOrdered){
            System.out.println(counter + ". " + item.toString());
            counter++;
        }
        System.out.println("Total cost "+ totalCost());
        System.out.println("***********************************************************");
    }

    public Media searchByTitle(String title){
        for(Media item : itemsOrdered){
            if(((DigitalVideoDisc)item).isMatch(title)) return item;
        }
        System.out.println("There is no such product");
        return null;
    }

    public Media searchByID(int x){
        for(Media item : itemsOrdered){
            if(item.getId() == x) return item;
        }
        System.out.println("There is no such product");
        return null;
    }

    public void sortByTitle() {
        itemsOrdered.sort(Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByPrice() {
        itemsOrdered.sort(Media.COMPARE_BY_COST_TITLE);
    }

    public void placeAnOrder(){
        itemsOrdered.clear();
        System.out.println("Order have been placed");
    }

    public ObservableList<Media> getItemsOrdered(){
        return itemsOrdered;
    }

    public void emptyCart() {
        itemsOrdered.clear();
    }
}
