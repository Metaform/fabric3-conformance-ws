/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package client_bws;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.net.URL;
import java.util.UUID;

/**
 * Client for BWS_5005_TestCase
 * Tests that where an SCA service with a bidirectional interface is invoked by a Web services client, 
 * and the client invocation message contains a wsa:MessageID SOAP header block, that an invocation of 
 * a callback operation contains a wsa:RelatesTo SOAP header block
 */
public class BWS_5005_TestCase extends BWS_JAXWSTestRunner {
	
	static Endpoint endpoint;

    protected BWSTestConfiguration getBWSTestConfiguration() {
    	BWSTestConfiguration config = new BWSTestConfiguration();
    	config.testName 		 = this.getClass().getSimpleName().substring(0, 8);
    	config.input 		 	 = "request";
    	config.output[0] 		 = "BWS_5005 service1 operation1 invoked callback service5Callback callback1 invoked headers OK";
    	config.composite 		 = "Test_" + config.testName + ".composite";
    	config.testServiceName 	 = "TestClient";
    	config.contributionNames = new String[] { "BWS_General", "BWS_General_Java" };
    	
    	// Additional items for the client-side Web service
    	config.endpointAddress	 = "http://localhost:9081/JAXWS/Service5Callback";
    	config.endpointClass     = "jaxwsClient.Service5CallbackValidatingImpl";
    	return config;
    }
    
      private static final String SCA_TEST_NS = "http://test.sca.oasisopen.org/";
	private static final String CALL_ADDRESS = "http://localhost:8080/Service1";
    
    /**
     * Invoke the test via a JAX-WS web services interface
     * @param input - input string
     * @return the output string from the web service invocation
     * @throws Exception - if the Web service invocation fails (e.g. the target service does not exist)
     */
    public String invokeTest( String input ) throws Exception {
    	
    	// Dynamically invoke the Service5Callback using the supplied address
    	String callAddress = CALL_ADDRESS;
    	System.out.println("Service5 client - call address is: " + callAddress);
    	URL wsdlURL = this.getClass().getClassLoader().getResource("Service5.wsdl");
    	Service service5 = Service.create( wsdlURL, new QName( SCA_TEST_NS, "Service5Service") );
    	QName callPort = new QName(SCA_TEST_NS, "Service5Dynamic");
    	// Flip port for debug purposes
    	//callAddress = callAddress.replace("8080", "9080");
    	service5.addPort( callPort, SOAPBinding.SOAP11HTTP_BINDING , callAddress );
    	
    	Dispatch<SOAPMessage> dispatch = service5.createDispatch(callPort, 
    			                                                 SOAPMessage.class, 
    			                                                 Service.Mode.MESSAGE);
		try {
			// Invoke service5 providing a message ID
			String messageID = UUID.randomUUID().toString();
			relatedMsg = messageID;
			relatesRequired = true;
	        SOAPMessage response = dispatch.invoke( prepareRequest(dispatch, callAddress, null,
	        		                                               getBWSTestConfiguration().getEndpointAddress(),
	        		                                               null, messageID, input) );
	        String responseValue = getResponseText( response );
	            
	        return "BWS_5005 " + responseValue;
		} catch (SOAPException e) {
			e.printStackTrace();
			return "BWS_5005 service5 operation invocation failed";
		} // end try
		
    } // end method invokeTest

    private String getResponseText( SOAPMessage response ) throws SOAPException {
    	try {
	        SOAPElement responseMessage = 
	        	(SOAPElement)response.getSOAPBody().getChildElements(new QName(SCA_TEST_NS, "operation1Response")).next();
	        if( responseMessage != null ) {
	        	SOAPElement returnValue = (SOAPElement) responseMessage.getChildElements(new QName("", "return")).next();
	        	if (returnValue != null ) {
	        		return returnValue.getTextContent();
	        	}
	        }
    	} catch (Exception e) {
    		throw new SOAPException("BWS_5005 - exception while handling response to service5 invocation: ", e);
    	}
        return null;
    } // end method getResponseText  
    
} // end class Test_BWS_5005
