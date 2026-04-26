package util;

import model.Book;
import model.DVD;
import model.Magazine;
import service.LibraryCatalog;
import service.MemberService;

/**
 * Seeds initial data into the system.
 * Demonstrates: Static methods, Object creation
 */
public class DataSeeder {

    public static void seed(LibraryCatalog catalog, MemberService memberService) {
        // Books
        catalog.addItem(new Book("B001", "Clean Code", "Robert C. Martin", "978-0132350884", "Programming", 431));
        catalog.addItem(new Book("B002", "The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565", "Fiction", 180));
        catalog.addItem(new Book("B003", "Sapiens", "Yuval Noah Harari", "978-0062316097", "History", 443));
        catalog.addItem(new Book("B004", "Dune", "Frank Herbert", "978-0441013593", "Sci-Fi", 688));
        catalog.addItem(new Book("B005", "Atomic Habits", "James Clear", "Programming"));

        // Magazines
        catalog.addItem(new Magazine("M001", "National Geographic", "Nat Geo Partners", 450, "April 2025", "Science"));
        catalog.addItem(new Magazine("M002", "The Economist", "Economist Group", 302, "March 2025", "Business"));
        catalog.addItem(new Magazine("M003", "Wired", "Condé Nast", 388, "February 2025", "Technology"));

        // DVDs
        catalog.addItem(new DVD("D001", "Inception", "Christopher Nolan", 148, "PG-13", "Sci-Fi"));
        catalog.addItem(new DVD("D002", "The Godfather", "Francis Ford Coppola", 175, "R", "Drama"));
        catalog.addItem(new DVD("D003", "Interstellar", "Christopher Nolan", 169, "PG-13", "Sci-Fi"));

        // Members
        memberService.registerMember("Alice Murphy", "alice@email.com", "083-111-2222");
        memberService.registerMember("Bob O'Brien", "bob@email.com", "087-333-4444");
        memberService.registerMember("Carol Byrne", "carol@email.com", "086-555-6666");

        System.out.println();
    }
}
