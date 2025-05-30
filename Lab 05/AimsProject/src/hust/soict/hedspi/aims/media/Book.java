package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.DuplicateAuthorException;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private final List<String> authors = new ArrayList<String>();

    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public Book(int id, String title, String category, float cost, List<String> authors) {
        super(id, title, category, cost);
        this.authors.addAll(authors);
    }

    public void addAuthor(String authorName) throws DuplicateAuthorException {
        if (authors.contains(authorName)) {
            throw new DuplicateAuthorException("Author \"" + authorName + "\" already exists.");
        }
        authors.add(authorName);
    }

    public void removeAuthor(String authorName){
        authors.remove(authorName);
    }

    public String getAuthors(){
        return String.join(", ", authors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if(!authors.isEmpty()) sb.append(", Authors: ").append(authors);
        return sb.toString();
    }
}
