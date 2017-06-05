package org.osh.stockquote.res.soa.persistence.dao.impl;

import org.osh.stockquote.res.soa.persistence.dao.FxIndustryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository("fxIndustryDAO")
public class FxIndustryDAOImpl implements FxIndustryDAO {

	@Autowired
    @Qualifier("fxExchangeNamedParameter")
    private NamedParameterJdbcTemplate fxExchangeNamedParameter;
	

	@Override
	public Integer selectCountIndustryByName(String name) {
		  String query = "select count(idindustry) from fxc_industry where name=:name ";
	        MapSqlParameterSource parameters = new MapSqlParameterSource();
	        parameters.addValue("name", name);
	        //parameters.addValue("idsector", idSector);
	        return fxExchangeNamedParameter.queryForObject(query, parameters, Integer.class);
	}

	@Override
	public int insertIndustry(String name, String description) {
		String query = "INSERT INTO fxc_industry(name,description) values (:name,:description)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        parameters.addValue("description", description);
       // parameters.addValue("idsector", idSector);

    	return fxExchangeNamedParameter.update(query, parameters);
	}
	
	@Override
	public Integer selectIndustryByName(String name) {
		  String query = "select idindustry from fxc_industry where fxc_industry.name=:name ";
	        MapSqlParameterSource parameters = new MapSqlParameterSource();
	        parameters.addValue("name", name);
	        //parameters.addValue("idsector", idSector);
	        Integer resultado=null;
	        try{
	          resultado = fxExchangeNamedParameter.queryForObject(query, parameters, Integer.class);
	        }catch(EmptyResultDataAccessException exception){
	        	resultado =null;
	        }
	        return resultado;
	}
	public NamedParameterJdbcTemplate getFxExchangeNamedParameter() {
		return fxExchangeNamedParameter;
	}

	public void setFxExchangeNamedParameter(NamedParameterJdbcTemplate fxExchangeNamedParameter) {
		this.fxExchangeNamedParameter = fxExchangeNamedParameter;
	}



}
