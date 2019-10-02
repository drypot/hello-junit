package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookShelfSpec {

  private BookShelf shelf;

  @BeforeEach
  void init() throws Exception {
    shelf = new BookShelf();
  }

  @Test
  void shelfEmptyWhenNoBookAdded() throws Exception {
    List<String> books = shelf.books();
    assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
  }

  @Test
  void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
    shelf.add("Effective Java");
    shelf.add("Code Complete");
    List<String> books = shelf.books();
    assertEquals(2, books.size(), () -> "BookShelf should have two books.");
  }

  @Test
  void emptyBookShelfWhenAddIsCalledWithoutBooks() {
    shelf.add();
    List<String> books = shelf.books();
    assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
  }

  @Test
  void booksReturnedFromBookShelfIsImmutableForClient() {
    shelf.add("Effective Java", "Code Complete");
    List<String> books = shelf.books();
    try {
      books.add("The Mythical Man-Month");
      fail(() -> "Should not be able to add book to books.");
    } catch(Exception e) {
      assertTrue(e instanceof UnsupportedOperationException,
        () -> "Should throw UnsupportedOperationException"
      );
    }
  }

  
}
