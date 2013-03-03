package client;

import client_bws.BWS_4001_TestCase;
import client_bws.BaseJAXWSTestRunner;
import junit.framework.TestCase;

/**
 *
 */
public class BWS4001Client extends TestCase {
    private static final String EXPECTED = "BWS_4001 request service1 operation1 invoked";

    public void testInvoke() throws Exception {
        BaseJAXWSTestRunner test = new BaseJAXWSTestRunner(){};
        assertEquals(EXPECTED, test.invokeTest("request"));
    }
}
