package com.isitdownmate.server;

import com.isitdownmate.client.URLCheckingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;

@SuppressWarnings("serial")
public class URLCheckingServiceImpl extends RemoteServiceServlet implements
		URLCheckingService {

	public String checkURL(String input){
		
		if(input.length() > 8){
			if(input.toLowerCase().startsWith("http://")){
				return input;
			}
		}
		
		return "http://" + input;
		
	}
	public String greetServer(String input) throws IllegalArgumentException {
		
		try {
		
			String strURL = checkURL(input);
			
			URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            int response = connection.getResponseCode();
            String message = "";
            
			switch (response) {
				case 200:  message = "is UP!";
					break;
				case 403:  message = "is UP however you are not authorised to view it!";
					break;
				case 404:  message = "is UP however the page could not be found!";
					break;
				default: message = "is DOWN!";
					break;
			}

            return input + " " + message;

        } catch (MalformedURLException e) {
        	return input + " Is Not a Valid URL!";
        } catch (IOException e) {
        	return input + " Is Down!";
        }
	
	}

}
