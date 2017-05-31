package org.osh.stockquote.res.soa.persistence.csv;

import java.util.List;

import org.osh.stockquote.res.soa.model.csv.CompanyListCSV;

public interface CompanyListCsvParser {
	List<CompanyListCSV> parseCSVtoBean(String filename);
}
