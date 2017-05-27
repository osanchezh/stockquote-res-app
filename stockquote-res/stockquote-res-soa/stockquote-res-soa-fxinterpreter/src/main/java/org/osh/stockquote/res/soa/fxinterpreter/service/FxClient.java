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
    	
    	String url = "http://download.finance.yahoo.com/d/quotes.csv?s=GOOG&f=nsc4xab2sa5sbb3sb6sl1sk3sd1t1opghva2kjm3m4sj2sss1sj1sf6sr1qdyee7e9e8rr5p6p5b4s6j4t8s7&e=.csv";

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
    		LOGGER.debug("line="+line);
    	}
    	LOGGER.debug("result.length()="+result.length());
    	
    }

}
