package model;

/**
 * Represents a magazine in the library.
 * Demonstrates: Inheritance, Method Overriding
 */
public class Magazine extends LibraryItem {

    private String publisher;
    private int issueNumber;
    private String month;

    public Magazine(String id, String title, String publisher, int issueNumber, String month, String genre) {
        super(id, title, genre);
        this.publisher = publisher;
        this.issueNumber = issueNumber;
        this.month = month;
    }

    @Override
    public String getItemType() {
        return "Magazine";
    }

    @Override
    public void printDetails() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│            MAGAZINE DETAILS              │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.printf("│  ID        : %-28s│%n", getId());
        System.out.printf("│  Title     : %-28s│%n", getTitle());
        System.out.printf("│  Publisher : %-28s│%n", publisher);
        System.out.printf("│  Issue     : %-28s│%n", "#" + issueNumber);
        System.out.printf("│  Month     : %-28s│%n", month);
        System.out.printf("│  Genre     : %-28s│%n", getGenre());
        System.out.printf("│  Status    : %-28s│%n", isAvailable() ? "Available" : "Borrowed");
        System.out.println("└─────────────────────────────────────────┘");
    }

    // Getters & Setters
    public String getPublisher()  { return publisher; }
    public int getIssueNumber()   { return issueNumber; }
    public String getMonth()      { return month; }

    public void setPublisher(String publisher) { this.publisher = publisher; }
    public void setIssueNumber(int issueNumber){ this.issueNumber = issueNumber; }
    public void setMonth(String month)         { this.month = month; }
}
