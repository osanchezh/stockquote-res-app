package org.osh.stockquote.res.soa.persistence.dao;

import java.util.Date;

import yahoofinance.histquotes.HistoricalQuote;

public interface FxtHistoricalQuoteDAO {
  Integer selectHistoricalQuote(int idsymbol,Date date);
  Integer insertHistoricalQuote(HistoricalQuote historicalQuote, int idsymbol);
}
