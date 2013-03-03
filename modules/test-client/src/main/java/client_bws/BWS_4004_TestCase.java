/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package client_bws;

/**
 * Client for BWS_4004_TestCase
 * Tests that where a <component/> <reference/> element has a <binding.ws/> subelement to which the SOAP intent is applied, 
 * that the reference both sends and receives messages in a version of the SOAP protocol
 */
public class BWS_4004_TestCase extends BWS_JAXWSTestRunner {
	
    protected BWSTestConfiguration getBWSTestConfiguration() {
    	BWSTestConfiguration config = new BWSTestConfiguration();
    	config.testName 		 = this.getClass().getSimpleName().substring(0, 8);
    	config.input 		 	 = "request";
    	config.output[0] 		 = "BWS_4004 request service1 operation1 invoked request Client service1 operation1 invoked";
    	config.composite 		 = "Test_" + config.testName + ".composite";
    	config.testServiceName 	 = "TestClient";
    	config.contributionNames = new String[] { "BWS_General", "BWS_General_Java" };

    	// Additional items for the client-side Web service
    	config.endpointAddress	 = "http://localhost:9081/JAXWS/Service1";
    	config.endpointClass     = "jaxwsClient.Service1Impl";
    	return config;
    }
    
} // end class Test_BWS_4004
