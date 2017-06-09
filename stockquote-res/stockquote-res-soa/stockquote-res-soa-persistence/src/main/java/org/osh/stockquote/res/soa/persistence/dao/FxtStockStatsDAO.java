package org.osh.stockquote.res.soa.persistence.dao;

import yahoofinance.quotes.stock.StockStats;

public interface FxtStockStatsDAO {
  Integer selectIdByIdStock(int idstock);
  Integer insertStockStats(StockStats stockStats, int idstock);
}
