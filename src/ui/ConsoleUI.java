package ui;

import model.*;
import service.BorrowingService;
import service.LibraryCatalog;
import service.MemberService;
import util.DataSeeder;
import util.InputHelper;

import java.util.List;

/**
 * Main console UI — drives all user interaction.
 * Demonstrates: Composition, Lambda usage, all services integrated
 */
public class ConsoleUI {

    private final LibraryCatalog catalog;
    private final MemberService memberService;
    private final BorrowingService borrowingService;

    public ConsoleUI() {
        this.catalog = new LibraryCatalog();
        this.memberService = new MemberService();
        this.borrowingService = new BorrowingService();
    }

    public void start() {
        printBanner();
        System.out.println("  Loading sample data...");
        DataSeeder.seed(catalog, memberService);

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = InputHelper.readInt("  Enter choice: ", 0, 6);
            System.out.println();
            switch (choice) {
                case 1 -> catalogMenu();
                case 2 -> memberMenu();
                case 3 -> borrowMenu();
                case 4 -> returnMenu();
                case 5 -> reportsMenu();
                case 6 -> searchMenu();
                case 0 -> running = false;
            }
        }
        System.out.println("\n  Thank you for using the Library System. Goodbye!");
    }

    // ─────────────────────────── MAIN MENU ───────────────────────────

    private void printMainMenu() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║         LIBRARY MANAGEMENT SYSTEM        ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  1. Manage Catalog (Books/DVDs/Magazines) ║");
        System.out.println("║  2. Manage Members                       ║");
        System.out.println("║  3. Borrow an Item                       ║");
        System.out.println("║  4. Return an Item                       ║");
        System.out.println("║  5. Reports & Statistics                 ║");
        System.out.println("║  6. Search                               ║");
        System.out.println("║  0. Exit                                 ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    // ─────────────────────────── CATALOG MENU ───────────────────────────

    private void catalogMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n── CATALOG MENU ──────────────────────────");
            System.out.println("  1. View all items");
            System.out.println("  2. Add a Book");
            System.out.println("  3. Add a Magazine");
            System.out.println("  4. Add a DVD");
            System.out.println("  5. View item details");
            System.out.println("  6. Remove an item");
            System.out.println("  0. Back");
            int choice = InputHelper.readInt("  Enter choice: ", 0, 6);
            System.out.println();
            switch (choice) {
                case 1 -> {
                    System.out.println("  ALL ITEMS (" + catalog.getTotalCount() + " total):");
                    catalog.printAllItems();
                }
                case 2 -> addBook();
                case 3 -> addMagazine();
                case 4 -> addDVD();
                case 5 -> viewItemDetails();
                case 6 -> removeItem();
                case 0 -> back = true;
            }
            if (choice != 0) InputHelper.pause();
        }
    }

    private void addBook() {
        System.out.println("  ── Add New Book ──");
        String id    = InputHelper.readString("  Item ID (e.g. B006): ");
        String title = InputHelper.readString("  Title: ");
        String author= InputHelper.readString("  Author: ");
        String isbn  = InputHelper.readString("  ISBN: ");
        String genre = InputHelper.readString("  Genre: ");
        int pages    = InputHelper.readInt("  Pages: ");
        catalog.addItem(new Book(id, title, author, isbn, genre, pages));
    }

    private void addMagazine() {
        System.out.println("  ── Add New Magazine ──");
        String id        = InputHelper.readString("  Item ID (e.g. M004): ");
        String title     = InputHelper.readString("  Title: ");
        String publisher = InputHelper.readString("  Publisher: ");
        int issue        = InputHelper.readInt("  Issue Number: ");
        String month     = InputHelper.readString("  Month: ");
        String genre     = InputHelper.readString("  Genre: ");
        catalog.addItem(new Magazine(id, title, publisher, issue, month, genre));
    }

    private void addDVD() {
        System.out.println("  ── Add New DVD ──");
        String id       = InputHelper.readString("  Item ID (e.g. D004): ");
        String title    = InputHelper.readString("  Title: ");
        String director = InputHelper.readString("  Director: ");
        int duration    = InputHelper.readInt("  Duration (mins): ");
        String rating   = InputHelper.readString("  Rating (e.g. PG, PG-13, R): ");
        String genre    = InputHelper.readString("  Genre: ");
        catalog.addItem(new DVD(id, title, director, duration, rating, genre));
    }

    private void viewItemDetails() {
        String id = InputHelper.readString("  Enter Item ID: ");
        LibraryItem item = catalog.findById(id);
        if (item == null) {
            System.out.println("  ✘ Item not found.");
        } else {
            item.printDetails();
        }
    }

    private void removeItem() {
        String id = InputHelper.readString("  Enter Item ID to remove: ");
        boolean removed = catalog.removeItem(id);
        System.out.println(removed ? "  ✔ Item removed." : "  ✘ Item not found.");
    }

    // ─────────────────────────── MEMBER MENU ───────────────────────────

    private void memberMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n── MEMBER MENU ───────────────────────────");
            System.out.println("  1. View all members");
            System.out.println("  2. Register new member");
            System.out.println("  3. View member details");
            System.out.println("  4. View member borrow history");
            System.out.println("  0. Back");
            int choice = InputHelper.readInt("  Enter choice: ", 0, 4);
            System.out.println();
            switch (choice) {
                case 1 -> {
                    System.out.println("  ALL MEMBERS:");
                    memberService.printAllMembers();
                }
                case 2 -> {
                    String name  = InputHelper.readString("  Name: ");
                    String email = InputHelper.readString("  Email: ");
                    String phone = InputHelper.readString("  Phone: ");
                    memberService.registerMember(name, email, phone);
                }
                case 3 -> {
                    String id = InputHelper.readString("  Enter Member ID: ");
                    Member m = memberService.findById(id);
                    if (m == null) System.out.println("  ✘ Member not found.");
                    else m.printDetails();
                }
                case 4 -> viewMemberHistory();
                case 0 -> back = true;
            }
            if (choice != 0) InputHelper.pause();
        }
    }

    private void viewMemberHistory() {
        String id = InputHelper.readString("  Enter Member ID: ");
        Member member = memberService.findById(id);
        if (member == null) { System.out.println("  ✘ Member not found."); return; }

        List<BorrowRecord> history = borrowingService.getRecordsForMember(id);
        if (history.isEmpty()) {
            System.out.println("  No borrow history for " + member.getName() + ".");
        } else {
            System.out.println("  Borrow history for " + member.getName() + ":");
            history.forEach(r -> System.out.println("  " + r));
        }
    }

    // ─────────────────────────── BORROW MENU ───────────────────────────

    private void borrowMenu() {
        System.out.println("── BORROW AN ITEM ────────────────────────");
        System.out.println("  Available items:");
        List<LibraryItem> available = catalog.getAvailableItems();
        if (available.isEmpty()) { System.out.println("  No items available."); InputHelper.pause(); return; }
        // Lambda to print available items
        available.forEach(item -> System.out.println("    " + item.getSummary(false)));

        System.out.println();
        String itemId = InputHelper.readString("  Enter Item ID to borrow: ");
        LibraryItem item = catalog.findById(itemId);
        if (item == null) { System.out.println("  ✘ Item not found."); InputHelper.pause(); return; }

        System.out.println("\n  Registered members:");
        memberService.printAllMembers();
        String memberId = InputHelper.readString("\n  Enter Member ID: ");
        Member member = memberService.findById(memberId);
        if (member == null) { System.out.println("  ✘ Member not found."); InputHelper.pause(); return; }

        borrowingService.borrowItem(member, item);
        InputHelper.pause();
    }

    // ─────────────────────────── RETURN MENU ───────────────────────────

    private void returnMenu() {
        System.out.println("── RETURN AN ITEM ────────────────────────");
        List<BorrowRecord> active = borrowingService.getActiveRecords();
        if (active.isEmpty()) { System.out.println("  No items are currently borrowed."); InputHelper.pause(); return; }

        System.out.println("  Currently borrowed items:");
        active.forEach(r -> System.out.println("    " + r));

        String recordId = InputHelper.readString("\n  Enter Borrow Record ID to return: ");
        borrowingService.returnItem(recordId);
        InputHelper.pause();
    }

    // ─────────────────────────── REPORTS MENU ───────────────────────────

    private void reportsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n── REPORTS & STATISTICS ──────────────────");
            System.out.println("  1. Summary statistics");
            System.out.println("  2. All borrowed items");
            System.out.println("  3. Overdue items");
            System.out.println("  4. All borrow records");
            System.out.println("  5. Items by type");
            System.out.println("  0. Back");
            int choice = InputHelper.readInt("  Enter choice: ", 0, 5);
            System.out.println();
            switch (choice) {
                case 1 -> printSummary();
                case 2 -> {
                    List<LibraryItem> borrowed = catalog.getBorrowedItems();
                    System.out.println("  Currently borrowed (" + borrowed.size() + " items):");
                    borrowed.forEach(i -> System.out.println("    " + i));
                }
                case 3 -> {
                    List<BorrowRecord> overdue = borrowingService.getOverdueRecords();
                    if (overdue.isEmpty()) System.out.println("  No overdue items.");
                    else overdue.forEach(r -> { r.printDetails(); System.out.println(); });
                }
                case 4 -> borrowingService.printAllRecords();
                case 5 -> {
                    String type = InputHelper.readString("  Type (Book/Magazine/DVD): ");
                    List<LibraryItem> typed = catalog.filterByType(type);
                    if (typed.isEmpty()) System.out.println("  No items of type: " + type);
                    else typed.forEach(i -> System.out.println("    " + i.getSummary(true)));
                }
                case 0 -> back = true;
            }
            if (choice != 0) InputHelper.pause();
        }
    }

    private void printSummary() {
        int total     = catalog.getTotalCount();
        int available = catalog.getAvailableItems().size();
        int borrowed  = catalog.getBorrowedItems().size();
        int overdue   = borrowingService.getOverdueRecords().size();
        int members   = memberService.getAllMembers().size();

        System.out.println("  ┌─────────────────────────────────────┐");
        System.out.println("  │          LIBRARY STATISTICS         │");
        System.out.println("  ├─────────────────────────────────────┤");
        System.out.printf("  │  Total items     : %-17d│%n", total);
        System.out.printf("  │  Available       : %-17d│%n", available);
        System.out.printf("  │  Borrowed        : %-17d│%n", borrowed);
        System.out.printf("  │  Overdue         : %-17d│%n", overdue);
        System.out.printf("  │  Total members   : %-17d│%n", members);
        System.out.println("  └─────────────────────────────────────┘");
    }

    // ─────────────────────────── SEARCH MENU ───────────────────────────

    private void searchMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n── SEARCH ────────────────────────────────");
            System.out.println("  1. Search items by title keyword");
            System.out.println("  2. Search items by genre");
            System.out.println("  3. Search members by name");
            System.out.println("  4. View all items sorted by title");
            System.out.println("  0. Back");
            int choice = InputHelper.readInt("  Enter choice: ", 0, 4);
            System.out.println();
            switch (choice) {
                case 1 -> {
                    String kw = InputHelper.readString("  Keyword: ");
                    List<LibraryItem> results = catalog.searchByTitle(kw);
                    printItemList(results);
                }
                case 2 -> {
                    String genre = InputHelper.readString("  Genre: ");
                    List<LibraryItem> results = catalog.searchByGenre(genre);
                    printItemList(results);
                }
                case 3 -> {
                    String name = InputHelper.readString("  Name keyword: ");
                    var results = memberService.searchByName(name);
                    if (results.isEmpty()) System.out.println("  No members found.");
                    else results.forEach(m -> System.out.println("  " + m));
                }
                case 4 -> {
                    System.out.println("  Items sorted by title:");
                    catalog.getAllItemsSortedByTitle()
                           .forEach(i -> System.out.println("    " + i.getSummary(true)));
                }
                case 0 -> back = true;
            }
            if (choice != 0) InputHelper.pause();
        }
    }

    private void printItemList(List<LibraryItem> items) {
        if (items.isEmpty()) System.out.println("  No results found.");
        else items.forEach(i -> System.out.println("  " + i.getSummary(true)));
    }

    // ─────────────────────────── BANNER ───────────────────────────

    private void printBanner() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                                              ║");
        System.out.println("║       📚  LIBRARY MANAGEMENT SYSTEM  📚      ║");
        System.out.println("║            Java Console Application          ║");
        System.out.println("║                                              ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
    }
}
