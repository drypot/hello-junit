package bookstoread;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@ToString
public class Book implements Comparable<Book> {
  @Getter
  private final String title;

  @Getter
  private final String author;

  @Getter
  private final LocalDate publishedOn;

  @Override
  public int compareTo(Book that) {
    return this.title.compareTo(that.title);
  }
}
