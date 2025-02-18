package protocol;

public class ReplyMessage {
  private int messageType; // 1 = Reply
  private int requestId;
  private String returnValue; // JSON com o valor de retorno (pode ser vazio para métodos void)

  public ReplyMessage() {
  }

  public ReplyMessage(int messageType, int requestId, String returnValue) {
    this.messageType = messageType;
    this.requestId = requestId;
    this.returnValue = returnValue;
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

  public String getReturnValue() {
    return returnValue;
  }

  public void setReturnValue(String returnValue) {
    this.returnValue = returnValue;
  }
}
