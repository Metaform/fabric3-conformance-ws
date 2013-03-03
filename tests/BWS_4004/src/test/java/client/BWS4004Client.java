package client;

import client_bws.BWS_4004_TestCase;
import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.test.TestInvocation;

/**
 *
 */
public class BWS4004Client extends TestCase {
    private static final String EXPECTED = "BWS_4004 request service1 operation1 invoked request Client service1 operation1 invoked";

    @Reference
    protected TestInvocation invocation;

    public void testInvoke() throws Exception {
        BWS_4004_TestCase testCase = new BWS_4004_TestCase();
        try {
            testCase.setUp();
        } catch (Throwable e) {
           throw new RuntimeException(e);
        }
        assertEquals(EXPECTED, invocation.invokeTest("request"));

        testCase.tearDown();
    }
}
