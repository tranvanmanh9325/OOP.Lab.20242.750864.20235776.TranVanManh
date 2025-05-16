package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;

    // Getter va Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public void setCategory(String category) {
        this.category = category;
    }
    public float getCost() {
        return cost;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }
    // Constructor co tham so
    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }
    // Kiem tra ten co giong khong (khong phan biet hoa thuong)
    public boolean isMatch(String title) {
        return this.getTitle().equalsIgnoreCase(title);
    }
    // Override equals de so sanh theo title
    @Override
    public boolean equals(Object o) {
        // Kiem tra neu cung doi tuong
        if (this == o) return true;

        // Kiem tra kieu
        if (!(o instanceof Media)) return false;

        Media media = (Media) o;

        // So sanh title (khong phan biet hoa thuong)
        return this.title != null && this.title.equalsIgnoreCase(media.getTitle());
    }
    // Comparator de sap xep theo tieu de, roi den gia (giam dan)
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new Comparator<Media>() {
        @Override
        public int compare(Media m1, Media m2) {
            // So sanh theo tieu de
            int titleComparison = m1.getTitle().compareTo(m2.getTitle());
            if (titleComparison != 0) {
                return titleComparison;
            }
            // Neu tieu de giong nhau, so sanh theo gia giam dan
            return Float.compare(m2.getCost(), m1.getCost()); // Sap xep giam dan
        }
    };
    // Comparator de sap xep theo gia tri, roi den tieu de
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new Comparator<Media>() {
        @Override
        public int compare(Media m1, Media m2) {
            // So sanh theo gia tri giam dan
            int costComparison = Float.compare(m2.getCost(), m1.getCost()); // Sap xep giam dan
            if (costComparison != 0) {
                return costComparison;
            }
            // Neu gia giong nhau, so sanh theo tieu de
            return m1.getTitle().compareTo(m2.getTitle());
        }
    };
}
