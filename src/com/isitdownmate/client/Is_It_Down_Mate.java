package com.isitdownmate.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;


public class Is_It_Down_Mate implements EntryPoint {

	private final URLCheckingServiceAsync greetingService = GWT
			.create(URLCheckingService.class);

	public void onModuleLoad() {
		final Button sendButton = new Button("Check");
		final TextBox websiteField = new TextBox();
		websiteField.setText("www.google.com.au");
		final Label resultLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("websiteFieldContainer").add(websiteField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("result").add(resultLabel);

		// Focus the cursor on the name field when the app loads
		websiteField.setFocus(true);
		websiteField.selectAll();

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {

			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			private void sendNameToServer() {
				// First, we validate the input.
				resultLabel.setText("Checking");
				String textToServer = websiteField.getText();
				
				greetingService.greetServer(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								resultLabel.setText("An error occured checking the website");
							}

							public void onSuccess(String result) {
								resultLabel.setText(result);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		websiteField.addKeyUpHandler(handler);
	}
}
