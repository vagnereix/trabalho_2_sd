package server;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.Book;
import model.Magazine;
import protocol.JsonUtils;
import protocol.ReplyMessage;
import protocol.RequestMessage;
import remote.RemoteInvocation;

public class RemoteInvocationImpl extends UnicastRemoteObject implements RemoteInvocation {

  private static final long serialVersionUID = 1L;
  private static int NEXT_REQUEST_ID = 1;

  // Objeto de negócio que executa as operações reais
  private LibraryService libraryService;

  public RemoteInvocationImpl() throws RemoteException {
    super();
    libraryService = new LibraryService();
  }

  /**
   * Método remoto que implementa:
   * doOperation(RemoteObjectRef o, int methodId, byte[] arguments)
   * Aqui, objectReference e methodId são Strings.
   */
  @Override
  public byte[] doOperation(String objectReference, String methodId, byte[] arguments) throws RemoteException {
    int requestId = NEXT_REQUEST_ID++;

    // Monta o RequestMessage usando os parâmetros recebidos
    RequestMessage request = new RequestMessage(0, requestId, objectReference, methodId, new String(arguments));

    Object returnValue = getRequest(request, methodId);

    // Monta o ReplyMessage com o valor de retorno (se houver)
    ReplyMessage reply = new ReplyMessage(1, requestId, "");
    if (returnValue != null) {
      String returnJson = JsonUtils.toJson(returnValue);
      reply.setReturnValue(returnJson);
    }

    // Usa o método sendReply para empacotar a resposta (clientHost e clientPort são
    // ignorados pois RMI cuida do transporte)
    byte[] replyBytes = sendReply(reply, null, 0);
    return replyBytes;
  }

  private Object getRequest(RequestMessage request, String methodId) {
    Object returnValue = null;

    try {
      // Despacho dos métodos com base no methodId
      switch (methodId) {
        case "addBook": {
          Book book = JsonUtils.fromJson(request.getArguments(), Book.class);
          libraryService.addBook(book);
          break;
        }
        case "addMagazine": {
          Magazine magazine = JsonUtils.fromJson(request.getArguments(), Magazine.class);
          libraryService.addMagazine(magazine);
          break;
        }
        case "listBooks": {
          returnValue = libraryService.listBooks();
          break;
        }
        case "listMagazines": {
          returnValue = libraryService.listMagazines();
          break;
        }
        default:
          System.out.println("[RemoteInvocationImpl] Método desconhecido: " + methodId);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return returnValue;
  }

  // Simula o envio de uma resposta para o cliente,
  // endereçando-a a um IP e porta (aqui, apenas empacota em JSON)
  private byte[] sendReply(ReplyMessage reply, InetAddress clientHost, int clientPort) {
    String json = JsonUtils.toJson(reply);
    return json.getBytes();
  }
}
