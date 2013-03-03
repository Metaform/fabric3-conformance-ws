/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package jaxwsClient;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPBinding;

/**
 * JAXWS Service Implementation for the Service5 interface as defined in the Service5.wsdl file
 *
 * Service5 is bidirectional and has an associated Service5Callback interface which this implementation uses
 * to call back to the invoker of the Service5 operation
 */

@WebService(serviceName = "Service5Service",
		    portName = "Service5Port",
		    targetNamespace = "http://test.sca.oasisopen.org/",
		    wsdlLocation = "Service5.wsdl")
public class Service5Impl implements Service5 {
	
	// Place to receive the SOAP context for this message...
	@Resource
	private WebServiceContext serviceCtx;

    @WebMethod(action = "http://test.sca.oasisopen.org/operation1")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "operation1", targetNamespace = "http://test.sca.oasisopen.org/", className = "jaxwsClient.Operation1")
    @ResponseWrapper(localName = "operation1Response", targetNamespace = "http://test.sca.oasisopen.org/", className = "jaxwsClient.Operation1Response")
	public String operation1(@WebParam(name = "arg0", targetNamespace = "") String arg0) {
    	// For this implementation, the Service5 interface is bidirectional and the client to this service must supply the
    	// Service5Callback interface, which is then called by this service.
    	
    	// The address of the Service5Callback service must be supplied using a SOAP header - either the wsa:From header or the
    	// wsa:ReplyTo header.  The first task is to read these headers and obtain the address.
    	String callbackAddress = getCallbackAddress();
    	if( callbackAddress == null ) return "Service5 callback invocation failed - callback address not found";
    	
    	// Dynamically invoke the Service5Callback using the supplied address
    	System.out.println("Service5Impl - callback address is: " + callbackAddress);
    	URL wsdlURL = this.getClass().getClassLoader().getResource("Service5Callback.wsdl");
    	Service callbackService = Service.create( wsdlURL, new QName( SCA_TEST_NS, "Service5CallbackService") );
    	QName callbackPort = new QName(SCA_TEST_NS, "Service5CallbackDynamic");

    	callbackService.addPort( callbackPort, SOAPBinding.SOAP11HTTP_BINDING , callbackAddress );
    	
    	Dispatch<SOAPMessage> dispatch = callbackService.createDispatch(callbackPort, 
    			                                                        SOAPMessage.class, 
    			                                                        Service.Mode.MESSAGE);
		try {
	        // Invoke the callback
	        SOAPMessage response = dispatch.invoke( prepareRequest(dispatch) );
	        
	        String responseValue = getResponseText( response );
	        
	        return arg0 + " Client service5 operation1 invoked " + responseValue;
		} catch (SOAPException e) {
			e.printStackTrace();
			return arg0 + " Client service5 operation callback invocation failed";
		} // end try

	} // end method operation1
    
    private SOAPMessage prepareRequest(Dispatch<SOAPMessage> dispatch) throws SOAPException {
    	MessageFactory factory = MessageFactory.newInstance();
    	SOAPMessage request = factory.createMessage();
        SOAPEnvelope envelope = request.getSOAPPart().getEnvelope();
        
        Map<String, Object> reqContext = dispatch.getRequestContext();
        
        // Create the request
        request.getSOAPBody().
          addBodyElement(envelope.createName("callback1", "ns2", "http://test.sca.oasisopen.org/")) .
          //addChildElement(envelope.createName("arg0")) .
          addChildElement(new QName(null, "arg0", "")) .
          addTextNode("callback");
        
        return request;
    } // end method prepareRequest
    
    private String getResponseText( SOAPMessage response ) throws SOAPException {
    	try {
	        SOAPElement responseMessage = 
	        	(SOAPElement)response.getSOAPBody().getChildElements(new QName(SCA_TEST_NS, "callback1Response")).next();
	        if( responseMessage != null ) {
	        	SOAPElement returnValue = (SOAPElement) responseMessage.getChildElements(new QName("", "return")).next();
	        	if (returnValue != null ) {
	        		return returnValue.getTextContent();
	        	}
	        }
    	} catch (Exception e) {
    		throw new SOAPException("Service5Impl - exception while handling response to callback invocation: ", e);
    	}
        return null;
    } // end method getResponseText
    
    // Namespace for SCA test services
    private static String SCA_TEST_NS = "http://test.sca.oasisopen.org/";
    private static final String ADDRESS_KEY = "returnAddress";
    
    /**
     * Extract the return address for the callback from the context - it is stored by the Handler into a
     * field with the name "returnAddress"
     * @return
     */
    private String getCallbackAddress() {
    	if( serviceCtx == null ) {
    		System.out.println("Service5Impl - Web Service Context is null");
    		return null;
    	} // end if
    	
    	MessageContext theContext = serviceCtx.getMessageContext();
    	
    	String returnAddress = (String)theContext.get(ADDRESS_KEY);
    	
    	if (returnAddress == null) {
    		System.out.println("Service5Impl - Message Headers have no return address");
    	} // end if
    	
    	return returnAddress;
    } // end method getCallbackAddress

} // end class Service1Impl
