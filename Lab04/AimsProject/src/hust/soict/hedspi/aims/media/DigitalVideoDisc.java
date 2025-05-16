package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc {
    private static int nbDigitalVideoDiscs = 0;

    // Constructor chi co title
    public DigitalVideoDisc(String title) {
        super(++nbDigitalVideoDiscs, title, null, 0, null, 0); // category = null, cost = 0, director = null, length = 0
    }

    // Constructor co title, category, cost
    public DigitalVideoDisc(String title, String category, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, null, 0);
    }

    // Constructor co title, category, director, cost
    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, director, 0);
    }

    // Constructor day du
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, director, length);
    }

    // Tra ve tong so DVD da tao
    public static int getTotalDVDs() {
        return nbDigitalVideoDiscs;
    }

    // Phuong thuc in thong tin DVD
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength()
                + ": " + getCost() + " $";
    }
}
