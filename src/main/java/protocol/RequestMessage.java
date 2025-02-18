package protocol;

public class RequestMessage {
  private int messageType; // 0 = Request, 1 = Reply
  private int requestId; // id da requisição
  private String objectReference; // ex: "LibraryService"
  private String methodId; // ex: "addBook"
  private String arguments; // JSON com os argumentos

  public RequestMessage() {
  }

  public RequestMessage(int messageType, int requestId, String objectReference, String methodId, String arguments) {
    this.messageType = messageType;
    this.requestId = requestId;
    this.objectReference = objectReference;
    this.methodId = methodId;
    this.arguments = arguments;
  }

  public int getMessageType() {
    return messageType;
  }

  public void setMessageType(int messageType) {
    this.messageType = messageType;
  }

  public int getRequestId() {
    return requestId;
  }

  public void setRequestId(int requestId) {
    this.requestId = requestId;
  }

  public String getObjectReference() {
    return objectReference;
  }

  public void setObjectReference(String objectReference) {
    this.objectReference = objectReference;
  }

  public String getMethodId() {
    return methodId;
  }

  public void setMethodId(String methodId) {
    this.methodId = methodId;
  }

  public String getArguments() {
    return arguments;
  }

  public void setArguments(String arguments) {
    this.arguments = arguments;
  }
}
