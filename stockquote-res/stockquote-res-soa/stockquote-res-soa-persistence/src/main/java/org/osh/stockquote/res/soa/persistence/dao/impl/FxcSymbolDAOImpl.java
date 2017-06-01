package org.osh.stockquote.res.soa.persistence.dao.impl;

import org.osh.stockquote.res.soa.persistence.dao.FxcSymbolDAO;
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
	public NamedParameterJdbcTemplate getFxExchangeNamedParameter() {
		return fxExchangeNamedParameter;
	}
	public void setFxExchangeNamedParameter(NamedParameterJdbcTemplate fxExchangeNamedParameter) {
		this.fxExchangeNamedParameter = fxExchangeNamedParameter;
	}
}
