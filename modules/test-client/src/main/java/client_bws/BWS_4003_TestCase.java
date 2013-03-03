/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package client_bws;

import javax.xml.ws.Endpoint;

/**
 * Client for BWS_4003_TestCase
 * Tests that where a <component/> <service/> element has a <binding.ws/> subelement to which the SOAP.v1_2 intent is applied, 
 * that the service endpoint both sends and receives messages in in the SOAP v1.2 protocol
 */
public class BWS_4003_TestCase { // extends BaseJAXWSTestRunner {
	
	static Endpoint endpoint;

//    protected TestConfiguration getTestConfiguration() {
//    	TestConfiguration config = new TestConfiguration();
//    	config.testName 		 = this.getClass().getSimpleName().substring(0, 8);
//    	config.input 		 	 = "request";
//    	config.output[0] 		 = "BWS_4003 request service1 operation1 invoked";
//    	config.composite 		 = "Test_" + config.testName + ".composite";
//    	config.testServiceName 	 = "TestClient";
//    	config.contributionNames = new String[] { "BWS_General", "BWS_General" + _Lang };
//
//    	config.serviceName = "TestInvocationServiceSOAP12";
//    	return config;
//    }
    
} // end class Test_BWS_4003
