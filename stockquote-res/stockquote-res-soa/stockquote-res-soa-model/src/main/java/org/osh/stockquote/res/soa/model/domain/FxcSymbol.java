package org.osh.stockquote.res.soa.model.domain;

import java.io.Serializable;

public class FxcSymbol implements Serializable {

	private static final long serialVersionUID = -5696848056736021916L;
	private int idSymbol;
    private String symbol;
    
    public int getIdSymbol() {
		return idSymbol;
	}
	public void setIdSymbol(int idSymbol) {
		this.idSymbol = idSymbol;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	@Override
	public String toString() {
		return "FxcSymbol [idSymbol=" + idSymbol + ", symbol=" + symbol + "]";
	}

}
