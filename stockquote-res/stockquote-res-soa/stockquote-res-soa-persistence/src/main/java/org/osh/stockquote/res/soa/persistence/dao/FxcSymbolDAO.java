package org.osh.stockquote.res.soa.persistence.dao;

public interface FxcSymbolDAO {
  Integer insertSymbol(String symbol, int idcompany, int idstockexchange,String summaryquote);
}
