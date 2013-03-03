/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package client_bws;

/**
 * Client for BWS_2022_TestCase
 * Tests that where a <binding.ws/> subelement of a component <reference/>  has a @wsdlElement attribute which 
 * references a Port element of a WSDL document and the Port element has an associated portType which contains 
 * an sca @callback extension attribute, but where the <reference/> interface declaration does not have a callback, 
 * that the portType is treated as being not compatible with the interface for the reference and that an error is raised.
 * 
 */
public class BWS_2022_TestCase { // extends BaseJAXWSTestRunner {

//    protected TestConfiguration getTestConfiguration() {
//    	TestConfiguration config = new TestConfiguration();
//    	config.testName 		 = this.getClass().getSimpleName().substring(0, 8);
//    	config.input 		 	 = "request";
//    	config.output[0] 		 = "exception";
//    	config.composite 		 = "Test_" + config.testName + ".composite";
//    	config.testServiceName 	 = "TestClient";
//    	config.contributionNames = new String[] { "BWS_2022", "BWS_General", "BWS_General" + _Lang };
//    	config.serviceInterface  = TestInvocation.class;
//    	return config;
//    }
    
} // end class Test_BWS_2022
