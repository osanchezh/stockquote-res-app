package org.osh.stockquote.res.soa.persistence.dao.impl;


import org.osh.stockquote.res.soa.persistence.dao.FxSectorDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("fxSectorDAO")
public class FxSectorDAOImpl implements FxSectorDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(FxSectorDAOImpl.class);

	@Autowired
    @Qualifier("fxExchangeNamedParameter")
    private NamedParameterJdbcTemplate fxExchangeNamedParameter;

	public Integer selectCountSectorByName(String name)  {
        String query = "select count(idsector) from FX_SECTOR where FX_SECTOR.name=:name";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        return fxExchangeNamedParameter.queryForObject(query, parameters, Integer.class);
	}
	
    public int insertCambStageConfirmacion(String name,String description){
    	String query = "INSERT INTO FX_SECTOR(name,description) values (:name,:description)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        parameters.addValue("description", description);
    	return fxExchangeNamedParameter.update(query, parameters);
    }
    
	
	public NamedParameterJdbcTemplate getFxExchangeNamedParameter() {
		return fxExchangeNamedParameter;
	}

	public void setFxExchangeNamedParameter(NamedParameterJdbcTemplate fxExchangeNamedParameter) {
		this.fxExchangeNamedParameter = fxExchangeNamedParameter;
	}
}
