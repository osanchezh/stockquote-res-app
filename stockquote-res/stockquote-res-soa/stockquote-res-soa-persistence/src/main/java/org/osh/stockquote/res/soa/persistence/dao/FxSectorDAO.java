package org.osh.stockquote.res.soa.persistence.dao;

public interface FxSectorDAO {
	Integer selectCountSectorByName(String name);
	Integer selectSectorByName(String name);
	int insertSector(String name,String description);
}
