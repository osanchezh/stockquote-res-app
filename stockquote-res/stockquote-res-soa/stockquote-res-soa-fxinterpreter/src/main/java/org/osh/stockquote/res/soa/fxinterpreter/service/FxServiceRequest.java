package org.osh.stockquote.res.soa.fxinterpreter.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.osh.stockquote.res.soa.fxinterpreter.model.StockQuotesRequest;

public class FxServiceRequest {
  public static final String EXTENSION=".csv";
  public void doRequest(String query){
	  
	  StockQuotesRequest stockQuotesRequest = new StockQuotesRequest(query);
      Map<String, String> params = new LinkedHashMap<String, String>();
      params.put("s", stockQuotesRequest.getQuery());
      params.put("f", stockQuotesRequest.getFieldsString());
      params.put("e", EXTENSION);
  }
}
