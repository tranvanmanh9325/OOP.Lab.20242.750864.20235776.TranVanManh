package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable {

    private final List<Track> tracks = new ArrayList<Track>();
    private String artist;

    public CompactDisc(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public CompactDisc(int id, String title, String category, float price, List<Track> tracks) {
        super(id, title, category, price);
        this.tracks.addAll(tracks);
    }

    public CompactDisc(int id, String title, String category, float price, String artist, List<Track> tracks) {
        super(id,title, category, price);
        this.tracks.addAll(tracks);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if(tracks.contains(track)) return;
        tracks.add(track);
    }

    public void removeTrack(Track track) {
        if(!tracks.contains(track)) return;
        tracks.remove(track);
    }

    public String getTracksString(){
        StringBuilder sb = new StringBuilder();
        for (Track track : tracks) {
            sb.append(track.toString()).append(",");
        }
        return sb.toString();
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public int getLength(){
        return tracks.size();
    }

    @Override
    public void play() throws PlayerException {
        if(this.getLength() > 0){
            for (Track track : tracks) {
                try{
                    track.play();
                } catch (PlayerException e) {
                    throw e;
                }
            }
        } else {
            throw new PlayerException("ERROR: CD length is non-positive");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if(artist!=null) sb.append(" - Artist: ").append(artist);
        sb.append(" - Tracks: ").append(getTracksString());
        return sb.toString();
    }
}
