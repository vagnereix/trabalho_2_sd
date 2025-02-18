package model;

public class Book extends Item {
  private static final long serialVersionUID = 1L;

  private Author author;

  public Book() {
    super();
  }

  public Book(String id, String title, Author author) {
    super(id, title);
    this.author = author;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }
}
