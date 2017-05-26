package org.osh.stockquote.res.soa.fxinterpreter.model;

import java.util.ArrayList;
import java.util.List;

public class StockQuotesRequest extends QuotesRequest {
	
    public StockQuotesRequest(String query) {
		super(query, DEFAULT_PROPERTIES);
	}

	public static final List<QuotesProperty> DEFAULT_PROPERTIES = new ArrayList<QuotesProperty>();

    static {

        // Always keep the name and symbol in first and second place respectively!
        DEFAULT_PROPERTIES.add(QuotesProperty.Name);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);

        DEFAULT_PROPERTIES.add(QuotesProperty.Currency);
        DEFAULT_PROPERTIES.add(QuotesProperty.StockExchange);

        DEFAULT_PROPERTIES.add(QuotesProperty.Ask);
        DEFAULT_PROPERTIES.add(QuotesProperty.AskRealtime);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.AskSize);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.Bid);
        DEFAULT_PROPERTIES.add(QuotesProperty.BidRealtime);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.BidSize);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);

        DEFAULT_PROPERTIES.add(QuotesProperty.LastTradePriceOnly);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.LastTradeSize);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.LastTradeDate);
        DEFAULT_PROPERTIES.add(QuotesProperty.LastTradeTime);

        DEFAULT_PROPERTIES.add(QuotesProperty.Open);
        DEFAULT_PROPERTIES.add(QuotesProperty.PreviousClose);
        DEFAULT_PROPERTIES.add(QuotesProperty.DaysLow);
        DEFAULT_PROPERTIES.add(QuotesProperty.DaysHigh);

        DEFAULT_PROPERTIES.add(QuotesProperty.Volume);
        DEFAULT_PROPERTIES.add(QuotesProperty.AverageDailyVolume);

        DEFAULT_PROPERTIES.add(QuotesProperty.YearHigh);
        DEFAULT_PROPERTIES.add(QuotesProperty.YearLow);

        DEFAULT_PROPERTIES.add(QuotesProperty.FiftydayMovingAverage);
        DEFAULT_PROPERTIES.add(QuotesProperty.TwoHundreddayMovingAverage);

        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.SharesOutstanding);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.SharesOwned);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.MarketCapitalization);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.SharesFloat);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);

        DEFAULT_PROPERTIES.add(QuotesProperty.DividendPayDate);
        DEFAULT_PROPERTIES.add(QuotesProperty.ExDividendDate);
        DEFAULT_PROPERTIES.add(QuotesProperty.TrailingAnnualDividendYield);
        DEFAULT_PROPERTIES.add(QuotesProperty.TrailingAnnualDividendYieldInPercent);

        DEFAULT_PROPERTIES.add(QuotesProperty.DilutedEPS);
        DEFAULT_PROPERTIES.add(QuotesProperty.EPSEstimateCurrentYear);
        DEFAULT_PROPERTIES.add(QuotesProperty.EPSEstimateNextQuarter);
        DEFAULT_PROPERTIES.add(QuotesProperty.EPSEstimateNextYear);
        DEFAULT_PROPERTIES.add(QuotesProperty.PERatio);
        DEFAULT_PROPERTIES.add(QuotesProperty.PEGRatio);

        DEFAULT_PROPERTIES.add(QuotesProperty.PriceBook);
        DEFAULT_PROPERTIES.add(QuotesProperty.PriceSales);
        DEFAULT_PROPERTIES.add(QuotesProperty.BookValuePerShare);

        DEFAULT_PROPERTIES.add(QuotesProperty.Revenue);
        DEFAULT_PROPERTIES.add(QuotesProperty.EBITDA);
        DEFAULT_PROPERTIES.add(QuotesProperty.OneyrTargetPrice);
        
        DEFAULT_PROPERTIES.add(QuotesProperty.ShortRatio);
    }
    
    public String getFieldsString() {
        StringBuilder result = new StringBuilder();
        for (QuotesProperty property : StockQuotesRequest.DEFAULT_PROPERTIES) {
            result.append(property.getTag());
        }
        return result.toString();
    }
}
