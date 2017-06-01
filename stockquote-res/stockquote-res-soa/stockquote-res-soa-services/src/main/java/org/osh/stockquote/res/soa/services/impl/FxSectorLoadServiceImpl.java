package org.osh.stockquote.res.soa.services.impl;

import java.util.List;

import org.osh.stockquote.res.soa.model.csv.CompanyListCSV;
import org.osh.stockquote.res.soa.persistence.csv.CompanyListCsvParser;
import org.osh.stockquote.res.soa.persistence.dao.FxIndustryDAO;
import org.osh.stockquote.res.soa.persistence.dao.FxSectorDAO;
import org.osh.stockquote.res.soa.persistence.dao.FxcCompanyDAO;
import org.osh.stockquote.res.soa.persistence.dao.FxcSectorIndustryDAO;
import org.osh.stockquote.res.soa.persistence.dao.FxcSymbolDAO;
import org.osh.stockquote.res.soa.services.FxSectorLoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("fxSectorLoadService")
public class FxSectorLoadServiceImpl implements FxSectorLoadService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FxSectorLoadServiceImpl.class);

	@Autowired
	@Qualifier("fxSectorDAO")
	private FxSectorDAO fxSectorDAO;

	@Autowired
	@Qualifier("fxIndustryDAO")
	private FxIndustryDAO fxIndustryDAO;
	
	@Autowired
	@Qualifier("fxcSectorIndustryDAO")
	private FxcSectorIndustryDAO fxcSectorIndustryDAO;

	@Autowired
	@Qualifier("fxcCompanyDAO")
	private FxcCompanyDAO fxcCompanyDAO;
	@Autowired
	@Qualifier("fxcSymbolDAO")
	private FxcSymbolDAO fxcSymbolDAO;
	@Autowired
	@Qualifier("companyListCsvParser")
	private CompanyListCsvParser companyListCsvParser;

	public void load() {
		List<CompanyListCSV> lstCompanyListCSV= companyListCsvParser.parseCSVtoBean("C:\\code\\personal\\stockquote-res-app\\stockquote-res\\stockquote-res-soa\\stockquote-res-soa-services\\src\\main\\resources\\csv\\companylist_nasdaq.csv");
		LOGGER.debug("size="+lstCompanyListCSV.size());
		for(CompanyListCSV companyListCSV:lstCompanyListCSV){
			Integer salida = fxSectorDAO.selectSectorByName(companyListCSV.getSector());
			LOGGER.debug("SECTOR="+companyListCSV.getSector()+",FIND="+salida);
			if(salida==null){
				fxSectorDAO.insertSector(companyListCSV.getSector(), companyListCSV.getSector());
			}
		}
	}
	
	public void loadIndustry(){
		List<CompanyListCSV> lstCompanyListCSV= companyListCsvParser.parseCSVtoBean("C:\\code\\personal\\stockquote-res-app\\stockquote-res\\stockquote-res-soa\\stockquote-res-soa-services\\src\\main\\resources\\csv\\companylist_nasdaq.csv");
		for(CompanyListCSV companyListCSV:lstCompanyListCSV){
			//Integer idSector =fxSectorDAO.selectSectorByName(companyListCSV.getSector());
			//if( idSector !=null){
				Integer countInd = fxIndustryDAO.selectCountIndustryByName(companyListCSV.getIndustry());
				LOGGER.debug("countInd="+countInd);
				if(countInd==0){
					fxIndustryDAO.insertIndustry(companyListCSV.getIndustry(), companyListCSV.getIndustry());
					LOGGER.debug("INSERTANDO,companyListCSV.getIndustry()="+companyListCSV.getIndustry());
				}
			//}
		}  
	}

	public void loadIndustrySector(){
		List<CompanyListCSV> lstCompanyListCSV= companyListCsvParser.parseCSVtoBean("C:\\code\\personal\\stockquote-res-app\\stockquote-res\\stockquote-res-soa\\stockquote-res-soa-services\\src\\main\\resources\\csv\\companylist_nasdaq.csv");
		for(CompanyListCSV companyListCSV:lstCompanyListCSV){
			Integer idindustry = fxIndustryDAO.selectIndustryByName(companyListCSV.getIndustry());
			if(idindustry!=null){
				List<Integer> lstSector =fxcSectorIndustryDAO.selectSector(idindustry);
				Integer idsector = fxSectorDAO.selectSectorByName(companyListCSV.getSector());
				if(lstSector.isEmpty()){
					fxcSectorIndustryDAO.insertIndustrySector(idindustry, idsector);
				}else{
					if(!lstSector.contains(idsector)){
						fxcSectorIndustryDAO.insertIndustrySector(idindustry, idsector);
					}
				}
			}
		}  
	}
	
	public void loadCompany(){
		List<CompanyListCSV> lstCompanyListCSV= companyListCsvParser.parseCSVtoBean("C:\\code\\personal\\stockquote-res-app\\stockquote-res\\stockquote-res-soa\\stockquote-res-soa-services\\src\\main\\resources\\csv\\companylist_nasdaq.csv");
		for(CompanyListCSV companyListCSV:lstCompanyListCSV){
			Integer idindustry = fxIndustryDAO.selectIndustryByName(companyListCSV.getIndustry());
			Integer idsector = fxSectorDAO.selectSectorByName(companyListCSV.getSector());
			Integer idCompany = fxcCompanyDAO.selectCompanyByName(companyListCSV.getName(),idindustry,idsector);
			if(idCompany==null){
				fxcCompanyDAO.insertCompany(idindustry, idsector, companyListCSV.getName(), companyListCSV.getName(), companyListCSV.getMarketCap(),companyListCSV.getIpoYear());
			}
		}
	}
	
	public void loadSymbol(){
		List<CompanyListCSV> lstCompanyListCSV= companyListCsvParser.parseCSVtoBean("C:\\code\\personal\\stockquote-res-app\\stockquote-res\\stockquote-res-soa\\stockquote-res-soa-services\\src\\main\\resources\\csv\\companylist_nasdaq.csv");
		for(CompanyListCSV companyListCSV:lstCompanyListCSV){
			Integer idindustry = fxIndustryDAO.selectIndustryByName(companyListCSV.getIndustry());
			Integer idsector = fxSectorDAO.selectSectorByName(companyListCSV.getSector());
			Integer idCompany = fxcCompanyDAO.selectCompanyByName(companyListCSV.getName(),idindustry,idsector);
			if(idCompany!=null){
				fxcSymbolDAO.insertSymbol(companyListCSV.getSymbol(), idCompany, 400, companyListCSV.getSummaryQuote());
			}
		}
	}

	public FxcSymbolDAO getFxcSymbolDAO() {
		return fxcSymbolDAO;
	}

	public void setFxcSymbolDAO(FxcSymbolDAO fxcSymbolDAO) {
		this.fxcSymbolDAO = fxcSymbolDAO;
	}

	public FxcCompanyDAO getFxcCompanyDAO() {
		return fxcCompanyDAO;
	}

	public void setFxcCompanyDAO(FxcCompanyDAO fxcCompanyDAO) {
		this.fxcCompanyDAO = fxcCompanyDAO;
	}

	
	public FxIndustryDAO getFxIndustryDAO() {
		return fxIndustryDAO;
	}

	public void setFxIndustryDAO(FxIndustryDAO fxIndustryDAO) {
		this.fxIndustryDAO = fxIndustryDAO;
	}
	
	public FxSectorDAO getFxSectorDAO() {
		return fxSectorDAO;
	}

	public void setFxSectorDAO(FxSectorDAO fxSectorDAO) {
		this.fxSectorDAO = fxSectorDAO;
	}

	public CompanyListCsvParser getCompanyListCsvParser() {
		return companyListCsvParser;
	}

	public void setCompanyListCsvParser(CompanyListCsvParser companyListCsvParser) {
		this.companyListCsvParser = companyListCsvParser;
	}


	public FxcSectorIndustryDAO getFxcSectorIndustryDAO() {
		return fxcSectorIndustryDAO;
	}

	public void setFxcSectorIndustryDAO(FxcSectorIndustryDAO fxcSectorIndustryDAO) {
		this.fxcSectorIndustryDAO = fxcSectorIndustryDAO;
	}
}
