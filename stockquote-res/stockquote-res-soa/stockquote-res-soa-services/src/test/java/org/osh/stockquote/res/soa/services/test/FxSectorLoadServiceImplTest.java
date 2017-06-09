package org.osh.stockquote.res.soa.services.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.osh.stockquote.res.soa.persistence.dao.FxSectorDAO;
import org.osh.stockquote.res.soa.services.FxSectorLoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"file:src/main/resources/spring/stockquote-services/stockquote-services-appctx.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class FxSectorLoadServiceImplTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(FxSectorLoadServiceImplTest.class);
	
	@Autowired
	@Qualifier("fxSectorLoadService")
	private FxSectorLoadService fxSectorLoadService;
	
	public void test(){
		//fxSectorLoadService.loadIndustry();
		//fxSectorLoadService.loadIndustrySector();
		//fxSectorLoadService.loadCompany();
		String url="C:\\code\\personal\\stockquote-res-app\\stockquote-res\\stockquote-res-soa\\stockquote-res-soa-services\\src\\main\\resources\\csv\\companylist_nasdaq.csv";
		String url3="C:\\code\\personal\\stockquote-res-app\\stockquote-res\\stockquote-res-soa\\stockquote-res-soa-services\\src\\main\\resources\\csv\\companylist_nyse.csv";

		//fxSectorLoadService.loadSymbol(url,400);
	}
	@Test
	public void loadNasdaq(){
		String urlwindows="C:\\code\\personal\\stockquote-res-app\\stockquote-res\\stockquote-res-soa\\stockquote-res-soa-services\\src\\main\\resources\\csv\\companylist_nasdaq.csv";
		String urllinux="/home/osh/code/java/doctorado/stockquote/stockquote-res-app/stockquote-res/stockquote-res-soa/stockquote-res-soa-services/src/main/resources/csv/companylist_nasdaq.csv";

		//fxSectorLoadService.loadSector(urllinux);
		//fxSectorLoadService.loadIndustry(urllinux);
		//fxSectorLoadService.loadIndustrySector(urllinux);
		//fxSectorLoadService.loadCompany(urllinux);
		//fxSectorLoadService.loadSymbol(urllinux, 1);
		
		//fxSectorLoadService.loadStock(urllinux, 1);
		//fxSectorLoadService.loadHistoryQuote(1);
		//fxSectorLoadService.loadStockStats(1);
		fxSectorLoadService.loadStockQuote(1);


	}
	
	public void loadAmex(){
		String url2="C:\\code\\personal\\stockquote-res-app\\stockquote-res\\stockquote-res-soa\\stockquote-res-soa-services\\src\\main\\resources\\csv\\companylist_amex.csv";
		//fxSectorLoadService.loadSector(url2);
		//fxSectorLoadService.loadIndustry(url2);
		//fxSectorLoadService.loadIndustrySector(url2);
		//fxSectorLoadService.loadCompany(url2);
		fxSectorLoadService.loadSymbol(url2, 401);
	}
	
	//
	public void loadNyse(){
		String url2="C:\\code\\personal\\stockquote-res-app\\stockquote-res\\stockquote-res-soa\\stockquote-res-soa-services\\src\\main\\resources\\csv\\companylist_nyse.csv";
		//fxSectorLoadService.loadSector(url2);
		//fxSectorLoadService.loadIndustry(url2);
		//fxSectorLoadService.loadIndustrySector(url2);
		//fxSectorLoadService.loadCompany(url2);
		fxSectorLoadService.loadSymbol(url2, 402);
	}
	//@Test
	public void loadHistory(){
		fxSectorLoadService.loadHistoryQuote(400);
	}

	public FxSectorLoadService getFxSectorLoadService() {
		return fxSectorLoadService;
	}


	public void setFxSectorLoadService(FxSectorLoadService fxSectorLoadService) {
		this.fxSectorLoadService = fxSectorLoadService;
	}

}
