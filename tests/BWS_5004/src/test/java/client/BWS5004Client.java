package client;

import client_bws.BWS_5004_TestCase;
import junit.framework.TestCase;

/**
 *
 */
public class BWS5004Client extends TestCase {
    private static final String EXPECTED = "BWS_5004 service1 operation1 invoked callback service5Callback callback1 invoked headers OK";

    public void testInvoke() throws Exception {
        BWS_5004_TestCase testCase = new BWS_5004_TestCase();
        try {
            testCase.setUp();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        assertEquals(EXPECTED, testCase.invokeTest("request"));

        testCase.tearDown();
    }
}
