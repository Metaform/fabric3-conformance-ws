/*
 *
 * Copyright(C) OASIS(R) 2005,2009. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 *   
 */
package org.oasisopen.sca.test;

import org.oasisopen.sca.annotation.*;


/**
 * Java component implementation
 * 1 service with interface Service5, which has a callback
 * 0 references
 *
 * @author MikeEdwards
 *
 */
@Service(Service5.class)
public class Service5Impl implements Service5 {
	
	@Property
	public String serviceName = "service1";
	@Callback
	public Service5Callback callback;
	
	public Service5Impl() { super(); }

	/**
	 * Service operation which also invokes an operation on the callback
	 */
	public String operation1(String input) {
		if( callback == null ) return serviceName + "operation1 invoked but injected callback is null";
		String callbackResult = callback.callback1("callback");
		return serviceName + " operation1 invoked" + " " + callbackResult;
	}

} // end class
