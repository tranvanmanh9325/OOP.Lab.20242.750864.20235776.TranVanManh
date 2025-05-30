package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

import java.util.Objects;

public class Track implements Playable {
    private String title;
    private int length;

    public int getLength() {
        return length;
    }

    public String getTitle() {
        return title;
    }

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    @Override
    public void play() throws PlayerException {
        if(this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        }  else {
            throw new PlayerException("ERROR: DVD length is non-positive");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track otherTrack = (Track) o;
        return Objects.equals(this.getTitle(), otherTrack.getTitle())
                && this.getLength() == otherTrack.getLength();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getLength());
    }

    @Override
    public String toString() {
        return title + " (" + length + " sec)";
    }

}
