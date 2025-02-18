package communication;

import java.rmi.RemoteException;
import remote.RemoteInvocation;

public class RequestReplyProtocol {

  private RemoteInvocation remote;

  public RequestReplyProtocol(RemoteInvocation remote) {
    this.remote = remote;
  }

  /**
   * Método que empacota a chamada e invoca o método remoto doOperation.
   */
  public byte[] doOperation(String objectReference, String methodId, String argumentsJson) {
    try {
      byte[] argumentsBytes = argumentsJson.getBytes();
      byte[] replyBytes = remote.doOperation(objectReference, methodId, argumentsBytes);
      return replyBytes;
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return null;
  }
}
