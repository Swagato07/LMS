package service;

import model.LibraryItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the library's collection of items.
 * Demonstrates: Generics, Lambdas, Streams, Interface Implementation
 */
public class LibraryCatalog implements Searchable<LibraryItem> {

    private final List<LibraryItem> items;

    public LibraryCatalog() {
        this.items = new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
        System.out.println("✔ Added: " + item.getSummary());
    }

    public boolean removeItem(String id) {
        return items.removeIf(item -> item.getId().equalsIgnoreCase(id));
    }

    // --- Searchable Interface Implementation ---

    @Override
    public List<LibraryItem> searchByTitle(String keyword) {
        // Lambda + Stream
        return items.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<LibraryItem> searchByGenre(String genre) {
        return items.stream()
                .filter(item -> item.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    @Override
    public LibraryItem findById(String id) {
        return items.stream()
                .filter(item -> item.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    // --- Utility Methods using Lambdas ---

    public List<LibraryItem> getAvailableItems() {
        return items.stream()
                .filter(LibraryItem::isAvailable) // Method reference
                .collect(Collectors.toList());
    }

    public List<LibraryItem> getBorrowedItems() {
        return items.stream()
                .filter(item -> !item.isAvailable())
                .collect(Collectors.toList());
    }

    public List<LibraryItem> getAllItemsSortedByTitle() {
        return items.stream()
                .sorted(Comparator.comparing(LibraryItem::getTitle))
                .collect(Collectors.toList());
    }

    public List<LibraryItem> filterByType(String type) {
        return items.stream()
                .filter(item -> item.getItemType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public void printAllItems() {
        if (items.isEmpty()) {
            System.out.println("  No items in catalog.");
            return;
        }
        items.forEach(item -> System.out.println("  " + item.getSummary(true)));
    }

    public int getTotalCount()     { return items.size(); }
    public List<LibraryItem> getAllItems() { return items; }
}
