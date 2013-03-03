package client;

import client_bws.BWS_4006_TestCase;
import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.test.TestInvocation;

/**
 *
 */
public class BWS4006Client extends TestCase {
    private static final String EXPECTED = "BWS_4006 request service1 operation1 invoked request Client service1 operation1 invoked";

    @Reference
    protected TestInvocation invocation;

    public void testInvoke() throws Exception {
        BWS_4006_TestCase testCase = new BWS_4006_TestCase();
        try {
            testCase.setUp();
        } catch (Throwable e) {
           throw new RuntimeException(e);
        }
        assertEquals(EXPECTED, invocation.invokeTest("request"));

        testCase.tearDown();
    }
}
