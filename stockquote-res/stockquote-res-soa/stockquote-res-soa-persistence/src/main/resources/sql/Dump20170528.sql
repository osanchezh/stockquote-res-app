CREATE DATABASE  IF NOT EXISTS `financialexchangedb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `financialexchangedb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: financialexchangedb
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Table structure for table `FX_INDUSTRY`
--

DROP TABLE IF EXISTS `FX_INDUSTRY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FX_INDUSTRY` (
  `idIndustry` int(11) NOT NULL,
  `idsector` int(11) DEFAULT NULL,
  `name` varchar(75) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `creationtime` datetime NOT NULL,
  `lastupdate` datetime DEFAULT NULL,
  PRIMARY KEY (`idIndustry`),
  UNIQUE KEY `idIndustry_UNIQUE` (`idIndustry`),
  UNIQUE KEY `name_industry_idx` (`name`),
  KEY `fk_fx_ind_sec_idx` (`idsector`),
  CONSTRAINT `fk_FX_INDUSTRY_1` FOREIGN KEY (`idsector`) REFERENCES `FX_SECTOR` (`idsector`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FX_INDUSTRY`
--

LOCK TABLES `FX_INDUSTRY` WRITE;
/*!40000 ALTER TABLE `FX_INDUSTRY` DISABLE KEYS */;
/*!40000 ALTER TABLE `FX_INDUSTRY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FX_SECTOR`
--

DROP TABLE IF EXISTS `FX_SECTOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FX_SECTOR` (
  `idsector` int(5) NOT NULL DEFAULT '1000',
  `name` varchar(75) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `creationtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdate` datetime DEFAULT NULL,
  PRIMARY KEY (`idsector`),
  UNIQUE KEY `idsector_sector_idx` (`idsector`),
  KEY `name_sector_idx` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FX_SECTOR`
--

LOCK TABLES `FX_SECTOR` WRITE;
/*!40000 ALTER TABLE `FX_SECTOR` DISABLE KEYS */;
INSERT INTO `FX_SECTOR` VALUES (1000,'PIH','PIH','','2017-05-28 01:11:09',NULL);
/*!40000 ALTER TABLE `FX_SECTOR` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`stock`@`localhost`*/ /*!50003 TRIGGER `financialexchangedb`.`FX_SECTOR_BEFORE_UPDATE` BEFORE UPDATE ON `FX_SECTOR` FOR EACH ROW
BEGIN
 set new.lastupdate=now();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `FX_STOCK`
--

DROP TABLE IF EXISTS `FX_STOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FX_STOCK` (
  `idstock` int(11) NOT NULL,
  `symbol` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `stockexchange` varchar(45) DEFAULT NULL,
  `active` tinyint(4) DEFAULT '1',
  `creationtime` datetime DEFAULT NULL,
  `lastupdate` datetime DEFAULT NULL,
  PRIMARY KEY (`idstock`),
  UNIQUE KEY `id_unq_idx` (`idstock`),
  KEY `symbol_idx` (`symbol`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FX_STOCK`
--

LOCK TABLES `FX_STOCK` WRITE;
/*!40000 ALTER TABLE `FX_STOCK` DISABLE KEYS */;
/*!40000 ALTER TABLE `FX_STOCK` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-28 21:41:34
