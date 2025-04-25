package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
    private static int nbDigitalVideoDiscs = 0; // static member để gán id

    public static int getNbDigitalVideoDiscs() {
        return nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String title) {
        super(title); // gọi constructor mặc định từ Disc
        setTitle(title);
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs); // assign unique id
    }

    public DigitalVideoDisc(String category, String title, float cost) {
        super(category, title, cost); // gọi constructor mặc định từ Disc
        setCategory(category);
        setTitle(title);
        setCost(cost);
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs);
    }

    public DigitalVideoDisc(String director, String category, String title, float cost) {
        super(category, title, cost);
        setDirector(director);
        setCategory(category);
        setTitle(title);
        setCost(cost);
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(category, title, cost);
        setTitle(title);
        setCategory(category);
        setDirector(director);
        setLength(length);
        setCost(cost);
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs);
    }

    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " +
                (getCategory() != null ? getCategory() : "Unknown") + " - " +
                (getDirector() != null ? getDirector() : "Unknown") + " - " +
                getLength() + ": " + getCost() + " $";
    }

    public boolean isMatch(String title) {
        return getTitle() != null && getTitle().toLowerCase().contains(title.toLowerCase());
    }
}
