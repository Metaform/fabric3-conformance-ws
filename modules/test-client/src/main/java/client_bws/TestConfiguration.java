/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package client_bws;

import testClient.TestInvocation;

/** 
 * A class to hold the metadata about the test
 *
 */
public class TestConfiguration {
	
	public String testName;
	public String input;
	public String[] output = new String[1];
	public String composite;
	public String testServiceName;
	//public Class<?> testClass;		//TODO - does the client need this??
	public String[] contributionNames = null;
	public Class<?> serviceInterface = TestInvocation.class;
	
	public String wsdlFileName = "TestClient.wsdl";
	public String serviceName = "TestInvocationService";

	public TestConfiguration() { }
	
	public String getTestName() { return testName; }
	public String getInput() { return input; }
	public String[] getExpectedOutput() { return output; }
	public String getComposite() { return composite; }
	public String getTestServiceName() { return testServiceName; }
	//public Class<?> getTestClass() { return testClass; }
	public String[] getContributionNames() { return contributionNames; }
	public Class<?> getServiceInterface() { return serviceInterface; }
	// Gets the name of the WSDL file which describes the Service exposed by the SCA application
	public String getWsdlFileName() { return wsdlFileName; }
	// Gets the name of the Service exposed by the SCA application that is invoked to run the test
	public String getServiceName() { return serviceName; }
}
