package model;

import java.io.Serializable;
import java.util.List;

public class Library implements Serializable {
  private static final long serialVersionUID = 1L;

  private List<Book> books;
  private List<Magazine> magazines;

  public Library() {
  }

  public Library(List<Book> books, List<Magazine> magazines) {
    this.books = books;
    this.magazines = magazines;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

  public List<Magazine> getMagazines() {
    return magazines;
  }

  public void setMagazines(List<Magazine> magazines) {
    this.magazines = magazines;
  }
}
