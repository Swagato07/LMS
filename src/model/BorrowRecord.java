package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Records a borrowing transaction between a Member and a LibraryItem.
 * Demonstrates: Composition, Encapsulation
 */
public class BorrowRecord {

    private final String recordId;
    private final Member member;
    private final LibraryItem item;
    private final LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;

    private static final int DEFAULT_LOAN_DAYS = 14;
    private static final double FINE_PER_DAY = 0.50;

    private static int recordCount = 0;

    public BorrowRecord(Member member, LibraryItem item) {
        recordCount++;
        this.recordId = "BR" + String.format("%04d", recordCount);
        this.member = member;
        this.item = item;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(DEFAULT_LOAN_DAYS);
        this.returned = false;
    }

    public void returnItem() {
        this.returnDate = LocalDate.now();
        this.returned = true;
        item.setAvailable(true);
    }

    // Overloaded — return on a specific date (for testing/backdating)
    public void returnItem(LocalDate date) {
        this.returnDate = date;
        this.returned = true;
        item.setAvailable(true);
    }

    public double calculateFine() {
        if (!returned) {
            long daysOverdue = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
            return daysOverdue > 0 ? daysOverdue * FINE_PER_DAY : 0.0;
        } else {
            long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
            return daysOverdue > 0 ? daysOverdue * FINE_PER_DAY : 0.0;
        }
    }

    public boolean isOverdue() {
        if (returned) return false;
        return LocalDate.now().isAfter(dueDate);
    }

    public void printDetails() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│           BORROW RECORD                  │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.printf("│  Record ID  : %-27s│%n", recordId);
        System.out.printf("│  Member     : %-27s│%n", member.getName());
        System.out.printf("│  Item       : %-27s│%n", item.getTitle());
        System.out.printf("│  Borrow Date: %-27s│%n", borrowDate);
        System.out.printf("│  Due Date   : %-27s│%n", dueDate);
        System.out.printf("│  Returned   : %-27s│%n", returned ? returnDate.toString() : "Not yet");
        double fine = calculateFine();
        System.out.printf("│  Fine       : %-27s│%n", fine > 0 ? "€" + String.format("%.2f", fine) : "None");
        System.out.println("└─────────────────────────────────────────┘");
    }

    // Getters
    public String getRecordId()    { return recordId; }
    public Member getMember()      { return member; }
    public LibraryItem getItem()   { return item; }
    public LocalDate getBorrowDate(){ return borrowDate; }
    public LocalDate getDueDate()  { return dueDate; }
    public LocalDate getReturnDate(){ return returnDate; }
    public boolean isReturned()    { return returned; }

    @Override
    public String toString() {
        return String.format("[%s] %s borrowed '%s' on %s", recordId, member.getName(), item.getTitle(), borrowDate);
    }
}
