package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

import java.util.Random;

public class DigitalVideoDisc extends Disc implements Playable{

    public DigitalVideoDisc(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public DigitalVideoDisc(int id, String title, String category,float cost , String director,int length){
        super(id, title, category, cost, length, director);
    }

    public DigitalVideoDisc(String title){
        super(new Random().nextInt(), title, null, 0);
    }

    public boolean isMatch(String title) {
        return this.getTitle().equals(title);
    }

    @Override
    public void play() throws PlayerException {
        if(this.getLength()>0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        } else {
            throw new PlayerException("ERROR: DVD length is non-positive");
        }
    }

    @Override
    public String toString() {
        return  super.toString();
    }

}
