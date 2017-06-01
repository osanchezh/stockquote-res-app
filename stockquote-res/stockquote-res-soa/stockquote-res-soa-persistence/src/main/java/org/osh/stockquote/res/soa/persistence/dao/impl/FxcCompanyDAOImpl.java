package org.osh.stockquote.res.soa.persistence.dao.impl;

import org.osh.stockquote.res.soa.persistence.dao.FxcCompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository("fxcCompanyDAO")
public class FxcCompanyDAOImpl implements FxcCompanyDAO {

	@Autowired
    @Qualifier("fxExchangeNamedParameter")
    private NamedParameterJdbcTemplate fxExchangeNamedParameter;
	

	@Override
	public Integer selectCompanyByName(String name,Integer idindustry, Integer idsector) {
		String query = "select idcompany from fxc_company where name=:name and idindustry=:idindustry and idsector=:idsector";
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("name", name);
	    parameters.addValue("idindustry", idindustry);
	    parameters.addValue("idsector", idsector);
        Integer resultado=null;;
        try{
          resultado = fxExchangeNamedParameter.queryForObject(query, parameters, Integer.class);
        }catch(EmptyResultDataAccessException exception){
        	resultado =null;
        }
        return resultado;
	}

	@Override
	public int insertCompany(Integer idindustry, Integer idsector, String name, String description, String marketcap, String ipoyear) {
		String query = "INSERT INTO fxc_company(idindustry,idsector,name,description,marketcap,ipoyear) values (:idindustry,:idsector,:name,:description,:marketcap,:ipoyear)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idindustry", idindustry);
        parameters.addValue("idsector", idsector);
        parameters.addValue("name", name);
        parameters.addValue("description", description);
        parameters.addValue("marketcap", marketcap);
        parameters.addValue("ipoyear", ipoyear);

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
