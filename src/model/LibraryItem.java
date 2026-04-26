package model;

import java.time.LocalDate;

/**
 * Abstract base class for all library items.
 * Demonstrates: Encapsulation, Abstraction
 */
public abstract class LibraryItem {

    // Encapsulated fields
    private final String id;
    private String title;
    private String genre;
    private boolean isAvailable;
    private LocalDate addedDate;

    // Constructor
    public LibraryItem(String id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.isAvailable = true;
        this.addedDate = LocalDate.now();
    }

    // Abstract method — must be overridden by subclasses
    public abstract String getItemType();

    // Abstract method for display details
    public abstract void printDetails();

    // Overloaded method — display with or without availability
    public String getSummary() {
        return String.format("[%s] %s (%s)", id, title, getItemType());
    }

    public String getSummary(boolean showAvailability) {
        String status = isAvailable ? "✔ Available" : "✘ Borrowed";
        return String.format("[%s] %s (%s) — %s", id, title, getItemType(), showAvailability ? status : "");
    }

    // Getters
    public String getId()        { return id; }
    public String getTitle()     { return title; }
    public String getGenre()     { return genre; }
    public boolean isAvailable() { return isAvailable; }
    public LocalDate getAddedDate() { return addedDate; }

    // Setters
    public void setTitle(String title)   { this.title = title; }
    public void setGenre(String genre)   { this.genre = genre; }
    public void setAvailable(boolean available) { this.isAvailable = available; }

    // Override Object.toString()
    @Override
    public String toString() {
        return getSummary(true);
    }
}
