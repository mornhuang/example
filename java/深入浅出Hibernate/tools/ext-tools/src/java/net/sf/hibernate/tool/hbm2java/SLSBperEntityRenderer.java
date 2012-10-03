package net.sf.hibernate.tool.hbm2java;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  Renderer that generates a StateLess-SessionBean per entity.
 *  Consider this more an example of how to write a renderer, than how use Hibernate with sessionbeans! 
 * 
 *  Uses meta attribute jndi-name and initial-context for generating the xdoclet remarks and initial context lookup.
 *  Inspired by postings by carniz at Hibernate forum:
 * 
 *    The basic idea is:
 *    - one SLSB per persistent class
 *    - each SLSB is a "service" for adding, removing, updating and finding objects of the actual class
 *  
 *  
 *  @author MAX
 *
 */
public class SLSBperEntityRenderer extends AbstractRenderer {

    /* (non-Javadoc)
     * @see net.sf.hibernate.tool.hbm2java.Renderer#render(java.lang.String, java.lang.String, net.sf.hibernate.tool.hbm2java.ClassMapping, java.util.Map, java.io.PrintWriter)
     */
    public void render(
        String savedToPackage,
        String savedToClass,
        ClassMapping classMapping,
        Map class2classmap,
        PrintWriter mainwriter)
        throws Exception {
        
        genPackageDelaration(savedToPackage, classMapping, mainwriter);
        mainwriter.println();
        
        // switch to another writer to be able to insert the actually
        // used imports when whole class has been rendered. 
        StringWriter strWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(strWriter);

        String jndiName = classMapping.getMetaAsString("jndi-name");
        String initialContext  = classMapping.getMetaAsString("initial-context");
        String classname = classMapping.getName();
        String serviceName = classname + "Service";
        
        writer.println("/**");
        writer.println("* @ejb:bean name=\"" + serviceName + "\"");
        writer.println("*  jndi-name=\"" + jndiName + "\"");
        writer.println("*  type=\"Stateless\""); 
        writer.println("**/");
        
        writer.println("public abstract class " + savedToClass + " implements SessionBean {");
        writer.println();
        writer.println("    SessionContext sessionContext;");          
        writer.println("    SessionFactory sessionFactory;");
        writer.println("    Session session;");
        
        
        writer.println("    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {");
        writer.println("        this.sessionContext = sessionContext;");
        writer.println("        try {");
        writer.println("            sessionFactory = (SessionFactory) new InitialContext().lookup(\"" + initialContext + "\");");
        writer.println("            }");
        writer.println("        catch (NamingException ne) {");
        writer.println("            throw new EJBException(ne.getExplanation(), ne);");
        writer.println("        }");       
        writer.println("    }");
        
        String localName = classname.toLowerCase();
        
        writer.println("    /**");
        writer.println("     * @ejb:interface-method");
        writer.println("     *  tview-type=\"remote\""); 
        writer.println("     **/");
        writer.println("     public void add" + classname + "(" + classname + " " + localName + ") throws HibernateException {");
        writer.println("       try {");
        writer.println("          session = sessionFactory.openSession();");
        writer.println("          session.save(" + localName + ");");
        writer.println("            session.flush();");
        writer.println("        }");
        writer.println("        catch (HibernateException he) {");
        writer.println("            sessionContext.setRollbackOnly();");   
        writer.println("            throw he;");           
        writer.println("        }");
        writer.println("        finally {");
        writer.println("            if (session != null)");
        writer.println("                try {");
        writer.println("                    session.close();");        
        writer.println("                }");
        writer.println("                catch (Exception e) {   // ignore");                   
        writer.println("                }");
        writer.println("        }");
        writer.println("    }");
        
        writer.println("    /**");
        writer.println("     * @ejb:interface-method");
        writer.println("     *  tview-type=\"remote\""); 
        writer.println("     **/");
        writer.println("     public void delete" + classname + "(" + classname + " " + localName + ") throws HibernateException {");
        writer.println("       try {");
        writer.println("          session = sessionFactory.openSession();");
        writer.println("          session.delete(" + localName + ");");
        writer.println("          session.flush();");
        writer.println("        }");
        writer.println("        catch (HibernateException he) {");
        writer.println("            sessionContext.setRollbackOnly();");   
        writer.println("            throw he;");           
        writer.println("        }");
        writer.println("        finally {");
        writer.println("            if (session != null)");
        writer.println("                try {");
        writer.println("                    session.close();");        
        writer.println("                }");
        writer.println("                catch (Exception e) {   // ignore");                   
        writer.println("                }");
        writer.println("        }");   
        writer.println("    }");     
        
        localName = "idValue";
        String identifierType = null;
        
        List list = classMapping.getAllFields();
        for (Iterator fields = list.iterator(); fields.hasNext();) {
            FieldProperty element = (FieldProperty) fields.next();
            if(element.isIdentifier()) {
                if(identifierType==null) {
                    identifierType = element.getFullyQualifiedTypeName();
                } else {
                    throw new IllegalStateException("Entities with multiple properties for fields are not supported in SLSBperEntity renderer.");
                }
            }
        }
        writer.println("    /**");
        writer.println("     * @ejb:interface-method");
        writer.println("     *  tview-type=\"remote\""); 
        writer.println("     **/");
        writer.println("     public " + classname + " findById" + classname + "(" + identifierType + " " + localName + ") throws HibernateException {");
        writer.println("       " + classname + " result = null;");
        writer.println("       try {");
        writer.println("          session = sessionFactory.openSession();");
        writer.println("          session.load(" + classname + ".class, " + localName + ");");
        writer.println("        }");
        writer.println("        catch (HibernateException he) {");
        writer.println("            sessionContext.setRollbackOnly();");   
        writer.println("            throw he;");           
        writer.println("        }");
        writer.println("        finally {");
        writer.println("            if (session != null)");
        writer.println("                try {");
        writer.println("                    session.close();");        
        writer.println("                }");
        writer.println("                catch (Exception e) {   // ignore");                   
        writer.println("               }");
        writer.println("        }");
        writer.println("        return result;");        writer.println("       }");                
        
        writer.println("    /**");
        writer.println("     * @ejb:interface-method");
        writer.println("     *  tview-type=\"remote\""); 
        writer.println("     **/");
        writer.println("     public List findAll() throws HibernateException {");
        writer.println("       List result = null;");
        writer.println("       try {");
        writer.println("          session = sessionFactory.openSession();");
        writer.println("          session.find(\"from " + classname.toLowerCase() + " in class " + classname + ".class.getName() + \");");
        writer.println("          return result;");
        writer.println("        }");
        writer.println("        catch (HibernateException he) {");
        writer.println("            sessionContext.setRollbackOnly();");   
        writer.println("            throw he;");           
        writer.println("        }");
        writer.println("        finally {");
        writer.println("            if (session != null)");
        writer.println("                try {");
        writer.println("                    session.close();");        
        writer.println("                }");
        writer.println("                catch (Exception e) {   // ignore");                   
        writer.println("               }");
        writer.println("        }");               
        writer.println("      }");  
        writer.println("}");
        
        // finally write the imports
        doImports(classMapping, mainwriter);
        mainwriter.print(strWriter.toString());
    }

    /**
     * @param classMapping
     * @param mainwriter
     */
    private void doImports(ClassMapping classMapping, PrintWriter writer) {
        writer.println("import " + classMapping.getFullyQualifiedName() + ";");
        writer.println("import java.rmi.RemoteException;");
        writer.println("import java.util.List;");
        writer.println("import javax.ejb.SessionBean;");
        writer.println("import javax.ejb.SessionContext;");
        writer.println("import javax.ejb.EJBException;");
        
        writer.println("import net.sf.hibernate.SessionFactory;");
        writer.println("import net.sf.hibernate.HibernateException;");        
        writer.println("import net.sf.hibernate.Session;");
        
        writer.println("import javax.naming.InitialContext;");
        writer.println("import javax.naming.NamingException;");

    }

}
