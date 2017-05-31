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
	

	@Test
	public void test(){
		fxSectorLoadService.load();
	}
	

	public FxSectorLoadService getFxSectorLoadService() {
		return fxSectorLoadService;
	}


	public void setFxSectorLoadService(FxSectorLoadService fxSectorLoadService) {
		this.fxSectorLoadService = fxSectorLoadService;
	}

}
