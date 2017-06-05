package org.osh.stockquote.res.soa.persistence.dao.impl;


import org.osh.stockquote.res.soa.persistence.dao.FxSectorDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
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
        String query = "select count(idsector) from fxc_sector where name=:name";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        return fxExchangeNamedParameter.queryForObject(query, parameters, Integer.class);
	}
	
	public Integer selectSectorByName(String name)  {
        String query = "select idsector from fxc_sector where name=:name";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        Integer resultado=null;;
        try{
          resultado = fxExchangeNamedParameter.queryForObject(query, parameters, Integer.class);
        }catch(EmptyResultDataAccessException exception){
        	resultado =null;
        }
        return resultado;
	}
	
    public int insertSector(String name,String description){
    	String query = "INSERT INTO fxc_sector(name,description) values (:name,:description)";
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
