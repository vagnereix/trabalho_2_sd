package model;

public class Magazine extends Item {
  private static final long serialVersionUID = 1L;

  private int issueNumber;

  public Magazine() {
    super();
  }

  public Magazine(String id, String title, int issueNumber) {
    super(id, title);
    this.issueNumber = issueNumber;
  }

  public int getIssueNumber() {
    return issueNumber;
  }

  public void setIssueNumber(int issueNumber) {
    this.issueNumber = issueNumber;
  }
}