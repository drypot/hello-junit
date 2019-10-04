package bookstoread;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BookShelf")
class BookShelfSpec {

  private BookShelf shelf;
  private Book effectiveJava;
  private Book codeComplete;
  private Book mythicalManMonth;
  private Book cleanCode;

  @BeforeEach
  void init() throws Exception {
    shelf = new BookShelf();
    effectiveJava = new Book(
      "Effective Java",
      "Joshua Bloch",
      LocalDate.of(2008, Month.MAY, 8)
    );
    codeComplete = new Book(
      "Code Complete",
      "Steve McConnel",
      LocalDate.of(2004, Month.JUNE, 9)
    );
    mythicalManMonth = new Book(
      "The Mythical Man-Month",
      "Frederick Phillips Brooks",
      LocalDate.of(1975, Month.JANUARY, 1)
    );
    cleanCode = new Book(
      "Clean Code",
      "Uncle Bob",
      LocalDate.of(2008, Month.JUNE, 1)
    );
  }

  @Nested
  @DisplayName("books()")
  class BooksSpec {

    @Test
    @DisplayName("when initailized, books() should return empty")
    void emptyBookShelfWhenNoBookAdded() throws Exception {
      List<Book> books = shelf.books();
      assertTrue(books.isEmpty());
    }

  }

  @Nested
  @DisplayName("add()")
  class AddSpec {

    @Test
    @DisplayName("when nothing added, it should be empty")
    void emptyBookShelfWhenAddIsCalledWithoutBooks() {
      shelf.add();
      List<Book> books = shelf.books();
      assertTrue(books.isEmpty());
    }

    @Test
    @DisplayName("when two books added, it should have books")
    void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
      shelf.add(effectiveJava);
      shelf.add(codeComplete);
      List<Book> books = shelf.books();
      assertEquals(2, books.size());
    }

    @Test
    @DisplayName("when books added, books() should return immutable")
    void booksReturnedFromBookShelfIsImmutableForClient() {
      shelf.add(effectiveJava, codeComplete);
      List<Book> books = shelf.books();
      try {
        books.add(mythicalManMonth);
        fail();
      } catch(Exception e) {
      }
    }

  }


  @Nested
  @DisplayName("arrange()")
  class ArrangeSpec {
    @Test
    @DisplayName("should return sorted by book title.")
    void bookshelfArrangedByBookTitle() {
      shelf.add(effectiveJava, codeComplete, mythicalManMonth);
      List<Book> books = shelf.arrange();
      assertEquals(
        asList(codeComplete, effectiveJava, mythicalManMonth),
        books
      );
    }

    @Test
    @DisplayName("after arrange(), books() shoud remain in insertion order")
    void booksInBookshelfAreInInsertionOrderAfterCallingArrange() {
      shelf.add(effectiveJava, codeComplete, mythicalManMonth);
      shelf.arrange();
      List<Book> books = shelf.books();
      assertEquals(
        asList(effectiveJava, codeComplete, mythicalManMonth),
        books
      );
    }

    @Test
    @DisplayName("can be called with user criteria")
    void bookshelfArrangedByUserProvidedCriteria() {
      shelf.add(effectiveJava, codeComplete, mythicalManMonth);
      Comparator<Book> reversed = Comparator.<Book>naturalOrder().reversed();
      List<Book> books = shelf.arrange(reversed);
      assertThat(books).isSortedAccordingTo(reversed);
    }

  }

  @Nested
  @DisplayName("groupByPublicationYear()")
  class GroupByPubYearSpec{

    @Test
    @DisplayName("should return grouped by publication year")
    void groupBooksInsideBookShelfByPubliationYear() {
      shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
      Map<Year, List<Book>> booksByPublicationYear = shelf.groupByPublicationYear();

      assertThat(booksByPublicationYear)
        .containsKey(Year.of(2008))
        .containsValue(asList(effectiveJava, cleanCode));

      assertThat(booksByPublicationYear)
        .containsKey(Year.of(2004))
        .containsValue(singletonList(codeComplete));

      assertThat(booksByPublicationYear)
        .containsKey(Year.of(1975))
        .containsValue(singletonList(mythicalManMonth));
    }
  }
}
