package com.isitdownmate.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("greet")
public interface URLCheckingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
}
