package org.osh.stockquote.res.soa.persistence.dao.impl;

import org.osh.stockquote.res.soa.persistence.dao.FxtStockStatsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("fxtStockStatsDAO")
public class FxtStockStatsDAOImpl implements FxtStockStatsDAO {

	@Autowired
    @Qualifier("fxExchangeNamedParameter")
    private NamedParameterJdbcTemplate fxExchangeNamedParameter;
	
	@Override
	public Integer selectIdByIdStock(int idstock) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public NamedParameterJdbcTemplate getFxExchangeNamedParameter() {
		return fxExchangeNamedParameter;
	}

	public void setFxExchangeNamedParameter(NamedParameterJdbcTemplate fxExchangeNamedParameter) {
		this.fxExchangeNamedParameter = fxExchangeNamedParameter;
	}


}
