package org.osh.stockquote.res.soa.persistence.dao.impl;

import java.util.List;

import org.osh.stockquote.res.soa.model.domain.FxcSymbol;
import org.osh.stockquote.res.soa.persistence.dao.FxcSymbolDAO;
import org.osh.stockquote.res.soa.persistence.mapper.FxcSymbolRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository("fxcSymbolDAO")
public class FxcSymbolDAOImpl implements FxcSymbolDAO {
	@Autowired
    @Qualifier("fxExchangeNamedParameter")
    private NamedParameterJdbcTemplate fxExchangeNamedParameter;

	@Override
	public Integer insertSymbol(String symbol, int idcompany, int idstockexchange, String summaryquote) {
		String query = "INSERT INTO fxc_symbol(symbol,idcompany,idstockexchange,summaryquote) values (:symbol,:idcompany,:idstockexchange,:summaryquote)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("symbol", symbol);
        parameters.addValue("idcompany", idcompany);
        parameters.addValue("idstockexchange", idstockexchange);
        parameters.addValue("summaryquote", summaryquote);
    	return fxExchangeNamedParameter.update(query, parameters);
	}
	
	@Override
	public List<FxcSymbol> selectSymbolByIdStockExchange(int idstockexchange) {
		String query = "select idsymbol, symbol from fxc_symbol where idstockexchange=:idstockexchange order by idsymbol asc";
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("idstockexchange", idstockexchange);
	    return fxExchangeNamedParameter.query(query, parameters, new FxcSymbolRowMapper());
	}
	
	public NamedParameterJdbcTemplate getFxExchangeNamedParameter() {
		return fxExchangeNamedParameter;
	}
	public void setFxExchangeNamedParameter(NamedParameterJdbcTemplate fxExchangeNamedParameter) {
		this.fxExchangeNamedParameter = fxExchangeNamedParameter;
	}

}
