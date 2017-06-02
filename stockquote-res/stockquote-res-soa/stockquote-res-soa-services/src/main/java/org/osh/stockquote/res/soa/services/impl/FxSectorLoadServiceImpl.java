package org.osh.stockquote.res.soa.services.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.osh.stockquote.res.soa.model.csv.CompanyListCSV;
import org.osh.stockquote.res.soa.model.domain.FxcSymbol;
import org.osh.stockquote.res.soa.persistence.csv.CompanyListCsvParser;
import org.osh.stockquote.res.soa.persistence.dao.FxIndustryDAO;
import org.osh.stockquote.res.soa.persistence.dao.FxSectorDAO;
import org.osh.stockquote.res.soa.persistence.dao.FxcCompanyDAO;
import org.osh.stockquote.res.soa.persistence.dao.FxcSectorIndustryDAO;
import org.osh.stockquote.res.soa.persistence.dao.FxcSymbolDAO;
import org.osh.stockquote.res.soa.persistence.dao.FxtHistoricalQuoteDAO;
import org.osh.stockquote.res.soa.services.FxSectorLoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

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
	@Qualifier("fxtHistoricalQuoteDAO")
	private FxtHistoricalQuoteDAO fxtHistoricalQuoteDAO;

	@Autowired
	@Qualifier("companyListCsvParser")
	private CompanyListCsvParser companyListCsvParser;

	public void loadSector(String csv) {
		List<CompanyListCSV> lstCompanyListCSV = companyListCsvParser.parseCSVtoBean(csv);
		LOGGER.debug("size=" + lstCompanyListCSV.size());
		for (CompanyListCSV companyListCSV : lstCompanyListCSV) {
			Integer salida = fxSectorDAO.selectSectorByName(companyListCSV.getSector());
			LOGGER.debug("SECTOR=" + companyListCSV.getSector() + ",FIND=" + salida);
			if (salida == null) {
				fxSectorDAO.insertSector(companyListCSV.getSector(), companyListCSV.getSector());
			}
		}
	}

	public void loadIndustry(String csv) {
		List<CompanyListCSV> lstCompanyListCSV = companyListCsvParser.parseCSVtoBean(csv);
		for (CompanyListCSV companyListCSV : lstCompanyListCSV) {
			// Integer idSector
			// =fxSectorDAO.selectSectorByName(companyListCSV.getSector());
			// if( idSector !=null){
			Integer countInd = fxIndustryDAO.selectCountIndustryByName(companyListCSV.getIndustry());
			LOGGER.debug("countInd=" + countInd);
			if (countInd == 0) {
				fxIndustryDAO.insertIndustry(companyListCSV.getIndustry(), companyListCSV.getIndustry());
				LOGGER.debug("INSERTANDO,companyListCSV.getIndustry()=" + companyListCSV.getIndustry());
			}
			// }
		}
	}

	public void loadIndustrySector(String csv) {
		List<CompanyListCSV> lstCompanyListCSV = companyListCsvParser.parseCSVtoBean(csv);
		for (CompanyListCSV companyListCSV : lstCompanyListCSV) {
			Integer idindustry = fxIndustryDAO.selectIndustryByName(companyListCSV.getIndustry());
			if (idindustry != null) {
				List<Integer> lstSector = fxcSectorIndustryDAO.selectSector(idindustry);
				Integer idsector = fxSectorDAO.selectSectorByName(companyListCSV.getSector());
				if (lstSector.isEmpty()) {
					fxcSectorIndustryDAO.insertIndustrySector(idindustry, idsector);
				} else {
					if (!lstSector.contains(idsector)) {
						fxcSectorIndustryDAO.insertIndustrySector(idindustry, idsector);
					}
				}
			}
		}
	}

	public void loadCompany(String csv) {
		List<CompanyListCSV> lstCompanyListCSV = companyListCsvParser.parseCSVtoBean(csv);
		for (CompanyListCSV companyListCSV : lstCompanyListCSV) {
			Integer idindustry = fxIndustryDAO.selectIndustryByName(companyListCSV.getIndustry());
			Integer idsector = fxSectorDAO.selectSectorByName(companyListCSV.getSector());
			Integer idCompany = fxcCompanyDAO.selectCompanyByName(companyListCSV.getName(), idindustry, idsector);
			if (idCompany == null) {
				fxcCompanyDAO.insertCompany(idindustry, idsector, companyListCSV.getName(), companyListCSV.getName(),
						companyListCSV.getMarketCap(), companyListCSV.getIpoYear());
			}
		}
	}

	public void loadSymbol(String csv, int idstockexchange) {
		List<CompanyListCSV> lstCompanyListCSV = companyListCsvParser.parseCSVtoBean(csv);
		for (CompanyListCSV companyListCSV : lstCompanyListCSV) {
			Integer idindustry = fxIndustryDAO.selectIndustryByName(companyListCSV.getIndustry());
			Integer idsector = fxSectorDAO.selectSectorByName(companyListCSV.getSector());
			Integer idCompany = fxcCompanyDAO.selectCompanyByName(companyListCSV.getName(), idindustry, idsector);
			if (idCompany != null) {
				fxcSymbolDAO.insertSymbol(companyListCSV.getSymbol(), idCompany, idstockexchange,
						companyListCSV.getSummaryQuote());
			}
		}
	}

	public void loadHistoryQuote(int idstockexchange) {
		List<FxcSymbol> lstSymbol = fxcSymbolDAO.selectSymbolByIdStockExchange(idstockexchange);

		Calendar today = Calendar.getInstance();
		today.set(Calendar.YEAR, 2017);
		today.set(Calendar.MONTH, 5);
		today.set(Calendar.DATE, 31);

		Calendar from = (Calendar) today.clone();
		from.add(Calendar.YEAR, -1);
		try {
			LOGGER.debug("symbol.total="+lstSymbol.size());
			
			for (FxcSymbol fxcSymbol : lstSymbol) {
				LOGGER.debug("CHECK.fxcSymbol=" + fxcSymbol.toString());
				try{
				Stock stock = YahooFinance.get(fxcSymbol.getSymbol(), from, today, Interval.DAILY);
				 for(HistoricalQuote histQuote : stock.getHistory()) {
				      Integer guardado=fxtHistoricalQuoteDAO.selectHistoricalQuote(fxcSymbol.getIdSymbol(), histQuote.getDate().getTime());
				      LOGGER.debug("ENCONTRADO="+guardado);
				      if(guardado==0){
				    	  LOGGER.debug("GUARDANDO="+fxcSymbol.getSymbol()+",date="+histQuote.getDate().getTime());
				         fxtHistoricalQuoteDAO.insertHistoricalQuote(histQuote, fxcSymbol.getIdSymbol());
				      }
				 }
				}catch(FileNotFoundException e){
					LOGGER.error(e.getMessage(), e);
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
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

	public FxtHistoricalQuoteDAO getFxtHistoricalQuoteDAO() {
		return fxtHistoricalQuoteDAO;
	}

	public void setFxtHistoricalQuoteDAO(FxtHistoricalQuoteDAO fxtHistoricalQuoteDAO) {
		this.fxtHistoricalQuoteDAO = fxtHistoricalQuoteDAO;
	}
}
