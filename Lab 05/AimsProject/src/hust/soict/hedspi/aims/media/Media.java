package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media implements Comparable<Media> {

    private int id;
    private String title;
    private final String category;
    private final float cost;
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public float getCost() {
        return cost;
    }

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Media)) return false;
        Media media = (Media) obj;
        // Check for null title to avoid NullPointerException
        if (this.title == null || media.title == null) return false;
        return this.title.equals(media.title) && this.cost == media.cost;
    }

    @Override
    public int compareTo(Media other) {
        if (other == null) throw new NullPointerException("Compared media is null");
        if (this.title == null && other.title == null) return 0;
        if (this.title == null) return -1;
        if (other.title == null) return 1;

        int titleComparison = this.title.compareTo(other.title);
        if (titleComparison != 0) {
            return titleComparison;
        } else {
            return Float.compare(this.cost, other.cost);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getId() != 0) sb.append("ID: ").append(this.id);
        sb.append(" - Title: ").append(this.title);
        if (!getCategory().isEmpty()) sb.append(" - Category: ").append(this.category);
        if (getCost() != 0) sb.append(" - Cost: ").append(this.cost);
        return sb.toString();
    }
}
