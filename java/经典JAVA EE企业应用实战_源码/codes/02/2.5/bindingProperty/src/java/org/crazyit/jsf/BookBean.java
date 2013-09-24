/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.crazyit.jsf;


/**
 *
 * @author yeeku
 */
public class BookBean {
	private String name;
	private String isbn;
	private String desc;

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getName() {
		return name;
	}

	public String addBook()	{
		System.out.println("=========!!!=======");
		return "success";
	}

}
