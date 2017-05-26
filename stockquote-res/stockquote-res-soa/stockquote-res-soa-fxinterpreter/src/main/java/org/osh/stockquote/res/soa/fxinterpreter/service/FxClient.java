package org.osh.stockquote.res.soa.fxinterpreter.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FxClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(FxClient.class);
    private final String USER_AGENT = "Mozilla/5.0";

    public void test() throws ClientProtocolException, IOException{
    	LOGGER.debug("ABC");
    	
    	String url = "http://www.google.com/search?q=httpClient";

    	HttpClient client = HttpClientBuilder.create().build();
    	HttpGet request = new HttpGet(url);

    	// add request header
    	request.addHeader("User-Agent", USER_AGENT);
    	HttpResponse response = client.execute(request);

    	System.out.println("Response Code : "
    	                + response.getStatusLine().getStatusCode());

    	BufferedReader rd = new BufferedReader(
    		new InputStreamReader(response.getEntity().getContent()));

    	StringBuffer result = new StringBuffer();
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    		result.append(line);
    	}
    	LOGGER.debug("result.length()="+result.length());
    	
    }

}
