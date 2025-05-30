package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;

import javax.naming.LimitExceededException;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private final int MAX_STORE_SIZE = 100;
    private final List<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media) throws LimitExceededException {
        if(itemsInStore.size() < MAX_STORE_SIZE){
            if(itemsInStore.contains(media)){
                return;
            }
            itemsInStore.add(media);
            StringBuilder sb = new StringBuilder();
            sb.append("Item ").append(media.getTitle()).append(" have been added to store");
            System.out.println(sb);
        } else {
            throw new LimitExceededException("ERROR: The number of media has reached its limit");
        }
    }

    public void removeMedia(Media media){
        itemsInStore.remove(media);
        StringBuilder sb = new StringBuilder();
        sb.append("Item ").append(media.getTitle()).append(" have been removed from store");
        System.out.println(sb);
    }

    public Media searchByTitle(String title){
        for(Media media : itemsInStore){
            if(media.getTitle().equals(title)){
                return media;
            }
        }
        System.out.println("No such title");
        return null;
    }

    public Media searchById(int id){
        for(Media media : itemsInStore){
            if(media.getId() == id){
                return media;
            }
        }
        System.out.println("No such id");
        return null;
    }

    public int getNumberOfMedia(){
        return itemsInStore.size();
    }

    public void print(){
        for(Media media : itemsInStore){
            System.out.println(media.toString());
        }
    }

    public List<Media> getItemsInStore(){
        return itemsInStore;
    }
}
