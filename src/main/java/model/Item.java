package model;

import java.io.Serializable;

public class Item implements Serializable {
  private static final long serialVersionUID = 1L;

  private String id;
  private String title;

  public Item() {
  }

  public Item(String id, String title) {
    this.id = id;
    this.title = title;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}