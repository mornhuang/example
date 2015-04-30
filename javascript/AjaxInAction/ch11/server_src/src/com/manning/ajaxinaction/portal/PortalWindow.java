package com.manning.ajaxinaction.portal;

public class PortalWindow {
  private int id=-1;
  private User user=null;
  private int xPos=0;
  private int yPos=0;
  private int width=0;
  private int height=0;
  private String url=null;
  private String title=null;
  
  public PortalWindow(
	int id, User user, int xPos, int yPos, 
	int width,int height, 
	String url, String title
  ) {
	super();
	this.id = id;
	this.user = user;
	this.xPos = xPos;
	this.yPos = yPos;
	this.width = width;
	this.height = height;
	this.url = url;
	this.title = title;
  }
  public int getHeight() {return height;}
  public void setHeight(int height) {this.height = height;}
  public int getId() {return id;}
  public void setId(int id) {this.id = id;}
  public String getTitle() {return title;}
  public void setTitle(String title) {this.title = title;}
  public String getUrl() {return url;}
  public void setUrl(String url) {this.url = url;}
  public User getUser() {return user;}
  public void setUser(User user) {this.user = user;}
  public int getWidth() {return width;}
  public void setWidth(int width) {this.width = width;}
  public int getXPos() {return xPos;}
  public void setXPos(int pos) {xPos = pos;}
  public int getYPos() {return yPos;}
  public void setYPos(int pos) {yPos = pos;}
}
