package client;

import client_bws.BaseJAXWSTestRunner;
import junit.framework.TestCase;

/**
 *
 */
public class BWS4002Client extends TestCase {
    private static final String EXPECTED = "BWS_4002 request service1 operation1 invoked";

    public void testInvoke() throws Exception {
        BaseJAXWSTestRunner test = new BaseJAXWSTestRunner(){};
        assertEquals(EXPECTED, test.invokeTest("request"));
    }
}
