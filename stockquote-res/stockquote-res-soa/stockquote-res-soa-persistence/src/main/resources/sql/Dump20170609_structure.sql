CREATE DATABASE  IF NOT EXISTS `financialexchangedb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `financialexchangedb`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: financialexchangedb
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `fxc_company`
--

DROP TABLE IF EXISTS `fxc_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fxc_company` (
  `idcompany` int(6) NOT NULL AUTO_INCREMENT,
  `idindustry` int(6) NOT NULL,
  `idsector` int(6) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `creationtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdate` datetime DEFAULT NULL,
  PRIMARY KEY (`idcompany`),
  UNIQUE KEY `idcompany_unique_idx` (`idcompany`),
  KEY `fx_industry_sector_idx` (`idindustry`,`idsector`),
  KEY `index4` (`name`),
  CONSTRAINT `fx_industry_sector` FOREIGN KEY (`idindustry`, `idsector`) REFERENCES `fxc_sector_industry` (`idindustry`, `idsector`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2933 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fxc_industry`
--

DROP TABLE IF EXISTS `fxc_industry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fxc_industry` (
  `idindustry` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(75) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `creationtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdate` datetime DEFAULT NULL,
  PRIMARY KEY (`idindustry`),
  UNIQUE KEY `idIndustry_UNIQUE` (`idindustry`),
  KEY `name_idx` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `financialexchangedb`.`fx_industry_BEFORE_UPDATE` BEFORE UPDATE ON `fxc_industry` FOR EACH ROW
BEGIN
 set new.lastupdate=now();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `fxc_sector`
--

DROP TABLE IF EXISTS `fxc_sector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fxc_sector` (
  `idsector` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(75) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `creationtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdate` datetime DEFAULT NULL,
  PRIMARY KEY (`idsector`),
  UNIQUE KEY `idsector_sector_idx` (`idsector`),
  KEY `name_sector_idx` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `financialexchangedb`.`fx_sector_BEFORE_UPDATE` BEFORE UPDATE ON `fxc_sector` FOR EACH ROW
BEGIN
 set new.lastupdate=now();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `fxc_sector_industry`
--

DROP TABLE IF EXISTS `fxc_sector_industry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fxc_sector_industry` (
  `idindustry` int(6) NOT NULL,
  `idsector` int(6) NOT NULL,
  PRIMARY KEY (`idindustry`,`idsector`),
  UNIQUE KEY `idindustry_UNIQUE` (`idindustry`,`idsector`),
  KEY `fk_sector_industry_idx` (`idindustry`),
  KEY `fk_industry_sector_idx` (`idsector`),
  CONSTRAINT `fk_industry_sector` FOREIGN KEY (`idsector`) REFERENCES `fxc_sector` (`idsector`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sector_industry` FOREIGN KEY (`idindustry`) REFERENCES `fxc_industry` (`idindustry`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fxc_stockexchange`
--

DROP TABLE IF EXISTS `fxc_stockexchange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fxc_stockexchange` (
  `idstockexchange` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  `url` varchar(125) DEFAULT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `creationtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdate` datetime DEFAULT NULL,
  PRIMARY KEY (`idstockexchange`),
  UNIQUE KEY `idstockexchange_UNIQUE` (`idstockexchange`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fxc_symbol`
--

DROP TABLE IF EXISTS `fxc_symbol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fxc_symbol` (
  `idsymbol` int(6) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) NOT NULL,
  `idcompany` int(6) NOT NULL,
  `idstockexchange` int(6) NOT NULL,
  `summaryquote` varchar(150) DEFAULT NULL,
  `marketcap` varchar(15) DEFAULT NULL,
  `ipoyear` varchar(4) DEFAULT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `creationtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdate` datetime DEFAULT NULL,
  PRIMARY KEY (`idsymbol`),
  UNIQUE KEY `idsymbol_UNIQUE` (`idsymbol`),
  KEY `fxcsymbol_idcompany_idx` (`idcompany`),
  KEY `fxcsymbol_idstockexchange` (`idstockexchange`),
  CONSTRAINT `fk_symbol_stockexchange` FOREIGN KEY (`idstockexchange`) REFERENCES `fxc_stockexchange` (`idstockexchange`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fx_symbol_company` FOREIGN KEY (`idcompany`) REFERENCES `fxc_company` (`idcompany`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3221 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fxt_historicalquote`
--

DROP TABLE IF EXISTS `fxt_historicalquote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fxt_historicalquote` (
  `idstock` int(6) NOT NULL,
  `date` date NOT NULL,
  `open` decimal(16,6) DEFAULT NULL,
  `low` decimal(16,6) DEFAULT NULL,
  `high` decimal(16,6) DEFAULT NULL,
  `close` decimal(16,6) DEFAULT NULL,
  `adjclose` decimal(16,6) DEFAULT NULL,
  `volume` decimal(18,0) DEFAULT NULL,
  PRIMARY KEY (`idstock`,`date`),
  UNIQUE KEY `idsymbol_UNIQUE` (`idstock`,`date`),
  KEY `idsymboldate_idx` (`idstock`,`date`),
  CONSTRAINT `fk_fxt_historicalquote_idstock` FOREIGN KEY (`idstock`) REFERENCES `fxt_stock` (`idstock`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fxt_stock`
--

DROP TABLE IF EXISTS `fxt_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fxt_stock` (
  `idstock` int(11) NOT NULL AUTO_INCREMENT,
  `idsymbol` int(6) NOT NULL,
  `currency` varchar(3) DEFAULT NULL,
  `stockExchange` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`idstock`),
  UNIQUE KEY `idstock_UNIQUE` (`idstock`),
  KEY `fk_fxt_stock_idsymbol_idx` (`idsymbol`),
  CONSTRAINT `fk_fxt_stock_idsymbol` FOREIGN KEY (`idsymbol`) REFERENCES `fxc_symbol` (`idsymbol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3156 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fxt_stockdivided`
--

DROP TABLE IF EXISTS `fxt_stockdivided`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fxt_stockdivided` (
  `idstock` int(6) NOT NULL,
  `paydate` datetime DEFAULT NULL,
  `exdate` datetime DEFAULT NULL,
  `annualyield` decimal(16,6) DEFAULT NULL,
  `annualyieldpercent` decimal(16,6) DEFAULT NULL,
  PRIMARY KEY (`idstock`),
  UNIQUE KEY `idstock_UNIQUE` (`idstock`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fxt_stockquote`
--

DROP TABLE IF EXISTS `fxt_stockquote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fxt_stockquote` (
  `idstockquote` int(8) NOT NULL AUTO_INCREMENT,
  `idstock` int(6) NOT NULL,
  `timezone` varchar(100) DEFAULT NULL,
  `ask` decimal(18,6) DEFAULT NULL,
  `asksize` decimal(18,0) DEFAULT NULL,
  `bid` decimal(18,6) DEFAULT NULL,
  `bidsize` decimal(18,0) DEFAULT NULL,
  `price` decimal(16,6) DEFAULT NULL,
  `lasttradesize` decimal(18,0) DEFAULT NULL,
  `lasttradedatestr` varchar(100) DEFAULT NULL,
  `lasttradetimestr` varchar(100) DEFAULT NULL,
  `lasttradetime` datetime DEFAULT NULL,
  `open` decimal(18,6) DEFAULT NULL,
  `previousclose` decimal(18,6) DEFAULT NULL,
  `daylow` decimal(16,6) DEFAULT NULL,
  `dayhigh` decimal(18,6) DEFAULT NULL,
  `yearlow` decimal(18,6) DEFAULT NULL,
  `yearhigh` decimal(18,6) DEFAULT NULL,
  `priceavg50` decimal(18,6) DEFAULT NULL,
  `priceavg200` decimal(18,6) DEFAULT NULL,
  `volume` decimal(18,0) DEFAULT NULL,
  `avgvolume` decimal(18,0) DEFAULT NULL,
  `creationtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idstockquote`),
  UNIQUE KEY `idstockquote_UNIQUE` (`idstockquote`)
) ENGINE=InnoDB AUTO_INCREMENT=3156 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fxt_stockstats`
--

DROP TABLE IF EXISTS `fxt_stockstats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fxt_stockstats` (
  `idstockstats` int(8) NOT NULL AUTO_INCREMENT,
  `idstock` int(6) NOT NULL,
  `marketcap` decimal(18,6) DEFAULT NULL,
  `sharesfloat` decimal(18,0) DEFAULT NULL,
  `sharesoutstanding` decimal(18,0) DEFAULT NULL,
  `sharesowned` decimal(18,0) DEFAULT NULL,
  `eps` decimal(18,6) DEFAULT NULL,
  `pe` decimal(18,6) DEFAULT NULL,
  `peg` decimal(18,6) DEFAULT NULL,
  `epsestimatecurrentyear` decimal(18,6) DEFAULT NULL,
  `epsestimatenextquarter` decimal(18,6) DEFAULT NULL,
  `epsestimatenextyear` decimal(18,6) DEFAULT NULL,
  `pricebook` decimal(18,6) DEFAULT NULL,
  `pricesales` decimal(18,6) DEFAULT NULL,
  `bookvaluepershare` decimal(18,6) DEFAULT NULL,
  `revenue` decimal(18,6) DEFAULT NULL,
  `ebitda` decimal(18,6) DEFAULT NULL,
  `oneyeartargetprice` decimal(18,6) DEFAULT NULL,
  `shortratio` decimal(18,6) DEFAULT NULL,
  `creationtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idstockstats`),
  UNIQUE KEY `idstockstats_UNIQUE` (`idstockstats`),
  KEY `fk_fxt_stockstats_idstock` (`idstock`)
) ENGINE=InnoDB AUTO_INCREMENT=3156 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'financialexchangedb'
--

--
-- Dumping routines for database 'financialexchangedb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-09 20:31:20
