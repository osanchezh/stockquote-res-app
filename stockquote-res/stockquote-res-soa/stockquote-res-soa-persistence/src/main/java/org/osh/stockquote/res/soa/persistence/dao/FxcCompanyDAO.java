package org.osh.stockquote.res.soa.persistence.dao;

public interface FxcCompanyDAO {
	Integer selectCompanyByName(String name,Integer idindustry, Integer idsector);
	int insertCompany(Integer idindustry, Integer idsector, String name, String description);
}
