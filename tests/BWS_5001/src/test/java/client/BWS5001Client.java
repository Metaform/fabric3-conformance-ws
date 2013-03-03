package client;

import client_bws.BWS_5001_TestCase;
import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.test.TestInvocation;

/**
 *
 */
public class BWS5001Client extends TestCase {
    private static final String EXPECTED = "BWS_5001 request service1 operation1 invoked request Client service5 operation1 invoked service1 callback1 " +
                                           "invoked";
    @Reference
    protected TestInvocation invocation;

    public void testInvoke() throws Exception {
        BWS_5001_TestCase testCase = new BWS_5001_TestCase();
        try {
            testCase.setUp();
        } catch (Throwable e) {
           throw new RuntimeException(e);
        }
        assertEquals(EXPECTED, invocation.invokeTest("request"));

        testCase.tearDown();
    }
}
