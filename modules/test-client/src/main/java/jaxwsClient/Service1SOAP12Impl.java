/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package jaxwsClient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * JAXWS Service Implementation for the Service1 interface as defined in the Service1JAXWSSOAP12.wsdl file
 *
 * The Service1.java interface contains full JAXWS annotations for the service
 */

@WebService( //endpointInterface = "jaxwsClient.Service1",
		    serviceName = "Service1ServiceJAXWS",
		    portName = "Service1PortJAXWS",
		    targetNamespace = "http://test.sca.oasisopen.org/",
		    wsdlLocation = "Service1JAXWSSOAP12.wsdl")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)		    
public class Service1SOAP12Impl implements Service1 {

    @WebMethod(action = "http://test.sca.oasisopen.org/operation1")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "operation1", targetNamespace = "http://test.sca.oasisopen.org/", className = "jaxwsClient.Operation1")
    @ResponseWrapper(localName = "operation1Response", targetNamespace = "http://test.sca.oasisopen.org/", className = "jaxwsClient.Operation1Response")
	public String operation1(@WebParam(name = "arg0", targetNamespace = "") String arg0) {
		return arg0 + " Client service1 operation1 invoked";
	}

} // end class Service1Impl
