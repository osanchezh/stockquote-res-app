package org.osh.stockquote.res.soa.persistence.dao.impl;

import org.osh.stockquote.res.soa.persistence.dao.FxtStockQuoteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import yahoofinance.quotes.stock.StockQuote;

@Repository("fxtStockQuoteDAO")
public class FxtStockQuoteDAOImpl implements FxtStockQuoteDAO{

	@Autowired
    @Qualifier("fxExchangeNamedParameter")
    private NamedParameterJdbcTemplate fxExchangeNamedParameter;
	

	@Override
	public Integer selectIdByIdStock(int idstock) {
		String query = "select idstockquote from financialexchangedb.fxt_stockquote where idstock=:idstock";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idstock", idstock);
        Integer resultado=null;;
        try{
          resultado = fxExchangeNamedParameter.queryForObject(query, parameters, Integer.class);
        }catch(EmptyResultDataAccessException exception){
        	resultado =null;
        }
        return resultado;
	}

	@Override
	public Integer insertStockQuote(StockQuote stockQuote, int idstock) {
		String query = "INSERT INTO financialexchangedb.fxt_stockquote(idstock,timezone,ask,asksize,bid,bidsize,price,lasttradesize,lasttradedatestr,lasttradetimestr,lasttradetime,open,previousclose,daylow,dayhigh,yearlow,yearhigh,priceavg50,priceavg200,volume,avgvolume)"
				+ "VALUES(:idstock,:timezone,:ask,:asksize,:bid,:bidsize,:price,:lasttradesize,:lasttradedatestr,"
				+ ":lasttradetimestr,:lasttradetime,:open,:previousclose,:daylow,:dayhigh,:yearlow,:yearhigh,:priceavg50,:priceavg200,:volume,:avgvolume)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idstock", idstock);
        parameters.addValue("timezone", stockQuote.getTimeZone().getID());
        parameters.addValue("ask", stockQuote.getAsk());
        parameters.addValue("asksize", stockQuote.getAskSize());
        parameters.addValue("bid", stockQuote.getBid());
        parameters.addValue("bidsize", stockQuote.getBidSize());
        parameters.addValue("price", stockQuote.getPrice());
        parameters.addValue("lasttradesize", stockQuote.getLastTradeSize());
        parameters.addValue("lasttradedatestr", stockQuote.getLastTradeDateStr());
        parameters.addValue("lasttradetimestr", stockQuote.getLastTradeTimeStr());
        parameters.addValue("lasttradetime", stockQuote.getLastTradeTime());
        parameters.addValue("open", stockQuote.getOpen());
        parameters.addValue("previousclose", stockQuote.getPreviousClose());
        parameters.addValue("daylow", stockQuote.getDayLow());
        parameters.addValue("dayhigh", stockQuote.getDayHigh());
        parameters.addValue("yearlow", stockQuote.getYearLow());
        parameters.addValue("yearhigh", stockQuote.getYearHigh());
        parameters.addValue("priceavg50", stockQuote.getPriceAvg50());
        parameters.addValue("priceavg200", stockQuote.getPriceAvg200());
        parameters.addValue("volume", stockQuote.getVolume());
        parameters.addValue("avgvolume", stockQuote.getAvgVolume());
    	return fxExchangeNamedParameter.update(query, parameters);
	}
	

	public NamedParameterJdbcTemplate getFxExchangeNamedParameter() {
		return fxExchangeNamedParameter;
	}

	public void setFxExchangeNamedParameter(NamedParameterJdbcTemplate fxExchangeNamedParameter) {
		this.fxExchangeNamedParameter = fxExchangeNamedParameter;
	}

}
