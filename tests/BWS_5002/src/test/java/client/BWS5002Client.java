package client;

import client_bws.BWS_5002_TestCase;
import junit.framework.TestCase;

/**
 *
 */
public class BWS5002Client extends TestCase {
    private static final String EXPECTED = "BWS_5002 service1 operation1 invoked callback service5Callback callback1 invoked";

    public void testInvoke() throws Exception {
        BWS_5002_TestCase testCase = new BWS_5002_TestCase();
        try {
            testCase.setUp();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        assertEquals(EXPECTED, testCase.invokeTest("request"));

        testCase.tearDown();
    }
}
