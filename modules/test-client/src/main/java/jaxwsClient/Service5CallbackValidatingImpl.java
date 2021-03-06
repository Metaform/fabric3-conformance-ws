/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package jaxwsClient;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.WebServiceContext;

import client_bws.BWS_JAXWSTestRunner;

/**
 * JAXWS Service Implementation for the Service5Callback interface as defined in the Service5Callback.wsdl file, which validates
 * information in the SOAP headers of the callback request
 *
 * Service5Callback is the callback interface for the Service5 interface
 */

@WebService(serviceName = "Service5CallbackService",
		    portName = "Service5CallbackPort",
		    targetNamespace = "http://test.sca.oasisopen.org/",
		    wsdlLocation = "Service5Callback.wsdl")
public class Service5CallbackValidatingImpl implements Service5Callback {

	// Place to receive the SOAP context for this message...
	@Resource
	private WebServiceContext serviceCtx;
	
    @WebMethod(action = "http://test.sca.oasisopen.org/callback1")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "callback1", targetNamespace = "http://test.sca.oasisopen.org/", className = "jaxwsClient.Callback1")
    @ResponseWrapper(localName = "callback1Response", targetNamespace = "http://test.sca.oasisopen.org/", className = "jaxwsClient.Callback1Response")
	public String callback1(@WebParam(name = "arg0", targetNamespace = "") String arg0) {
    	// For this implementation, the Service5 interface is bidirectional and the client to this service must supply the
    	// Service5Callback interface, which is then called by this service.
    	
    	return arg0 + " service5Callback callback1 invoked " + BWS_JAXWSTestRunner.validateCallbackHeaders( serviceCtx );

	} // end method callback1

} // end class Service1Impl
