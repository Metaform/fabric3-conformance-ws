/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package client_bws;

import javax.xml.ws.Endpoint;

/**
 * Client for BWS_5001_TestCase
 * Tests that where an SCA reference with a bidirectional interface invokes a Web service, 
 * that the Web service message sent to the target service contains a Callback EPR, 
 * either in the wsa:From SOAP header or in the wsa:ReplyTo header
 */
public class BWS_5001_TestCase extends BWS_JAXWSTestRunner {
	
	static Endpoint endpoint;

    protected BWSTestConfiguration getBWSTestConfiguration() {
    	BWSTestConfiguration config = new BWSTestConfiguration();
    	config.testName 		 = this.getClass().getSimpleName().substring(0, 8);
    	config.input 		 	 = "request";
    	config.output[0] 		 = "BWS_5001 request service1 operation1 invoked request Client service5 operation1 invoked service1 callback1 invoked";
    	config.composite 		 = "Test_" + config.testName + ".composite";
    	config.testServiceName 	 = "TestClient";
    	config.contributionNames = new String[] { "BWS_General", "BWS_General_Java" };

    	// Additional items for the client-side Web service
    	config.endpointAddress	 = "http://localhost:9081/JAXWS/Service1";
    	config.endpointClass     = "jaxwsClient.Service5Impl";
    	return config;
    }
    
} // end class Test_BWS_5001
