package org.osh.stockquote.res.soa.model.csv;

import java.io.Serializable;

public class CompanyListCSV implements Serializable {

	private static final long serialVersionUID = 5903187767605880214L;
	
	private String symbol;
    private String name;
    private String lastSale;
    private String marketCap;
    private String ipoYear;
    private String sector;
    private String industry;
    private String summaryQuote;
    
    
    public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastSale() {
		return lastSale;
	}
	public void setLastSale(String lastSale) {
		this.lastSale = lastSale;
	}
	public String getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}
	public String getIpoYear() {
		return ipoYear;
	}
	public void setIpoYear(String ipoYear) {
		this.ipoYear = ipoYear;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getSummaryQuote() {
		return summaryQuote;
	}
	public void setSummaryQuote(String summaryQuote) {
		this.summaryQuote = summaryQuote;
	}
	
	@Override
	public String toString() {
		return "CompanyListCSV [symbol=" + symbol + ", name=" + name + ", lastSale=" + lastSale + ", marketCap="
				+ marketCap + ", ipoYear=" + ipoYear + ", sector=" + sector + ", industry=" + industry
				+ ", summaryQuote=" + summaryQuote + "]";
	}

    
}
