package hust.soict.hedspi.aims.media;

public class Track implements Playable {
    private String title;
    private int length;

    // Constructor
    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }
    // Getter
    public String getTitle() {
        return title;
    }
    public int getLength() {
        return length;
    }
    // Ghi de equals de so sanh 2 track dua tren title va length
    @Override
    public boolean equals(Object obj) {
        // Neu cung la mot doi tuong
        if (this == obj) return true;

        // Kiem tra null hoac khong cung kieu
        if (!(obj instanceof Track)) return false;

        Track other = (Track) obj;

        // So sanh title (khong phan biet hoa thuong) va length
        return this.title != null
                && this.title.equalsIgnoreCase(other.getTitle())
                && this.length == other.getLength();
    }
    // Phuong thuc play
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }
}
