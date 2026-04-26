package service;

import java.util.List;

/**
 * Interface for searchable collections.
 * Demonstrates: Interface, Abstraction
 */
public interface Searchable<T> {
    List<T> searchByTitle(String keyword);
    List<T> searchByGenre(String genre);
    T findById(String id);
}
