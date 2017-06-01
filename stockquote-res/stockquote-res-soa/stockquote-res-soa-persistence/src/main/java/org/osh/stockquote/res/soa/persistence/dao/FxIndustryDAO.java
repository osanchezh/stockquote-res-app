package org.osh.stockquote.res.soa.persistence.dao;

public interface FxIndustryDAO {
	Integer selectCountIndustryByName(String name);
	 Integer selectIndustryByName(String name);
	int insertIndustry( String name,String description);
}
