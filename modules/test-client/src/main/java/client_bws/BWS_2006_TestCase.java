/*
 * Copyright(C) OASIS(R) 2005,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package client_bws;

/**
 * Client for BWS_2006_TestCase
 * Tests that where a <component/> <reference/> has a <binding.ws/> element with @wsdlElement attribute 
 * referencing a WSDL service element, and the WSDL service element has 2 or more WSDL Ports available, 
 * that the SCA Runtime uses exactly one port for each invocation through the reference
 */
public class BWS_2006_TestCase { // extends BaseJAXWSTestRunner {

//    protected TestConfiguration getTestConfiguration() {
//    	TestConfiguration config = new TestConfiguration();
//    	config.testName 		 = this.getClass().getSimpleName().substring(0, 8);
//    	config.input 		 	 = "request";
//    	config.output    		 = new String[] {
//    		                          "BWS_2006 request service1 operation1 invoked service2 operation1 invoked",
//    			                      "BWS_2006 request service1 operation1 invoked service3 operation1 invoked" };
//    	config.composite 		 = "Test_" + config.testName + ".composite";
//    	config.testServiceName 	 = "TestClient";
//    	config.contributionNames = new String[] { "BWS_2006", "BWS_General", "BWS_General" + _Lang };
//    	config.serviceInterface  = TestInvocation.class;
//    	return config;
//    }
    
} // end class Test_BWS_2006
