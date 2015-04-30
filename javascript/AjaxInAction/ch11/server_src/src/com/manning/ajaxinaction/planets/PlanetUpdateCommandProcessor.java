package com.manning.ajaxinaction.planets;

import java.beans.Statement;

import org.jdom.Element;

import com.manning.ajaxinaction.command.XMLCommandProcessor;

/**
 * @author crane
 */
public class PlanetUpdateCommandProcessor implements XMLCommandProcessor {

  public PlanetUpdateCommandProcessor() {
    super();
  }

  public Element processXML(Element el) {
    Element result=new Element("command");
    String id=el.getAttributeValue("id");
    result.setAttribute("id",id);
    String status=null;
    String reason=null;
    String planetId=el.getAttributeValue("planetId");
    String field=el.getAttributeValue("field");
    String value=el.getAttributeValue("value");
    Planet planet=findPlanet(planetId);
    if (planet==null){
      status="failed";
      reason="no planet found for id "+planetId;
    }else{
      Double numValue=new Double(value);
      Object[] args=new Object[]{ numValue };
      String method="set"+field.substring(0,1).toUpperCase()+field.substring(1);
      Statement statement=new Statement(planet,method,args);
      try {
      statement.execute();
      status="ok";
      } catch (Exception e) {
      status="failed";
        reason="unable to set value "+value+" for field "+field;
      }
    }
    result.setAttribute("status",status);
    if (reason!=null){
      result.setAttribute("reason",reason);
    }
    return result;
  }

  /**
   * @param planetId
   * @return
   */
  private Planet findPlanet(String planetId) {
    // TODO use hibernate
    return null;
  }

}
