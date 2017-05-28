package org.osh.stockquote.res.soa.services.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.osh.stockquote.res.soa.model.csv.CompanyListCSV;
import org.osh.stockquote.res.soa.services.CompanyListCsvParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

public class CompanyListCsvParserServiceImpl implements CompanyListCsvParserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyListCsvParserServiceImpl.class);

	public List<CompanyListCSV> parseCSVtoBean(String filename) {

		List<CompanyListCSV> productList = new ArrayList<CompanyListCSV>(0);

		try {
			// To ignore Processing of 1st row
			CSVReader reader = new CSVReader(new FileReader(filename), ',', '\"', 1);

			ColumnPositionMappingStrategy<CompanyListCSV> mappingStrategy = new ColumnPositionMappingStrategy<CompanyListCSV>();
			mappingStrategy.setType(CompanyListCSV.class);

			// the fields to bind do in your JavaBean
			String[] columns = new String[] { "symbol", "name", "lastSale", "marketCap", "ipoYear", "sector",
					"industry", "summaryQuote" };
			mappingStrategy.setColumnMapping(columns);

			CsvToBean<CompanyListCSV> csv = new CsvToBean<CompanyListCSV>();

			productList.addAll(csv.parse(mappingStrategy, reader));
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return productList;
	}

}
