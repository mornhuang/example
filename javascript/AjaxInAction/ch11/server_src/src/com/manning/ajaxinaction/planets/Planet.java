/*
 * Created on 16-Jun-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manning.ajaxinaction.planets;

import java.util.List;

/**
 * @author dave
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Planet {
	private int id=-1;
	private SolarSystem parent=null;
	private String name=null;
	private double distance=0;
	private double diameter=0;
	private String imageUrl=null;
	private List facts=null;
	/**
	 * 
	 */
	public Planet() {
		super();
	}
	/**
	 * @return Returns the diameter.
	 */
	public double getDiameter() {
		return diameter;
	}
	/**
	 * @param diameter The diameter to set.
	 */
	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	/**
	 * @return Returns the distance.
	 */
	public double getDistance() {
		return distance;
	}
	/**
	 * @param distance The distance to set.
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
	/**
	 * @return Returns the facts.
	 */
	public List getFacts() {
		return facts;
	}
	/**
	 * @param facts The facts to set.
	 */
	public void setFacts(List facts) {
		this.facts = facts;
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
	 * @return Returns the imageUrl.
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * @param imageUrl The imageUrl to set.
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the parent.
	 */
	public SolarSystem getParent() {
		return parent;
	}
	/**
	 * @param parent The parent to set.
	 */
	public void setParent(SolarSystem parent) {
		this.parent = parent;
	}
}
