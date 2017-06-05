package org.osh.stockquote.res.soa.persistence.dao;

public interface FxtStockDAO {
  Integer selectIdStockByIdSymbol(int idsymbol);
  Integer insertStock(int idsymbol, String currency, String stockexchange);
}
