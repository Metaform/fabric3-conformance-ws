/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package client_bws;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

import testClient.TestInvocation;

/**
 * A generic test client based on JAX-WS APIs
 */
public class BaseJAXWSTestRunner {

    private TestConfiguration testConfiguration;

    public BaseJAXWSTestRunner() {
        testConfiguration = getTestConfiguration();
    }

    /**
     * Invoke the test via a JAX-WS web services interface
     *
     * @param input - input string
     * @return the output string from the web service invocation
     * @throws Exception - if the Web service invocation fails (e.g. the target service does not exist)
     */
    public String invokeTest(String input) throws Exception {
        //Web service invocation via JAXWS
        QName serviceName = new QName("http://test.sca.oasisopen.org/", testConfiguration.getServiceName());
        URL wsdlLocation = this.getClass().getClassLoader().getResource(testConfiguration.getWsdlFileName());
        javax.xml.ws.Service webService = Service.create(wsdlLocation, serviceName);
        TestInvocation wsProxy = (TestInvocation) webService.getPort(testConfiguration.getServiceInterface());

        return wsProxy.invokeTest(input);
    }

    /**
     * The test configuration - this method is subclassed by individual testcases to provide detail about the test to run
     *
     * @return a Test Configuration data structure
     */
    protected TestConfiguration getTestConfiguration() {
        TestConfiguration config = new TestConfiguration();
        // The name of the test
        config.testName = "ASM_0001";
        // The input string for the test
        config.input = "request";
        // The expected output string from the test
        config.output[0] = config.testName + " " + config.input + " invoked ok";
        // The name of the composite to run for the SCA application under test
        config.composite = "Test_ASM_0101.composite";
        // The test service to invoke on the SCA application side
        config.testServiceName = "TestClient";
        // An array of contribution names
        config.contributionNames = new String[]{"General"};
        // The client service invocation class
        config.serviceInterface = TestInvocation.class;
        return config;
    }

}
