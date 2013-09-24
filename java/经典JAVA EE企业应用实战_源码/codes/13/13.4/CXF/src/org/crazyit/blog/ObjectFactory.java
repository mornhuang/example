
package org.crazyit.blog;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.crazyit.Person_Type;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.crazyit.blog package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Somebd_QNAME = new QName("http://blog.crazyit.org", "somebd");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.crazyit.blog
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Person_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://blog.crazyit.org", name = "somebd")
    public JAXBElement<Person_Type> createSomebd(Person_Type value) {
        return new JAXBElement<Person_Type>(_Somebd_QNAME, Person_Type.class, null, value);
    }

}
