package org.osh.stockquote.res.soa.persistence.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.osh.stockquote.res.soa.persistence.dao.FxSectorDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"file:src/main/resources/spring/stockquote/stockquote-persistence-appctx.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class FxSectorDAOImplTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(FxSectorDAOImplTest.class);
	
	@Autowired
	@Qualifier("fxSectorDAO")
	private FxSectorDAO fxSectorDAO;
	

	@Test
	public void test(){
		LOGGER.debug("TEST="+fxSectorDAO.selectCountSectorByName("PIH"));
	}
	

	public FxSectorDAO getFxSectorDAO() {
		return fxSectorDAO;
	}

	public void setFxSectorDAO(FxSectorDAO fxSectorDAO) {
		this.fxSectorDAO = fxSectorDAO;
	}
}
