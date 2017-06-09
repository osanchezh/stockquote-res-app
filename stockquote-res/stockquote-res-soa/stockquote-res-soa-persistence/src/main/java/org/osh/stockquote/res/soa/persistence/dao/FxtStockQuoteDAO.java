package org.osh.stockquote.res.soa.persistence.dao;

import yahoofinance.quotes.stock.StockQuote;

public interface FxtStockQuoteDAO {
	  Integer selectIdByIdStock(int idstock);
	  Integer insertStockQuote(StockQuote stockQuote, int idstock);
}
