/*
 *
 * Copyright(C) OASIS(R) 2009, 2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 *   
 */
package jaxwsClient;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * A remotable service interface used for a Callback from Service5
 */
@WebService(name = "Service5Callback", targetNamespace = "http://test.sca.oasisopen.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Service5Callback {
	
	/**
	 * Method 1 for invoking callback service
	 * @param input - input parameter(s) as a String
	 * @return - output data as a String
	 */
    @WebMethod(action = "http://test.sca.oasisopen.org/callback1")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "callback1", targetNamespace = "http://test.sca.oasisopen.org/", className = "jaxwsClient.Callback1")
    @ResponseWrapper(localName = "callback1Response", targetNamespace = "http://test.sca.oasisopen.org/", className = "jaxwsClient.Callback1Response")
	public String callback1( String input );
	
} // end interface
