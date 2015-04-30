/*
 * Created on 16-Jun-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manning.ajaxinaction.web;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.manning.ajaxinaction.command.XMLCommandProcessor;

/**
 * @author dave
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommandServlet extends HttpServlet {

  private Map commandTypes=null;

  /* (non-Javadoc)
   * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
   */
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    commandTypes=new HashMap();
    boolean more=true;
    for(int counter=1;more;counter++){
      String typeName=config.getInitParameter("type"+counter);
      String typeImpl=config.getInitParameter("impl"+counter);
      if (typeName==null || typeImpl==null){
        more=false;
      }else{
        try{
          Class cls=Class.forName(typeImpl);
          commandTypes.put(typeName,cls);
        }catch (ClassNotFoundException clanfex){
          this.log("couldn't resolve handler class name "+typeImpl);
        }
      }
    }
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    resp.setContentType("text/xml");
    Reader reader=req.getReader();
    Writer writer=resp.getWriter();
    try{
      SAXBuilder builder=new SAXBuilder(false);
    Document doc=builder.build(reader);
    Element root=doc.getRootElement();
    if ("commands".equals(root.getName())){
      for(Iterator iter=root.getChildren("command").iterator();iter.hasNext();){
        Element el=(Element)(iter.next());
        String type=el.getAttributeValue("type");
        XMLCommandProcessor command=getCommand(type,writer);
        if (command!=null){
          Element result=command.processXML(el);
          writer.write(result.toString());
        }
      }
    }else{
      sendError(writer,"incorrect document format - expected top-level command tag");
    }
    }catch (JDOMException jdomex){
      sendError(writer,"unable to parse request document");
    }
  }

private XMLCommandProcessor getCommand(String type,Writer writer) throws IOException{
    XMLCommandProcessor cmd=null;
  Class cls=(Class)(commandTypes.get(type));
  if (cls!=null){
    try{
    cmd=(XMLCommandProcessor)(cls.newInstance());
    }catch (ClassCastException castex){
         sendError(writer,"class "+cls.getName()+" is not a command");
    } catch (InstantiationException instex) {
         sendError(writer,"not able to create class "+cls.getName());
       } catch (IllegalAccessException illex) {
         sendError(writer,"not allowed to create class "+cls.getName());
    }
  }else{
    sendError(writer,"no command type registered for "+type);
  }
  return cmd;
}

private void sendError(Writer writer,String message) throws IOException{
    writer.write("<error msg='"+message+"'/>");
    writer.flush();
  }
}
