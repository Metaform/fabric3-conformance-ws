/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package client_bws;

/**
 * An extended version of the TestConfiguration class which holds additional metadata specific to some Web Services Binding testcases
 *
 */
public class BWSTestConfiguration extends TestConfiguration {
	
    // Additional BWS metadata
	// - for a set of BWS testcases, the client test runner must supply a Web service endpoint that can be invoked by
	//   references in the SCA application under test.  This Web service endpoint is characterized by the endpointAddress
	//   on which it is made available and also by the Java class which provides the implementation of the service
	public String endpointAddress = "";
	public String endpointClass = "";

	public BWSTestConfiguration() { super(); }
	

	// Gets the endpoint address for the Web service provided by the client
	public String getEndpointAddress() { return endpointAddress; }
	// Gets the name of the class used for the implementation of the Web service provided by the client
	public String getEndpointClass() { return endpointClass; }
}
