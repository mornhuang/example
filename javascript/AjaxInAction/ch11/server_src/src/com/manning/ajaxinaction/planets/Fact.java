/*
 * Created on 16-Jun-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manning.ajaxinaction.planets;

import java.util.Date;

/**
 * @author dave
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Fact {
	private int id=-1;
	private Planet parent=null;
	private String text=null;
	private String author=null;
	private Date date=null;
	/**
	 * 
	 */
	public Fact() {
		super();
	}
	/**
	 * @return Returns the author.
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author The author to set.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return Returns the date.
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date The date to set.
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return Returns the id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return Returns the parent.
	 */
	public Planet getParent() {
		return parent;
	}
	/**
	 * @param parent The parent to set.
	 */
	public void setParent(Planet parent) {
		this.parent = parent;
	}
	/**
	 * @return Returns the text.
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text The text to set.
	 */
	public void setText(String text) {
		this.text = text;
	}
}
