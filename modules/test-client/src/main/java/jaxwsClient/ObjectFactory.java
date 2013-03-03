
package jaxwsClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxwsClient package. 
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

    private final static QName _Operation1ResponseReturn_QNAME = new QName("", "return");
    private final static QName _Operation1Arg0_QNAME = new QName("", "arg0");
    private final static QName _Callback1ResponseReturn_QNAME = new QName("", "return");
    private final static QName _Callback1Arg0_QNAME = new QName("", "arg0");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxwsClient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Operation1Response }
     * 
     */
    public Operation1Response createOperation1Response() {
        return new Operation1Response();
    }

    /**
     * Create an instance of {@link Operation1 }
     * 
     */
    public Operation1 createOperation1() {
        return new Operation1();
    }

    /**
     * Create an instance of {@link Callback1Response }
     * 
     */
    public Callback1Response createCallback1Response() {
        return new Callback1Response();
    }

    /**
     * Create an instance of {@link Callback1 }
     * 
     */
    public Callback1 createCallback1() {
        return new Callback1();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = Operation1Response.class)
    public JAXBElement<String> createOperation1ResponseReturn(String value) {
        return new JAXBElement<String>(_Operation1ResponseReturn_QNAME, String.class, Operation1Response.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg0", scope = Operation1 .class)
    public JAXBElement<String> createOperation1Arg0(String value) {
        return new JAXBElement<String>(_Operation1Arg0_QNAME, String.class, Operation1 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = Callback1Response.class)
    public JAXBElement<String> createCallback1ResponseReturn(String value) {
        return new JAXBElement<String>(_Callback1ResponseReturn_QNAME, String.class, Callback1Response.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg0", scope = Callback1 .class)
    public JAXBElement<String> createCallback1Arg0(String value) {
        return new JAXBElement<String>(_Callback1Arg0_QNAME, String.class, Callback1 .class, value);
    }

}
