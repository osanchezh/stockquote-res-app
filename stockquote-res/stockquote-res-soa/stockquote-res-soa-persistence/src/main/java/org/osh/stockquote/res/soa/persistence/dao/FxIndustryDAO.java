package org.osh.stockquote.res.soa.persistence.dao;

public interface FxIndustryDAO {
	Integer selectCountIndustryByName(String name, Integer idSector);
	int insertCambStageConfirmacion(Integer idSector, String name,String description);
}
