package server;

import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.Magazine;
import model.Library;

public class LibraryService {
  private Library library;

  public LibraryService() {
    this.library = new Library(new ArrayList<>(), new ArrayList<>());
  }

  public void addBook(Book book) {
    library.getBooks().add(book);
    System.out.println("[LibraryService] Book adicionado: " + book.getTitle());
  }

  public void addMagazine(Magazine magazine) {
    library.getMagazines().add(magazine);
    System.out.println("[LibraryService] Magazine adicionada: " + magazine.getTitle());
  }

  public List<Book> listBooks() {
    return library.getBooks();
  }

  public List<Magazine> listMagazines() {
    return library.getMagazines();
  }
}
