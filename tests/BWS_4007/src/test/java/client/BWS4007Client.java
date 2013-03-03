package client;

import client_bws.BWS_4007_TestCase;
import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.test.TestInvocation;

/**
 *
 */
public class BWS4007Client extends TestCase {
    private static final String EXPECTED = "BWS_4007 request service1 operation1 invoked request Client service1 operation1 invoked";

    @Reference
    protected TestInvocation invocation;

    public void testInvoke() throws Exception {
        BWS_4007_TestCase testCase = new BWS_4007_TestCase();
        try {
            testCase.setUp();
        } catch (Throwable e) {
           throw new RuntimeException(e);
        }
        assertEquals(EXPECTED, invocation.invokeTest("request"));

        testCase.tearDown();
    }
}
