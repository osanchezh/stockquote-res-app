package org.osh.stockquote.res.soa.persistence.dao;

public interface FxSectorDAO {
	Integer selectCountSectorByName(String name);
	int insertCambStageConfirmacion(String name,String description);
}
