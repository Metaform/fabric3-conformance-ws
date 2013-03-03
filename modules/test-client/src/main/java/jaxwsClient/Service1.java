
package jaxwsClient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.1 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "Service1", targetNamespace = "http://test.sca.oasisopen.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Service1 {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://test.sca.oasisopen.org/operation1")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "operation1", targetNamespace = "http://test.sca.oasisopen.org/", className = "jaxwsClient.Operation1")
    @ResponseWrapper(localName = "operation1Response", targetNamespace = "http://test.sca.oasisopen.org/", className = "jaxwsClient.Operation1Response")
    public String operation1(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
