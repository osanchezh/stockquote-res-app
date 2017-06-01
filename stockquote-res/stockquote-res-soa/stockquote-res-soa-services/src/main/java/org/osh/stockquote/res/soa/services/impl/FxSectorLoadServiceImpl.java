package org.osh.stockquote.res.soa.services.impl;

import java.util.List;

import org.osh.stockquote.res.soa.model.csv.CompanyListCSV;
import org.osh.stockquote.res.soa.persistence.csv.CompanyListCsvParser;
import org.osh.stockquote.res.soa.persistence.dao.FxIndustryDAO;
import org.osh.stockquote.res.soa.persistence.dao.FxSectorDAO;
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
			Integer idSector =fxSectorDAO.selectSectorByName(companyListCSV.getSector());
			if( idSector !=null){
				Integer countInd = fxIndustryDAO.selectCountIndustryByName(companyListCSV.getIndustry(), idSector);
				LOGGER.debug("countInd="+countInd);
				if(countInd==0){
					fxIndustryDAO.insertCambStageConfirmacion(idSector, companyListCSV.getIndustry(), companyListCSV.getIndustry());
					LOGGER.debug("INSERTANDO=idSector="+idSector+",companyListCSV.getIndustry()="+companyListCSV.getIndustry());
				}
			}
		}  
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

}
