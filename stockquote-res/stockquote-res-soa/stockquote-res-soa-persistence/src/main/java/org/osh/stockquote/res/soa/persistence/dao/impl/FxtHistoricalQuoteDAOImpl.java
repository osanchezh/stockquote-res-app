package org.osh.stockquote.res.soa.persistence.dao.impl;

import java.util.Date;

import org.osh.stockquote.res.soa.persistence.dao.FxtHistoricalQuoteDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import yahoofinance.histquotes.HistoricalQuote;

@Repository("fxtHistoricalQuoteDAO")
public class FxtHistoricalQuoteDAOImpl implements FxtHistoricalQuoteDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(FxtHistoricalQuoteDAOImpl.class);

	@Autowired
    @Qualifier("fxExchangeNamedParameter")
    private NamedParameterJdbcTemplate fxExchangeNamedParameter;
	

	@Override
	public Integer selectHistoricalQuote(int idsymbol, Date date) {
        String query = "select count(idsymbol) from fxt_historicalquote where idsymbol=:idsymbol and date=:date";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idsymbol", idsymbol);
        parameters.addValue("date", date);
        return fxExchangeNamedParameter.queryForObject(query, parameters, Integer.class);
	}

	@Override
	public Integer insertHistoricalQuote(HistoricalQuote historicalQuote, int idsymbol) {
		String query = "INSERT INTO fxt_historicalquote (idsymbol,date,open,low,high,close,adjclose,volume) VALUES (:idsymbol,:date,:open,:low,:high,:close,:adjclose,:volume)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idsymbol", idsymbol);
        parameters.addValue("date", historicalQuote.getDate());
        parameters.addValue("open", historicalQuote.getOpen());
        parameters.addValue("low", historicalQuote.getLow());
        parameters.addValue("high", historicalQuote.getHigh());
        parameters.addValue("close", historicalQuote.getClose());
        parameters.addValue("adjclose", historicalQuote.getAdjClose());
        parameters.addValue("volume", historicalQuote.getVolume());
    	return fxExchangeNamedParameter.update(query, parameters);
	}

	

	public NamedParameterJdbcTemplate getFxExchangeNamedParameter() {
		return fxExchangeNamedParameter;
	}

	public void setFxExchangeNamedParameter(NamedParameterJdbcTemplate fxExchangeNamedParameter) {
		this.fxExchangeNamedParameter = fxExchangeNamedParameter;
	}
	
}
