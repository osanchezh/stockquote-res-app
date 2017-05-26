package org.osh.stockquote.res.soa.fxinterpreter.model;

import java.util.List;


public abstract class QuotesRequest {
	
	protected final String query;
    protected List<QuotesProperty> properties;
    
    public QuotesRequest(String query, List<QuotesProperty> properties) {
        this.query = query;
        this.properties = properties;
    }
    
    public List<QuotesProperty> getProperties() {
		return properties;
	}
	public void setProperties(List<QuotesProperty> properties) {
		this.properties = properties;
	}
	public String getQuery() {
		return query;
	}

    
}
