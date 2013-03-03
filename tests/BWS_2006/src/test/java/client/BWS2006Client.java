package client;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.test.TestInvocation;

/**
 *
 */
public class BWS2006Client extends TestCase {
    private static final String EXPECTED1 = "BWS_2006 request service1 operation1 invoked service2 operation1 invoked";
    private static final String EXPECTED2 = "BWS_2006 request service1 operation1 invoked service3 operation1 invoked";

    @Reference
    protected TestInvocation invocation;

    public void testInvoke() throws Exception {
        String result = invocation.invokeTest("request");
        assertTrue(result.equals(EXPECTED1) || result.equals(EXPECTED2));
    }
}
