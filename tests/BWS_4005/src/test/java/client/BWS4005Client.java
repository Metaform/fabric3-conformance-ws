package client;

import client_bws.BWS_4005_TestCase;
import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.test.TestInvocation;

/**
 *
 */
public class BWS4005Client extends TestCase {
    private static final String EXPECTED = "BWS_4005 request service1 operation1 invoked request Client service1 operation1 invoked";

    @Reference
    protected TestInvocation invocation;

    public void testInvoke() throws Exception {
        BWS_4005_TestCase testCase = new BWS_4005_TestCase();
        try {
            testCase.setUp();
        } catch (Throwable e) {
           throw new RuntimeException(e);
        }
        assertEquals(EXPECTED, invocation.invokeTest("request"));

        testCase.tearDown();
    }
}
