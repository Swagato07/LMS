package model;

/**
 * Represents a DVD in the library.
 * Demonstrates: Inheritance, Method Overriding
 */
public class DVD extends LibraryItem {

    private String director;
    private int durationMinutes;
    private String rating; // e.g. PG, PG-13, R

    public DVD(String id, String title, String director, int durationMinutes, String rating, String genre) {
        super(id, title, genre);
        this.director = director;
        this.durationMinutes = durationMinutes;
        this.rating = rating;
    }

    @Override
    public String getItemType() {
        return "DVD";
    }

    @Override
    public void printDetails() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│              DVD DETAILS                 │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.printf("│  ID        : %-28s│%n", getId());
        System.out.printf("│  Title     : %-28s│%n", getTitle());
        System.out.printf("│  Director  : %-28s│%n", director);
        System.out.printf("│  Duration  : %-28s│%n", durationMinutes + " mins");
        System.out.printf("│  Rating    : %-28s│%n", rating);
        System.out.printf("│  Genre     : %-28s│%n", getGenre());
        System.out.printf("│  Status    : %-28s│%n", isAvailable() ? "Available" : "Borrowed");
        System.out.println("└─────────────────────────────────────────┘");
    }

    // Getters & Setters
    public String getDirector()      { return director; }
    public int getDurationMinutes()  { return durationMinutes; }
    public String getRating()        { return rating; }

    public void setDirector(String director)          { this.director = director; }
    public void setDurationMinutes(int duration)      { this.durationMinutes = duration; }
    public void setRating(String rating)              { this.rating = rating; }
}
