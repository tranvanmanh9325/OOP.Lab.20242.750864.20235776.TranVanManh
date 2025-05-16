package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class Book extends Media {
    // Khai bao danh sach tac gia
    private ArrayList<String> authors = new ArrayList<>();

    // Constructor co tham so
    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost); // Goi constructor cua Media
    }

    // Phuong thuc them tac gia
    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        }
    }

    // Phuong thuc xoa tac gia
    public void removeAuthor(String authorName) {
        authors.remove(authorName);
    }

    // Ghi de equals de so sanh title
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;

        Book other = (Book) obj;
        return this.getTitle() != null && this.getTitle().equalsIgnoreCase(other.getTitle());
    }

    // Ghi de toString de in thong tin day du
    @Override
    public String toString() {
        return "Book - " + getTitle() + " - " + getCategory() + ": " + getCost() + " $";
    }
}
