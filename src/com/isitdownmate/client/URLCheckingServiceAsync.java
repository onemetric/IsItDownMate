package com.isitdownmate.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface URLCheckingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
