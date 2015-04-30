
package com.manning.ajaxinaction.portal;

public class User {
  private int id=-1;
  private String userName=null;
  
  public User(int id, String userName) {
	super();
	this.id = id;
	this.userName = userName;
  }
  public int getId() { return id;}
  public String getUserName() { return userName;}
}
