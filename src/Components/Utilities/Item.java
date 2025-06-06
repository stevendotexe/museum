package Components.Utilities;

public class Item {
    private int id;
    private String name;
    private String imageUrl;
    private String description;
    private String locationFound;
    private String category;
    private String dateDiscovered;
    private String dateAdded;
    private String modifiedAt;
    private boolean isExhibited;

    public Item(int id, String name, String imageUrl, String description, String locationFound, 
                String category, String dateDiscovered, String dateAdded, String modifiedAt, boolean isExhibited) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.locationFound = locationFound;
        this.category = category;
        this.dateDiscovered = dateDiscovered;
        this.dateAdded = dateAdded;
        this.modifiedAt = modifiedAt;
        this.isExhibited = isExhibited;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public String getCategory() {
        return category;
    }

    public String getDateDiscovered() {
        return dateDiscovered;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public boolean isExhibited() {
        return isExhibited;
    }
} 