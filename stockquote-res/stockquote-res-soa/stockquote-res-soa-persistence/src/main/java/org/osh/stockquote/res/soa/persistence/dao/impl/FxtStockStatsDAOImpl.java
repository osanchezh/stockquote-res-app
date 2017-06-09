package org.osh.stockquote.res.soa.persistence.dao.impl;

import org.osh.stockquote.res.soa.persistence.dao.FxtStockStatsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import yahoofinance.quotes.stock.StockStats;

@Repository("fxtStockStatsDAO")
public class FxtStockStatsDAOImpl implements FxtStockStatsDAO {

	@Autowired
    @Qualifier("fxExchangeNamedParameter")
    private NamedParameterJdbcTemplate fxExchangeNamedParameter;
	
	
	@Override
	public Integer selectIdByIdStock(int idstock) {
		String query = "select idstockstats from financialexchangedb.fxt_stockstats where idstock=:idstock";
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
	public Integer insertStockStats(StockStats stockStats, int idstock) {
		String query = "INSERT INTO financialexchangedb.fxt_stockstats(idstock,marketcap,sharesfloat,sharesoutstanding,sharesowned,eps,pe,peg,epsestimatecurrentyear,epsestimatenextquarter,epsestimatenextyear,pricebook,pricesales,bookvaluepershare,revenue,ebitda,oneyeartargetprice,shortratio) "
				+ "VALUES (:idstock,:marketcap,:sharesfloat,:sharesoutstanding,:sharesowned,:eps,:pe,:peg,:epsestimatecurrentyear,:epsestimatenextquarter,:epsestimatenextyear,:pricebook,:pricesales,:bookvaluepershare,:revenue,:ebitda,:oneyeartargetprice,:shortratio);";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idstock", idstock);
        parameters.addValue("marketcap", stockStats.getMarketCap());
        parameters.addValue("sharesfloat", stockStats.getSharesFloat());
        parameters.addValue("sharesoutstanding", stockStats.getSharesOutstanding());
        parameters.addValue("sharesowned", stockStats.getSharesOwned());
        parameters.addValue("eps", stockStats.getEps());
        parameters.addValue("pe", stockStats.getPe());
        parameters.addValue("peg", stockStats.getPeg());
        parameters.addValue("epsestimatecurrentyear", stockStats.getEpsEstimateCurrentYear());
        parameters.addValue("epsestimatenextquarter", stockStats.getEpsEstimateNextQuarter());
        parameters.addValue("epsestimatenextyear", stockStats.getEpsEstimateNextYear());
        parameters.addValue("pricebook", stockStats.getPriceBook());
        parameters.addValue("pricesales", stockStats.getPriceSales());
        parameters.addValue("bookvaluepershare", stockStats.getBookValuePerShare());
        parameters.addValue("revenue", stockStats.getRevenue());
        parameters.addValue("ebitda", stockStats.getEBITDA());
        parameters.addValue("oneyeartargetprice", stockStats.getOneYearTargetPrice());
        parameters.addValue("shortratio", stockStats.getShortRatio());

    	return fxExchangeNamedParameter.update(query, parameters);
	}	

	public NamedParameterJdbcTemplate getFxExchangeNamedParameter() {
		return fxExchangeNamedParameter;
	}

	public void setFxExchangeNamedParameter(NamedParameterJdbcTemplate fxExchangeNamedParameter) {
		this.fxExchangeNamedParameter = fxExchangeNamedParameter;
	}





}
