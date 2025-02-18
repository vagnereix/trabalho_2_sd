package client;

import communication.RequestReplyProtocol;
import java.rmi.Naming;
import java.util.List;
import model.Author;
import model.Book;
import model.Magazine;
import remote.RemoteInvocation;
import remote.LibraryRemote;

public class ClientMain {
  public static void main(String[] args) {
    try {
      // Faz lookup do objeto remoto usando o IP e a porta do servidor
      // Se o servidor estiver em outra máquina, substitua "localhost" pelo IP real.
      RemoteInvocation remote = (RemoteInvocation) Naming.lookup("rmi://localhost:1099/RemoteInvocationService");

      // Cria o protocolo que utilizará o objeto remoto
      RequestReplyProtocol protocol = new RequestReplyProtocol(remote);
      // Cria o stub para a biblioteca (usando "LibraryService" como referência)
      LibraryRemote library = new LibraryStub(protocol, "LibraryService");

      // Chamada remota: adiciona um livro
      Book book = new Book("B001", "Distributed Systems", new Author("Tanenbaum", "tanenbaum@example.com"));
      library.addBook(book);

      // Chamada remota: adiciona uma revista
      Magazine magazine = new Magazine("M001", "Tech Today", 42);
      library.addMagazine(magazine);

      // Chamada remota: lista os livros
      List<Book> books = library.listBooks();
      System.out.println("Livros cadastrados:");
      for (Book b : books) {
        System.out.println(" - " + b.getId() + ": " + b.getTitle() +
            " (autor: " + b.getAuthor().getName() + ")");
      }

      // Chamada remota: lista as revistas
      List<Magazine> magazines = library.listMagazines();
      System.out.println("Revistas cadastradas:");
      for (Magazine m : magazines) {
        System.out.println(" - " + m.getId() + ": " + m.getTitle() +
            " (edição: " + m.getIssueNumber() + ")");
      }
    } catch (Exception e) {
      System.err.println("Exceção no cliente: " + e.toString());
      e.printStackTrace();
    }
  }
}
