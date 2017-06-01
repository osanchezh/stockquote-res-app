package org.osh.stockquote.res.soa.persistence.dao;

import java.util.List;

public interface FxcSectorIndustryDAO {
   List<Integer> selectSector(int idindustry);
   int insertIndustrySector(int idindustry, int idsector) ;
}
