package client;

import communication.RequestReplyProtocol;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.rmi.RemoteException;
import java.util.List;
import model.Book;
import model.Magazine;
import protocol.JsonUtils;
import protocol.ReplyMessage;
import remote.LibraryRemote;

public class LibraryStub implements LibraryRemote {

  private RequestReplyProtocol protocol;
  private String objectReference;

  public LibraryStub(RequestReplyProtocol protocol, String objectReference) {
    this.protocol = protocol;
    this.objectReference = objectReference; // Ex: "LibraryService"
  }

  @Override
  public void addBook(Book book) throws RemoteException {
    String argsJson = JsonUtils.toJson(book);
    protocol.doOperation(objectReference, "addBook", argsJson);
  }

  @Override
  public void addMagazine(model.Magazine magazine) throws RemoteException {
    String argsJson = JsonUtils.toJson(magazine);
    protocol.doOperation(objectReference, "addMagazine", argsJson);
  }

  @Override
  public List<Book> listBooks() throws RemoteException {
    byte[] replyBytes = protocol.doOperation(objectReference, "listBooks", "");
    String replyJson = new String(replyBytes);
    ReplyMessage reply = JsonUtils.fromJson(replyJson, ReplyMessage.class);
    String returnJson = reply.getReturnValue();
    Type listType = new TypeToken<List<Book>>() {
    }.getType();
    List<Book> books = JsonUtils.fromJson(returnJson, listType);
    return books;
  }

  @Override
  public List<Magazine> listMagazines() throws RemoteException {
    byte[] replyBytes = protocol.doOperation(objectReference, "listMagazines", "");
    String replyJson = new String(replyBytes);
    ReplyMessage reply = JsonUtils.fromJson(replyJson, ReplyMessage.class);
    String returnJson = reply.getReturnValue();
    Type listType = new TypeToken<List<Magazine>>() {
    }.getType();
    List<Magazine> magazines = JsonUtils.fromJson(returnJson, listType);
    return magazines;
  }
}
