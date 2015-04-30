/*
 * Created on 16-Jun-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manning.ajaxinaction.planets;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dave
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SolarSystem {
	
	private static SolarSystem instance=null;
	
	/**
	 * @return Returns the instance.
	 */
	public static SolarSystem getInstance() {
		if (instance==null){
			instance=new SolarSystem();
		}
		return instance;
	}
	
	/**
	 * 
	 */
	private SolarSystem() {
		super();
		planets=new ArrayList();
	}
	
	/**
	 * @return Returns the planets.
	 */
	public List getPlanets() {
		return planets;
	}
	/**
	 * @param planets The planets to set.
	 */
	public void setPlanets(List planets) {
		this.planets = planets;
	}
	private List planets=null;
	/**
	 * @param o
	 * @return
	 */
	public boolean addPlanet(Planet p) {
		return planets.add(p);
	}
	/**
	 * 
	 */
	public void clear() {
		planets.clear();
	}
}
