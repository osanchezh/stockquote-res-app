package org.osh.stockquote.res.soa.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.osh.stockquote.res.soa.model.domain.FxcSymbol;
import org.springframework.jdbc.core.RowMapper;

public class FxcSymbolRowMapper  implements RowMapper<FxcSymbol> {

	@Override
	public FxcSymbol mapRow(ResultSet rs, int rowNum) throws SQLException {
		FxcSymbol fxcSymbol = new FxcSymbol();
		fxcSymbol.setIdSymbol(rs.getInt("idsymbol"));
		fxcSymbol.setSymbol(rs.getString("symbol"));
		return fxcSymbol;
	}

}
