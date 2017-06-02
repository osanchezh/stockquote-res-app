package org.osh.stockquote.res.soa.persistence.dao;

import java.util.List;

import org.osh.stockquote.res.soa.model.domain.FxcSymbol;

public interface FxcSymbolDAO {
  List<FxcSymbol> selectSymbolByIdStockExchange(int idstockexchange);
  Integer insertSymbol(String symbol, int idcompany, int idstockexchange,String summaryquote);
}
