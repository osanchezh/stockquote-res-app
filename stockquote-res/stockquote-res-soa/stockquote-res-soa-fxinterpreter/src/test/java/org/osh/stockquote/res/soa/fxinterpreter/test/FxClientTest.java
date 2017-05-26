package org.osh.stockquote.res.soa.fxinterpreter.test;

import java.io.IOException;

import org.junit.Test;
import org.osh.stockquote.res.soa.fxinterpreter.service.FxClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FxClientTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(FxClientTest.class);
	@Test
	public void init(){
		LOGGER.debug("ABC");
		FxClient fxClient=new FxClient();
		try {
			fxClient.test();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
