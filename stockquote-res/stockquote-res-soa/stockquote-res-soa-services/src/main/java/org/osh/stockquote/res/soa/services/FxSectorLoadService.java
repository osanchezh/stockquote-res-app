package org.osh.stockquote.res.soa.services;

public interface FxSectorLoadService {
	void loadSector(String csv);
	void loadIndustry(String csv);
	void loadIndustrySector(String csv);
	void loadCompany(String csv);
	void loadSymbol(String csv, int idstockexchange);
	void loadStock(String csv, int idstockexchange);
	void loadHistoryQuote(int idstockexchange);
	void loadStockStats(int idstockexchange);
	void loadStockQuote(int idstockexchange);
}
