package pl.test;

import java.sql.*;
import javax.xml.parsers.*;

import pl.*;
import pl.criteria.*;
import pl.xml.*;

/**
 * Insert the type's description here.
 *
 * @author: Artem Rudoy
 */
public class Test {
    /**
     * Constructor.
     */
    public Test()
    {
        super();
    }

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        Test test = new Test();
        test.performTest();
    }

    /**
     * Test method.
     */
    public void performTest()
    {
        try
        {
            PersistenceManagerFactory pmf = new PersistenceManagerFactory();

            // String dir = "D:\\Users\\Artyom\\Projects\\PersistenceLayer\\Test\\";
            String dir = "D:\\temp\\newpl\\pl\\test\\";
            // Load database
            //PersistenceBroker.getInstance().loadConfig(new XMLConfigLoader(dir + "oracleTest.xml"));
            //PersistenceBroker.getInstance().loadConfig(new XMLConfigLoader(dir + "cloudscapeTest.xml"));
            //PersistenceBroker.getInstance().loadConfig(new XMLConfigLoader(dir + "postgresqlTest.xml"));
            pmf.loadConfig(new XMLConfigLoader(dir + "mySqlTest.xml"));
            // Load database schema
            pmf.loadConfig(new XMLConfigLoader(dir + "schema.xml"));

            PersistenceManager pm = pmf.getPersistenceManager();

            // Single simple object test
            System.out.println("---Single simple object test and transaction test");
            pm.getTransaction().begin();
            Person person = new Person(pm);
            person.setName("Test person 1");
            // Insert object
            person.save();
            // Update object
            person.setName("Test person 2");
            person.save();
            // Retrieve object
            person.retrieve();
            // Lock object
            person.lock();
            // Delete object
            person.delete();
            pm.getTransaction().commit();
            System.out.println();

            // Single object test with inheritance
            System.out.println("---Single object test with inheritance");
            Manager manager = new Manager(pm);
            manager.setName("Test manager 1");
            manager.setDepartment("Test department 1");
            // Insert object
            manager.save();
            // Update object
            manager.setName("Test manager 2");
            manager.setDepartment("Test department 2");
            manager.save();
            // Retrieve object
            manager.retrieve();
            // Delete object
            manager.delete();
            System.out.println();

            // Test for association support
            System.out.println("---Test for association support");
            Employee employee = new Employee(pm);
            employee.setName("Test epmloyee");
            Position position = new Position(pm);
            position.setName("Test position");
            employee.setPosition(position);
            Task task = new Task(pm);
            task.setName("Test task 1");
            employee.getTasks().add(task);
            task = new Task(pm);
            task.setName("Test task 2");
            employee.getTasks().add(task);
            task = new Task(pm);
            task.setName("Test task 3");
            employee.getTasks().add(task);
            // Insert objects
            employee.save();
            // Update objects
            employee.save();
            // Retrieve obects
            employee.retrieve();
            // Retrieve tasks only
            employee.retrieveAssociation("tasks", null);
            // Delete objects
            employee.delete();
            System.out.println();

            // Empty criteria test
            System.out.println("---Empty criteria test");
            RetrieveCriteria criteria = pm.getRetrieveCriteria(Employee.class);
            Cursor result = criteria.perform(new java.util.Vector());
            while(result.next())
            {
                System.out.println("Name: " + ((Employee)result.getObject()).getName() + "; position: " +((Employee)result.getObject()).getPosition().getName());
            }
            result.close();
            System.out.println();

            // Simple criteria test
            System.out.println("---Simple criteria test");
            criteria = pm.getRetrieveCriteria(Employee.class);
            criteria.getWhereCondition().addOrCriteria(criteria.getEqualToCriteria("name"));
            criteria.getWhereCondition().addOrCriteria(criteria.getEqualToCriteria("name"));
            java.util.Vector parameters = new java.util.Vector();
            parameters.add("Mary");
            parameters.add("John");
            result = criteria.perform(parameters);
            while(result.next())
            {
                System.out.println(((Employee)result.getObject()).getName());
            }
            result.close();
            System.out.println();

            // Simple criteria test 2
            System.out.println("---Simple criteria test 2");
            //criteria = new RetrieveCriteria(Employee.class);
            criteria.getWhereCondition().addAndCriteria(criteria.getGreaterThanCriteria("id"));
            parameters.add(new Long(2));
            result = criteria.perform(parameters);
            while(result.next())
            {
                System.out.println(((Employee)result.getObject()).getName());
            }
            result.close();
            System.out.println();

            // Complex criteria test
            System.out.println("---Complex criteria test");
            criteria = pm.getRetrieveCriteria(Employee.class);
            criteria.getWhereCondition().addAndCriteria(criteria.getEqualToCriteria("position.name"));
            parameters = new java.util.Vector();
            parameters.add("Software developer");
            CriteriaCondition orCondition = criteria.getNewCondition();
            orCondition.addOrCriteria(criteria.getEqualToCriteria("name"));
            orCondition.addOrCriteria(criteria.getEqualToCriteria("name"));
            criteria.getWhereCondition().addAndCriteria(orCondition);
            parameters.add("John");
            parameters.add("Mary");
            result = criteria.perform(parameters);
            while(result.next())
            {
                System.out.println(((Employee)result.getObject()).getName());
            }
            result.close();
            System.out.println();

            // Optimistic lock test
            System.out.println("---Optimistic lock test");
            manager = new Manager(pm);
            manager.setName("Test manager 1");
            manager.setDepartment("Test department 1");
            // Insert object
            manager.save();
            System.out.println("Before lock: " + manager.getTimestamp());
            manager.lockOptimistic();
            System.out.println("After lock: " + manager.getTimestamp());
            manager.retrieve();
            System.out.println("After retrieve: " + manager.getTimestamp());
            manager.setName("Test manager 2");
            manager.save();
            System.out.println("After update: " + manager.getTimestamp());
            manager.delete();
            System.out.println();

            // Proxy object test
            System.out.println("---Proxy object test");
            position = new Position(pm);
            position.setId(new Long(21));
            position.retrieveAsProxy();
            System.out.println(position.isProxy());
            System.out.println(position.getName());
            position.retrieve();
            System.out.println(position.isProxy());
            System.out.println(position.getName());
            System.out.println();

            // Proxy object creteria test
            System.out.println("---Proxy object criteria test");
            criteria = pm.getRetrieveCriteria(Position.class);
            result = criteria.performForProxies(new java.util.Vector());
            while(result.hasMoreElements())
            {
                Position pos = (Position)result.getObject();
                System.out.println(pos.isProxy()  + "; " + pos.getName());
            }

            // XML marshalling test
            System.out.println("---XML marshalling test");
            Marshaller marshaller = pmf.getXMLMarshaller();
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            org.w3c.dom.Document doc = db.newDocument();
            employee = new Employee(pm);
            employee.setId(new Long(3));
            employee.retrieve();
            marshaller.marshall(employee, doc, doc);
            java.io.PrintWriter pw = new java.io.PrintWriter(System.out);
            ((org.apache.crimson.tree.XmlWritable)doc).writeXml(new org.apache.crimson.tree.XmlWriteContext(pw));
            pw.flush();
            System.out.println();
            System.out.println("---Test for namespace support");
            marshaller = pmf.getXMLMarshaller("aaa/bbb/ccc", "aaa");
            doc = db.newDocument();
            marshaller.marshall(employee, doc, doc);
            ((org.apache.crimson.tree.XmlWritable)doc).writeXml(new org.apache.crimson.tree.XmlWriteContext(pw));
            pw.flush();
            System.out.println("---Test for default namespace support");
            marshaller = pmf.getXMLMarshaller("aaa/bbb/ccc", "");
            doc = db.newDocument();
            marshaller.marshall(employee, doc, doc);
            ((org.apache.crimson.tree.XmlWritable)doc).writeXml(new org.apache.crimson.tree.XmlWriteContext(pw));
            pw.flush();
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
    }
}
