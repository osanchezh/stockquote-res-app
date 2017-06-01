package org.osh.stockquote.res.soa.services;

public interface FxSectorLoadService {
	void load();
	void loadIndustry();
	void loadIndustrySector();
	void loadCompany();
	void loadSymbol();
}
