package service;

import model.BorrowRecord;
import model.LibraryItem;
import model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles borrowing and returning of library items.
 * Demonstrates: Lambdas, Streams, Exception handling
 */
public class BorrowingService {

    private final List<BorrowRecord> records;
    private static final int MAX_BORROW_LIMIT = 3;

    public BorrowingService() {
        this.records = new ArrayList<>();
    }

    public BorrowRecord borrowItem(Member member, LibraryItem item) {
        // Validation
        if (!item.isAvailable()) {
            System.out.println("  ✘ Item '" + item.getTitle() + "' is not available.");
            return null;
        }
        if (member.getActiveBorrowCount() >= MAX_BORROW_LIMIT) {
            System.out.println("  ✘ Member has reached the borrow limit of " + MAX_BORROW_LIMIT + ".");
            return null;
        }

        // Create record
        BorrowRecord record = new BorrowRecord(member, item);
        item.setAvailable(false);
        member.addBorrowRecord(record);
        records.add(record);

        System.out.println("  ✔ Borrow successful! Record ID: " + record.getRecordId());
        System.out.println("  Due date: " + record.getDueDate());
        return record;
    }

    public boolean returnItem(String recordId) {
        BorrowRecord record = findRecordById(recordId);
        if (record == null) {
            System.out.println("  ✘ Record not found: " + recordId);
            return false;
        }
        if (record.isReturned()) {
            System.out.println("  ✘ Item already returned.");
            return false;
        }

        record.returnItem();
        double fine = record.calculateFine();
        System.out.println("  ✔ Item returned successfully.");
        if (fine > 0) {
            System.out.printf("  ⚠ Overdue fine: €%.2f%n", fine);
        } else {
            System.out.println("  No fine incurred.");
        }
        return true;
    }

    public BorrowRecord findRecordById(String id) {
        return records.stream()
                .filter(r -> r.getRecordId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public List<BorrowRecord> getActiveRecords() {
        return records.stream()
                .filter(r -> !r.isReturned())
                .collect(Collectors.toList());
    }

    public List<BorrowRecord> getOverdueRecords() {
        return records.stream()
                .filter(BorrowRecord::isOverdue) // method reference
                .collect(Collectors.toList());
    }

    public List<BorrowRecord> getRecordsForMember(String memberId) {
        return records.stream()
                .filter(r -> r.getMember().getMemberId().equalsIgnoreCase(memberId))
                .collect(Collectors.toList());
    }

    public void printAllRecords() {
        if (records.isEmpty()) {
            System.out.println("  No borrow records found.");
            return;
        }
        records.forEach(r -> System.out.println("  " + r));
    }

    public List<BorrowRecord> getAllRecords() { return records; }
}
