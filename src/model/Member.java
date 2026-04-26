package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a library member.
 * Demonstrates: Encapsulation, Composition
 */
public class Member {

    private final String memberId;
    private String name;
    private String email;
    private String phone;
    private final List<BorrowRecord> borrowHistory;
    private static int memberCount = 0; // Static field

    public Member(String name, String email, String phone) {
        memberCount++;
        this.memberId = "M" + String.format("%03d", memberCount);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.borrowHistory = new ArrayList<>();
    }

    public void addBorrowRecord(BorrowRecord record) {
        borrowHistory.add(record);
    }

    public int getActiveBorrowCount() {
        // Lambda to count active (not yet returned) borrow records
        return (int) borrowHistory.stream()
                .filter(r -> !r.isReturned())
                .count();
    }

    public void printDetails() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│             MEMBER DETAILS               │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.printf("│  Member ID : %-28s│%n", memberId);
        System.out.printf("│  Name      : %-28s│%n", name);
        System.out.printf("│  Email     : %-28s│%n", email);
        System.out.printf("│  Phone     : %-28s│%n", phone);
        System.out.printf("│  Borrowed  : %-28s│%n", getActiveBorrowCount() + " item(s) currently");
        System.out.println("└─────────────────────────────────────────┘");
    }

    // Getters & Setters
    public String getMemberId()          { return memberId; }
    public String getName()              { return name; }
    public String getEmail()             { return email; }
    public String getPhone()             { return phone; }
    public List<BorrowRecord> getBorrowHistory() { return borrowHistory; }

    public void setName(String name)     { this.name = name; }
    public void setEmail(String email)   { this.email = email; }
    public void setPhone(String phone)   { this.phone = phone; }

    public static int getMemberCount()   { return memberCount; }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s)", memberId, name, email);
    }
}
