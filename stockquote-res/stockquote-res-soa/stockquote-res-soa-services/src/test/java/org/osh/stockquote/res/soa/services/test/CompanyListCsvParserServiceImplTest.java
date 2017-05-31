package org.osh.stockquote.res.soa.services.test;

import org.junit.Test;
import org.osh.stockquote.res.soa.persistence.csv.impl.CompanyListCsvParserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyListCsvParserServiceImplTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyListCsvParserImpl.class);

	@Test
	public void init(){
		LOGGER.debug("test");
		
		CompanyListCsvParserImpl service = new CompanyListCsvParserImpl();
		service.parseCSVtoBean("/home/osh/code/java/doctorado/stockquote/stockquote-res-app/stockquote-res/stockquote-res-soa/stockquote-res-soa-services/src/main/resources/csv/companylist_nasdaq.csv");
	}
}
