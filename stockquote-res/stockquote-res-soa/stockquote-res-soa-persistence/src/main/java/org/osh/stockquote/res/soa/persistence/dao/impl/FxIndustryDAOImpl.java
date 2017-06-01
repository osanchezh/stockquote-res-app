package org.osh.stockquote.res.soa.persistence.dao.impl;

import org.osh.stockquote.res.soa.persistence.dao.FxIndustryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository("fxIndustryDAO")
public class FxIndustryDAOImpl implements FxIndustryDAO {

	@Autowired
    @Qualifier("fxExchangeNamedParameter")
    private NamedParameterJdbcTemplate fxExchangeNamedParameter;
	

	@Override
	public Integer selectCountIndustryByName(String name, Integer idSector) {
		  String query = "select count(idindustry) from FX_INDUSTRY where  FX_INDUSTRY.idsector=:idsector and FX_INDUSTRY.name=:name ";
	        MapSqlParameterSource parameters = new MapSqlParameterSource();
	        parameters.addValue("name", name);
	        parameters.addValue("idsector", idSector);
	        return fxExchangeNamedParameter.queryForObject(query, parameters, Integer.class);
	}

	@Override
	public int insertCambStageConfirmacion(Integer idSector, String name, String description) {
		String query = "INSERT INTO FX_INDUSTRY(idsector,name,description) values (:idsector,:name,:description)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        parameters.addValue("description", description);
        parameters.addValue("idsector", idSector);

    	return fxExchangeNamedParameter.update(query, parameters);
	}
	

	public NamedParameterJdbcTemplate getFxExchangeNamedParameter() {
		return fxExchangeNamedParameter;
	}

	public void setFxExchangeNamedParameter(NamedParameterJdbcTemplate fxExchangeNamedParameter) {
		this.fxExchangeNamedParameter = fxExchangeNamedParameter;
	}

}
