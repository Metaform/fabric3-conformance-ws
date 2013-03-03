/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package client_bws;

import javax.xml.ws.Endpoint;

/**
 * Client for BWS_4008_TestCase
 * Tests that where <component/> <reference/> element has an explicit portType rpc-encoded in form, it is rejected
 */
public class BWS_4008_TestCase extends BWS_JAXWSTestRunner {
	
	static Endpoint endpoint;

    protected BWSTestConfiguration getBWSTestConfiguration() {
    	BWSTestConfiguration config = new BWSTestConfiguration();
//    	config.testName 		 = this.getClass().getSimpleName().substring(0, 8);
//    	config.input 		 	 = "request";
//    	config.output[0] 		 = "exception";
//    	config.composite 		 = "Test_" + config.testName + ".composite";
//    	config.testServiceName 	 = "TestClient";
//    	config.contributionNames = new String[] { "BWS_4008", "BWS_General", "BWS_General" + _Lang };
//
//    	// Additional items for the client-side Web service
//    	config.endpointAddress	 = "http://localhost:9081/JAXWS/Service1";
//    	config.endpointClass     = "jaxwsClient.Service1RPCEncodedImpl";
    	return config;
    }
    
} // end class Test_BWS_4008
