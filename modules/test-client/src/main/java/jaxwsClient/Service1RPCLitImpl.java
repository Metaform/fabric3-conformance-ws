/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package jaxwsClient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * JAXWS Service Implementation for the Service1 interface as defined in the Service1RPCLiteral.wsdl file
 *
 * The WSDL uses RPC Literal form for the service interface
 */

@WebService(serviceName = "Service1ServiceRPCLit",
		    portName = "Service1PortRPCLit",
		    targetNamespace = "http://test.sca.oasisopen.org/",
		    wsdlLocation = "Service1RPCLiteral.wsdl")
@SOAPBinding(style = SOAPBinding.Style.RPC)		    
public class Service1RPCLitImpl implements Service1 {

    @WebMethod(action = "http://test.sca.oasisopen.org/operation1")
    @WebResult(name = "operation1Response", partName = "operation1Response")
	public String operation1(@WebParam(name = "operation1", partName = "operation1") String arg0) {
		return arg0 + " Client service1 operation1 invoked";
	}

} // end class Service1Impl
