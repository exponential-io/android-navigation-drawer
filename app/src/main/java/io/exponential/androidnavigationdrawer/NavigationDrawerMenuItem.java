package io.exponential.androidnavigationdrawer;

/**
 * Individual NavigationDrawer menu item. This is a basic data model.
 */
public class NavigationDrawerMenuItem {
    private int imageId;
    private String title;
    private int count;

    public NavigationDrawerMenuItem(int imageId, String title, int count) {
        this.imageId = imageId;
        this.title = title;
        this.count = count;
    }

    public NavigationDrawerMenuItem() {
        // empty constructor
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
