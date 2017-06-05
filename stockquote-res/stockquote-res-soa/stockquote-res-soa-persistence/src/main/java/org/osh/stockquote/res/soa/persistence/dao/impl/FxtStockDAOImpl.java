package org.osh.stockquote.res.soa.persistence.dao.impl;

import org.osh.stockquote.res.soa.persistence.dao.FxtStockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("fxtStockDAO")
public class FxtStockDAOImpl implements  FxtStockDAO {

	@Autowired
    @Qualifier("fxExchangeNamedParameter")
    private NamedParameterJdbcTemplate fxExchangeNamedParameter;
	


	@Override
	public Integer selectIdStockByIdSymbol(int idsymbol) {
		String query = "select idstock from fxt_stock where idsymbol=:idsymbol";
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("idsymbol", idsymbol);
        Integer resultado=null;
        try{
          resultado = fxExchangeNamedParameter.queryForObject(query, parameters, Integer.class);
        }catch(EmptyResultDataAccessException exception){
        	resultado =null;
        }
        return resultado;
	}

	@Override
	public Integer insertStock(int idsymbol, String currency, String stockexchange) {
		String query = "INSERT INTO fxt_stock(idsymbol,currency,stockexchange) values (:idsymbol,:currency,:stockexchange)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idsymbol", idsymbol);
        parameters.addValue("currency", currency);
        parameters.addValue("stockexchange", stockexchange);
    	return fxExchangeNamedParameter.update(query, parameters);
	}
	
	public NamedParameterJdbcTemplate getFxExchangeNamedParameter() {
		return fxExchangeNamedParameter;
	}

	public void setFxExchangeNamedParameter(NamedParameterJdbcTemplate fxExchangeNamedParameter) {
		this.fxExchangeNamedParameter = fxExchangeNamedParameter;
	}

}
