package model;

/**
 * Represents a physical book in the library.
 * Demonstrates: Inheritance, Method Overriding, Encapsulation
 */
public class Book extends LibraryItem {

    private String author;
    private String isbn;
    private int totalPages;

    public Book(String id, String title, String author, String isbn, String genre, int totalPages) {
        super(id, title, genre); // Call parent constructor
        this.author = author;
        this.isbn = isbn;
        this.totalPages = totalPages;
    }

    // Overriding abstract method
    @Override
    public String getItemType() {
        return "Book";
    }

    // Overriding abstract method
    @Override
    public void printDetails() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│              BOOK DETAILS                │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.printf("│  ID      : %-30s│%n", getId());
        System.out.printf("│  Title   : %-30s│%n", getTitle());
        System.out.printf("│  Author  : %-30s│%n", author);
        System.out.printf("│  ISBN    : %-30s│%n", isbn);
        System.out.printf("│  Genre   : %-30s│%n", getGenre());
        System.out.printf("│  Pages   : %-30s│%n", totalPages);
        System.out.printf("│  Status  : %-30s│%n", isAvailable() ? "Available" : "Borrowed");
        System.out.println("└─────────────────────────────────────────┘");
    }

    // Overloaded constructor — minimal book (no pages info)
    public Book(String id, String title, String author, String genre) {
        this(id, title, author, "N/A", genre, 0);
    }

    // Getters & Setters
    public String getAuthor()    { return author; }
    public String getIsbn()      { return isbn; }
    public int getTotalPages()   { return totalPages; }

    public void setAuthor(String author)       { this.author = author; }
    public void setIsbn(String isbn)           { this.isbn = isbn; }
    public void setTotalPages(int totalPages)  { this.totalPages = totalPages; }
}
