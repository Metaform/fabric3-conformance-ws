/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package client_bws;

/**
 * Client for BWS_2014_TestCase
 * Tests that where a <component/> <reference/> has a <binding.ws/> element with @wsdlElement attribute 
 * referencing a WSDL binding element, that when the endpoint address of the target service is supplied 
 * as an <wsa:EndpointReference/> element, the service can be successfully invoked
 */
public class BWS_2014_TestCase { // extends BaseJAXWSTestRunner {

//    protected TestConfiguration getTestConfiguration() {
//    	TestConfiguration config = new TestConfiguration();
//    	config.testName 		 = this.getClass().getSimpleName().substring(0, 8);
//    	config.input 		 	 = "request";
//    	config.output[0]   		 = "BWS_2014 request service1 operation1 invoked service2 operation1 invoked";
//    	config.composite 		 = "Test_" + config.testName + ".composite";
//    	config.testServiceName 	 = "TestClient";
//    	config.contributionNames = new String[] { "BWS_2014", "BWS_General", "BWS_General" + _Lang };
//    	config.serviceInterface  = TestInvocation.class;
//    	return config;
//    }
    
} // end class Test_BWS_2014
