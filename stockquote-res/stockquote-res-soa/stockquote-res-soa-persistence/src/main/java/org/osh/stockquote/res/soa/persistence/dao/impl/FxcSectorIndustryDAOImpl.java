package org.osh.stockquote.res.soa.persistence.dao.impl;

import java.util.List;

import org.osh.stockquote.res.soa.persistence.dao.FxcSectorIndustryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository("fxcSectorIndustryDAO")
public class FxcSectorIndustryDAOImpl implements FxcSectorIndustryDAO {

	@Autowired
    @Qualifier("fxExchangeNamedParameter")
    private NamedParameterJdbcTemplate fxExchangeNamedParameter;
	

	@Override
	public List<Integer> selectSector(int idindustry) {
		String query = "select idsector from fxc_sector_industry where idindustry=:idindustry ";
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("idindustry", idindustry);
	    return fxExchangeNamedParameter.queryForList(query, parameters, Integer.class);
	}
	@Override
	public int insertIndustrySector(int idindustry, int idsector) {
		String query = "INSERT INTO fxc_sector_industry(idindustry,idsector) values (:idindustry,:idsector)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idindustry", idindustry);
        parameters.addValue("idsector", idsector);
       // parameters.addValue("idsector", idSector);
    	return fxExchangeNamedParameter.update(query, parameters);
	}
	
	public NamedParameterJdbcTemplate getFxExchangeNamedParameter() {
		return fxExchangeNamedParameter;
	}

	public void setFxExchangeNamedParameter(NamedParameterJdbcTemplate fxExchangeNamedParameter) {
		this.fxExchangeNamedParameter = fxExchangeNamedParameter;
	}
}
