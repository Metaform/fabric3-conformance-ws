package client;

import javax.xml.ws.soap.SOAPFaultException;

import client_bws.BWS_5003_TestCase;
import junit.framework.TestCase;

/**
 *
 */
public class BWS5003Client extends TestCase {

    public void testInvoke() throws Exception {
        BWS_5003_TestCase testCase = new BWS_5003_TestCase();
        try {
            testCase.setUp();
            testCase.invokeTest("request");
            fail();
        } catch (SOAPFaultException e) {
            assertTrue(e.getMessage().contains("Invalid Callback Address: http://www.w3.org/2005/08/addressing/anonymous"));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            testCase.tearDown();
        }

    }
}
