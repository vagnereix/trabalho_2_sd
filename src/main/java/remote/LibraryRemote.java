package remote;

import java.rmi.RemoteException;
import java.util.List;
import model.Book;
import model.Magazine;

public interface LibraryRemote {
  void addBook(model.Book book) throws RemoteException;

  void addMagazine(model.Magazine magazine) throws RemoteException;

  List<Book> listBooks() throws RemoteException;

  List<Magazine> listMagazines() throws RemoteException;
}
