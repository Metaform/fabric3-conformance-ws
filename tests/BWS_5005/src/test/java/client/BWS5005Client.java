package client;

import client_bws.BWS_5005_TestCase;
import junit.framework.TestCase;

/**
 *
 */
public class BWS5005Client extends TestCase {
    private static final String EXPECTED = "BWS_5005 service1 operation1 invoked callback service5Callback callback1 invoked headers OK";

    public void testInvoke() throws Exception {
        BWS_5005_TestCase testCase = new BWS_5005_TestCase();
        try {
            testCase.setUp();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        assertEquals(EXPECTED, testCase.invokeTest("request"));

        testCase.tearDown();
    }
}
