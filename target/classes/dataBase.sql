-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: poll
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `ballance`
--

DROP TABLE IF EXISTS `ballance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ballance` (
  `BALLANCEID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Ballance ID',
  `CUSTVENDID` int(11) NOT NULL COMMENT 'Customer-Vendor Code',
  `TRANSACTIONDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Transaction Date',
  `AMOUNT` float NOT NULL COMMENT 'Transaction Amount',
  `INSDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `SYSUSER` smallint(6) DEFAULT NULL,
  `UPDDATE` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`BALLANCEID`),
  KEY `ballance_ibfk_1` (`CUSTVENDID`),
  CONSTRAINT `ballance_ibfk_1` FOREIGN KEY (`CUSTVENDID`) REFERENCES `custvend` (`CUSTVENDID`)
) ENGINE=InnoDB AUTO_INCREMENT=382 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ballance`
--

LOCK TABLES `ballance` WRITE;
/*!40000 ALTER TABLE `ballance` DISABLE KEYS */;
INSERT INTO `ballance` VALUES (358,74,'2018-05-17 22:28:53',2.48,'2018-05-17 22:28:53',NULL,NULL),(359,74,'2018-05-17 22:28:58',2.48,'2018-05-17 22:28:58',NULL,NULL),(360,74,'2018-05-17 22:29:08',2.48,'2018-05-17 22:29:08',NULL,NULL),(361,74,'2018-05-17 22:29:17',2.48,'2018-05-17 22:29:17',NULL,NULL),(362,74,'2018-05-17 22:30:32',2.48,'2018-05-17 22:30:32',NULL,NULL),(363,74,'2018-05-17 22:32:14',2.48,'2018-05-17 22:32:14',NULL,NULL),(364,77,'2018-05-17 22:37:00',13.64,NULL,NULL,NULL),(365,74,'2018-05-17 22:48:53',2.48,'2018-05-17 22:48:53',NULL,NULL),(366,74,'2018-05-17 22:49:42',2.48,'2018-05-17 22:49:42',NULL,NULL),(367,77,'2018-05-17 22:53:44',44.64,NULL,NULL,NULL),(368,77,'2018-05-18 00:14:14',17.36,NULL,NULL,NULL),(369,74,'2018-05-18 00:15:23',2.48,'2018-05-18 00:15:23',NULL,NULL),(370,74,'2018-05-18 00:15:23',7.19,'2018-05-18 00:15:23',NULL,NULL),(371,74,'2018-05-18 00:15:23',3.47,'2018-05-18 00:15:23',NULL,NULL),(372,74,'2018-05-18 00:15:36',7.19,'2018-05-18 00:15:36',NULL,NULL),(373,74,'2018-05-18 00:16:43',6.7,'2018-05-18 00:16:43',NULL,NULL),(374,74,'2018-05-18 00:16:43',2.73,'2018-05-18 00:16:43',NULL,NULL),(375,74,'2018-05-18 00:16:56',2.23,'2018-05-18 00:16:56',NULL,NULL),(376,74,'2018-05-18 00:37:01',7.19,'2018-05-18 00:37:01',NULL,NULL),(377,74,'2018-05-18 00:37:02',9.42,'2018-05-18 00:37:02',NULL,NULL),(378,77,'2018-05-18 00:53:01',57.04,NULL,NULL,NULL),(379,77,'2018-05-18 00:55:38',83.08,NULL,NULL,NULL),(380,77,'2018-05-18 00:59:55',34.72,NULL,NULL,NULL),(381,77,'2018-05-18 01:10:41',11.16,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ballance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custvend`
--

DROP TABLE IF EXISTS `custvend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `custvend` (
  `CUSTVENDID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Customer-Vendor Code',
  `FNAME` varchar(64) NOT NULL,
  `LNAME` varchar(64) NOT NULL,
  `AFM` varchar(20) DEFAULT ' ',
  `USERNAME` varchar(45) DEFAULT NULL,
  `PASSWD` varchar(255) NOT NULL,
  `ROLEID` int(11) NOT NULL COMMENT 'Role Code',
  `ADDRESS` varchar(100) DEFAULT NULL,
  `ZIP` varchar(10) DEFAULT NULL,
  `CITY` varchar(30) DEFAULT NULL,
  `DISTRICT` varchar(30) DEFAULT NULL COMMENT 'District(Nomos) Code',
  `PHONE` varchar(20) NOT NULL,
  `EMAIL` varchar(64) NOT NULL,
  `JOBNAME` varchar(128) DEFAULT NULL COMMENT 'Job Type Description',
  `BALLANCE` float DEFAULT NULL,
  `REMARKS` varchar(2000) DEFAULT NULL,
  `REGISTER` smallint(6) DEFAULT NULL COMMENT 'Registration Flag (Accept or not as vendor)',
  `ISACTIVE` smallint(6) NOT NULL DEFAULT '1',
  `INSDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `SYSUSER` smallint(6) DEFAULT NULL,
  `UPDDATE` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `CREDITS` float DEFAULT NULL,
  PRIMARY KEY (`CUSTVENDID`),
  UNIQUE KEY `idx_custvend_ROLEID_PHONE` (`ROLEID`,`PHONE`),
  KEY `PHONE` (`PHONE`),
  KEY `USERNAME` (`USERNAME`),
  CONSTRAINT `custvend_ibfk_1` FOREIGN KEY (`ROLEID`) REFERENCES `roles` (`ROLEID`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custvend`
--

LOCK TABLES `custvend` WRITE;
/*!40000 ALTER TABLE `custvend` DISABLE KEYS */;
INSERT INTO `custvend` VALUES (39,'admin','admin','23412345','admin','$2a$12$u9kPPQSQ9S.IHTzyz.rn4egJEfl7FSAOpLcTZYJaLys/hQbCwFODi',1,'Κηφισίας 53','18547','Αθήνα','Παγκράτι','6942988390','mixalisverros@hotmail.gr','Διαχειριστής',-133.03,NULL,1,1,'2018-05-17 22:08:34',NULL,'2018-05-18 00:37:01',0),(74,'Μιχάλης-Γιώργος','Βέρρος','12344123','mixaverros88','$2a$12$7qMS4c/dZtHZ3G30I.w2MuquZxQxZl2kRNp8vdx4I7QQmqc8fQhDO',2,'Κηφισίας 56','18547','Αθήνα','Παγκράτι','2242988390','mverros@kathimerini.gr','Γεωργός',0,NULL,1,1,'2018-05-17 22:06:10',NULL,'2018-05-18 00:17:19',0),(77,'Αγγελος','Φραγκουλίδης','1234567891','aggelos','$2a$12$XGYv2suxnMRyJ2VZTai7oOwskKMF3RQ7iYBznYJoIjTkbXpIFv.Oi',3,'Μιχαλακοπούλου 39','18547','Αθήνα','Ηλίσια','6942988310','a.fragoulidis@yahoo.com','Web developer',-19.35,NULL,0,1,'2018-05-18 00:13:34',NULL,'2018-05-18 01:10:40',0);
/*!40000 ALTER TABLE `custvend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `district` (
  `DISTRICT` int(11) NOT NULL AUTO_INCREMENT COMMENT 'District(Nomos) Code',
  `NAME` varchar(50) NOT NULL COMMENT 'District(Nomos) Name',
  `TRANSFEE` float DEFAULT NULL,
  PRIMARY KEY (`DISTRICT`),
  UNIQUE KEY `idx_district_NAME` (`NAME`),
  KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,'ΑΙΤΩΛΟΑΚΑΡΝΑΝΙΑΣ',5),(2,'ΑΡΓΟΛΙΔΑΣ',5),(3,'ΑΡΚΑΔΙΑΣ',7),(4,'ΑΡΤΑΣ',7),(5,'ΑΤΤΙΚΗΣ',3),(6,'ΑΧΑΪΑΣ',5),(7,'ΒΟΙΩΤΙΑΣ',5),(8,'ΓΡΕΒΕΝΩΝ',7),(9,'ΔΡΑΜΑΣ',10),(10,'ΔΩΔΕΚΑΝΗΣΟΥ',12),(11,'ΕΒΡΟΥ',12),(12,'ΕΥΒΟΙΑΣ',5),(13,'ΕΥΡΥΤΑΝΙΑΣ',5),(14,'ΖΑΚΥΝΘΟΥ',10),(15,'ΗΛΕΙΑΣ',5),(16,'ΗΜΑΘΙΑΣ',7),(17,'ΗΡΑΚΛΕΙΟΥ',10),(18,'ΘΕΣΠΡΩΤΙΑΣ',7),(19,'ΘΕΣΣΑΛΟΝΙΚΗΣ',5),(20,'ΙΩΑΝΝΙΝΩΝ',7),(21,'ΚΑΒΑΛΑΣ',7),(22,'ΚΑΡΔΙΤΣΑΣ',7),(23,'ΚΑΣΤΟΡΙΑΣ',10),(24,'ΚΕΡΚΥΡΑΣ',10),(25,'ΚΕΦΑΛΛΗΝΙΑΣ',10),(26,'ΚΙΛΚΙΣ',7),(27,'ΚΟΖΑΝΗΣ',10),(28,'ΚΟΡΙΝΘΙΑΣ',5),(29,'ΚΥΚΛΑΔΩΝ',12),(30,'ΛΑΚΩΝΙΑΣ',7),(31,'ΛΑΡΙΣΑΣ',5),(32,'ΛΑΣΙΘΙΟΥ',10),(33,'ΛΕΣΒΟΥ',12),(34,'ΛΕΥΚΑΔΑΣ',10),(35,'ΜΑΓΝΗΣΙΑΣ',7),(36,'ΜΕΣΣΗΝΙΑΣ',7),(37,'ΞΑΝΘΗΣ',10),(38,'ΠΕΛΛΗΣ',7),(39,'ΠΙΕΡΙΑΣ',7),(40,'ΠΡΕΒΕΖΑΣ',10),(41,'ΡΕΘΥΜΝΗΣ',10),(42,'ΡΟΔΟΠΗΣ',12),(43,'ΣΑΜΟΥ',12),(44,'ΣΕΡΡΩΝ',10),(45,'ΤΡΙΚΑΛΩΝ',7),(46,'ΦΘΙΩΤΙΔΑΣ',5),(47,'ΦΛΩΡΙΝΑΣ',12),(48,'ΦΩΚΙΔΑΣ',7),(49,'ΧΑΛΚΙΔΙΚΗΣ',7),(50,'ΧΑΝΙΩΝ',10),(51,'ΧΙΟΥ',12),(52,'ΔΙΑΦΟΡΑ',12);
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (73),(73);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderlines`
--

DROP TABLE IF EXISTS `orderlines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderlines` (
  `ORDERLINESID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDERID` int(11) NOT NULL COMMENT 'Table Orders ID',
  `ROLEID` int(11) NOT NULL COMMENT 'Role Code',
  `PRODUCTID` int(11) NOT NULL COMMENT 'Material Code',
  `VENDOR` int(11) DEFAULT NULL COMMENT 'Vendor Code',
  `PRODUNITID` int(11) DEFAULT NULL COMMENT 'Unit Code',
  `QTY` float DEFAULT NULL COMMENT 'Quantity',
  `PRICE` float DEFAULT NULL COMMENT 'Unit Value',
  `LINENETVAL` float NOT NULL COMMENT 'Net Line Value (Pro FPA)',
  `LINEVATVAL` float NOT NULL COMMENT 'Vat Line Value (FPA)',
  `LINESUMVAL` float NOT NULL COMMENT 'Sum Line Value',
  `REMARKS` varchar(2000) DEFAULT NULL COMMENT 'Comment Field',
  PRIMARY KEY (`ORDERLINESID`),
  KEY `ROLEID` (`ROLEID`),
  KEY `PRODUNITID` (`PRODUNITID`),
  KEY `idx_orderlines_ORDERS` (`ORDERID`),
  KEY `idx_orderlines_PRODUCTID` (`PRODUCTID`),
  KEY `idx_orderlines_ORDERS_PRODUCTID` (`ORDERID`,`PRODUCTID`),
  KEY `idx_orderlines_VENDOR` (`VENDOR`),
  CONSTRAINT `orderlines_ibfk_1` FOREIGN KEY (`ORDERID`) REFERENCES `orders` (`ORDERID`),
  CONSTRAINT `orderlines_ibfk_2` FOREIGN KEY (`ROLEID`) REFERENCES `roles` (`ROLEID`),
  CONSTRAINT `orderlines_ibfk_3` FOREIGN KEY (`PRODUCTID`) REFERENCES `product` (`PRODUCTID`),
  CONSTRAINT `orderlines_ibfk_4` FOREIGN KEY (`PRODUNITID`) REFERENCES `produnit` (`PRODUNITID`),
  CONSTRAINT `orderlines_ibfk_5` FOREIGN KEY (`VENDOR`) REFERENCES `custvend` (`CUSTVENDID`)
) ENGINE=InnoDB AUTO_INCREMENT=304 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderlines`
--

LOCK TABLES `orderlines` WRITE;
/*!40000 ALTER TABLE `orderlines` DISABLE KEYS */;
INSERT INTO `orderlines` VALUES (286,253,3,11,77,1,10,1.1,11,2.64,13.64,NULL),(287,254,2,9,77,1,2,1,2,0.48,2.48,NULL),(288,255,2,9,77,1,2,1,2,0.48,2.48,NULL),(289,256,3,21,77,1,30,1.2,36,8.64,44.64,NULL),(290,257,3,12,77,1,10,1.4,14,3.36,17.36,NULL),(291,258,2,9,77,1,2,1,2,0.48,2.48,NULL),(292,258,2,8,77,1,2,2.9,5.8,1.39,7.19,NULL),(293,258,2,25,77,2,2,1.4,2.8,0.67,3.47,NULL),(294,259,2,8,77,1,2,2.9,5.8,1.39,7.19,NULL),(295,260,2,14,77,1,3,1.8,5.4,1.3,6.7,NULL),(296,260,2,19,77,2,2,1.1,2.2,0.53,2.73,NULL),(297,261,2,24,77,2,2,0.9,1.8,0.43,2.23,NULL),(298,262,2,8,77,1,2,2.9,5.8,1.39,7.19,NULL),(299,262,2,11,77,1,4,1.9,7.6,1.82,9.42,NULL),(300,263,3,8,77,1,20,2.3,46,11.04,57.04,NULL),(301,264,3,8,77,1,29,2.3,67,16.08,83.08,NULL),(302,265,3,8,77,1,12,2.3,28,6.72,34.72,NULL),(303,266,3,9,77,1,23,0.4,9,2.16,11.16,NULL);
/*!40000 ALTER TABLE `orderlines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `ORDERID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLEID` int(11) NOT NULL COMMENT 'Role Code',
  `ORDERDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Transaction Date',
  `CUSTVENDID` int(11) DEFAULT NULL COMMENT 'Customer-Vendor Code',
  `VATID` int(11) NOT NULL COMMENT 'Vat Code',
  `ISCANCEL` smallint(6) NOT NULL DEFAULT '0',
  `ISAPPRV` smallint(6) NOT NULL DEFAULT '0' COMMENT 'Approve Flag',
  `ISPAYED` smallint(6) NOT NULL DEFAULT '0' COMMENT 'Is Payed to the Vendor Flag',
  `SUMAMNT` float NOT NULL COMMENT 'Sum Amount',
  `REMARKS` varchar(2000) DEFAULT NULL COMMENT 'Comment Field',
  `INSDATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'Insert Date',
  `SYSUSER` smallint(6) DEFAULT NULL COMMENT 'Transaction User',
  `UPDDATE` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update Date',
  `INVOICE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ORDERID`),
  KEY `ROLEID` (`ROLEID`),
  KEY `CUSTVENDID` (`CUSTVENDID`),
  KEY `idx_orders_ORDERDATE_CUSTVENDID` (`ORDERDATE`,`CUSTVENDID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`ROLEID`) REFERENCES `roles` (`ROLEID`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`CUSTVENDID`) REFERENCES `custvend` (`CUSTVENDID`)
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (253,3,'2018-05-14 22:37:00',77,1,0,1,0,13.64,NULL,'2018-05-17 22:41:20',77,'2018-05-18 00:19:34',NULL),(254,2,'2018-05-15 22:48:53',74,1,0,1,0,7.48,NULL,'2018-05-17 22:56:38',74,'2018-05-18 00:19:34','254.pdf'),(255,2,'2018-05-15 22:49:42',74,1,0,1,0,7.48,NULL,'2018-05-17 22:50:30',74,'2018-05-18 00:19:34','255.pdf'),(256,3,'2018-05-15 22:53:44',77,1,0,1,0,44.64,NULL,'2018-05-17 22:57:08',77,'2018-05-18 00:19:34',NULL),(257,3,'2018-05-16 00:14:14',77,1,0,1,0,17.36,NULL,'2018-05-18 00:14:41',77,'2018-05-18 00:19:34',NULL),(258,2,'2018-05-16 00:15:23',74,1,0,1,0,18.14,NULL,'2018-05-18 00:34:46',74,'2018-05-18 00:35:00','258.pdf'),(259,2,'2018-05-16 00:15:36',74,1,0,1,0,12.19,NULL,'2018-05-18 00:20:36',74,'2018-05-18 00:20:38','259.pdf'),(260,2,'2018-05-17 00:16:43',74,1,0,1,0,14.42,NULL,'2018-05-18 00:37:21',74,'2018-05-18 00:37:29','260.pdf'),(261,2,'2018-05-17 00:16:56',74,1,0,1,0,7.23,NULL,'2018-05-18 00:20:18',74,'2018-05-18 00:20:21','261.pdf'),(262,2,'2018-05-18 00:37:01',74,1,0,1,0,21.62,NULL,'2018-05-18 00:37:34',74,'2018-05-18 00:37:36','262.pdf'),(263,3,'2018-05-18 00:53:01',77,1,0,0,0,57.04,NULL,'2018-05-18 00:53:01',77,NULL,NULL),(264,3,'2018-05-18 00:55:38',77,1,0,0,0,83.08,NULL,'2018-05-18 00:55:38',77,NULL,NULL),(265,3,'2018-05-18 00:59:55',77,1,0,0,0,34.72,NULL,'2018-05-18 00:59:55',77,NULL,NULL),(266,3,'2018-05-18 01:10:41',77,1,0,0,0,11.16,NULL,'2018-05-18 01:10:41',77,NULL,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photos`
--

DROP TABLE IF EXISTS `photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photos` (
  `PHOTOSID` int(11) NOT NULL AUTO_INCREMENT,
  `URL` varchar(2000) NOT NULL,
  `MIMEtype` varchar(255) NOT NULL,
  PRIMARY KEY (`PHOTOSID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photos`
--

LOCK TABLES `photos` WRITE;
/*!40000 ALTER TABLE `photos` DISABLE KEYS */;
INSERT INTO `photos` VALUES (1,'12s.png','image/png'),(5,'2018-05-12-22-15-09aktinidia.jpg','image/jpeg'),(6,'2018-05-12-23-47-34rodakina.jpg','image/jpeg'),(7,'2018-05-12-23-53-05bananes.jpg','image/jpeg'),(8,'2018-05-12-23-54-21fraoula.jpg','image/jpeg'),(9,'2018-05-12-23-57-53kydonia.jpg','image/jpeg'),(10,'2018-05-13-00-05-33domates.jpg','image/jpeg'),(11,'2018-05-13-00-06-20spanaki.jpg','image/jpeg'),(12,'2018-05-13-00-07-28patates.jpg','image/jpeg'),(13,'2018-05-13-00-08-30piperies-florinis.jpg','image/jpeg'),(14,'2018-05-15-23-16-00mago.jpg','image/jpeg'),(16,'2018-05-16-19-14-53patzaria.jpg','image/jpeg'),(17,'2018-05-16-19-18-38xorta-agria.jpg','image/jpeg'),(18,'2018-05-16-19-31-33karota.jpg','image/jpeg'),(19,'2018-05-16-20-08-06fava.jpg','image/jpeg'),(20,'2018-05-16-20-10-14rizi.jpg','image/jpeg'),(21,'2018-05-16-20-11-56pligouri.jpg','image/jpeg'),(22,'2018-05-16-20-14-17adidia.jpg','image/jpeg'),(23,'2018-05-16-20-18-33melitzanes.jpg','image/jpeg'),(24,'2018-05-16-20-20-00prasinesPiperies.jpg','image/jpeg'),(31,'2018-05-17-20-30-46mantarinia-klimentines.jpg','image/jpeg');
/*!40000 ALTER TABLE `photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodcategory`
--

DROP TABLE IF EXISTS `prodcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prodcategory` (
  `PRODCATEGORYID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Category Code',
  `NAME` varchar(150) NOT NULL COMMENT 'Category Name',
  `SLUGNAME` varchar(150) NOT NULL COMMENT 'Greeklish Category Name',
  `ISACTIVE` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`PRODCATEGORYID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodcategory`
--

LOCK TABLES `prodcategory` WRITE;
/*!40000 ALTER TABLE `prodcategory` DISABLE KEYS */;
INSERT INTO `prodcategory` VALUES (1,'ΦΡΟΥΤΑ','FROUTA',1),(2,'ΛΑΧΑΝΙΚΑ','LACHANIKA',1),(3,'ΖΑΡΖΑΒΑΤΙΚΑ','ZARZAVATIKA',1),(4,'ΧΟΡΤΑΡΙΚΑ','CHORTARIKA',1),(5,'ΟΣΠΡΙΑ','OSPRIA',1),(7,'ddd2','ds',0);
/*!40000 ALTER TABLE `prodcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `PRODUCTID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Material Code',
  `NAME` varchar(200) NOT NULL COMMENT 'Material Name',
  `SLUGNAME` varchar(200) NOT NULL COMMENT 'Greeklish Material Name',
  `ISACTIVE` int(11) NOT NULL DEFAULT '1',
  `ISVISIBLE` smallint(6) NOT NULL DEFAULT '1',
  `PRODCATEGORYID` int(11) DEFAULT NULL COMMENT 'Material Category',
  `PRODUNITID` int(11) NOT NULL COMMENT 'Unit Code',
  `BUYPRICE` float DEFAULT NULL COMMENT 'Buy Price',
  `SELLPRICE` float DEFAULT NULL COMMENT 'Sale Price',
  `VENDOR` int(11) DEFAULT NULL COMMENT 'Vendor Code (CUSTVENDID)',
  `QTY` int(11) NOT NULL DEFAULT '0' COMMENT 'Quantity In Warehouse',
  `PHOTOSID` int(11) DEFAULT NULL COMMENT 'Product Photo',
  `REMARKS` varchar(2000) DEFAULT NULL,
  `INSDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `SYSUSER` int(11) DEFAULT NULL,
  `UPDDATE` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`PRODUCTID`),
  KEY `PRODCATEGORYID` (`PRODCATEGORYID`),
  KEY `PRODUNITID` (`PRODUNITID`),
  KEY `VENDOR` (`VENDOR`),
  KEY `idx_product_PRODUCTID_VENDOR` (`PRODUCTID`,`VENDOR`),
  KEY `product_ibfk_4` (`PHOTOSID`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`PRODCATEGORYID`) REFERENCES `prodcategory` (`PRODCATEGORYID`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`PRODUNITID`) REFERENCES `produnit` (`PRODUNITID`),
  CONSTRAINT `product_ibfk_3` FOREIGN KEY (`VENDOR`) REFERENCES `custvend` (`CUSTVENDID`),
  CONSTRAINT `product_ibfk_4` FOREIGN KEY (`PHOTOSID`) REFERENCES `photos` (`PHOTOSID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (8,'Πιπεριές Φλωρίνης','piperies-flwrinis',1,1,2,1,2.3,2.9,77,54,13,'Η πιπεριά είναι ποώδες και θαμνώδες φυτό με βλαστούς που στην αρχή είναι τρυφεροί και στη συνέχεια ξυλώδεις με φύλλα σχετικά μικρά, ανοιχτοπράσινα, άνθη λευκά. Ο καρπός της πιπεριάς είναι πολύσπερμος πράσινος ή κιτρινοπράσινος, που γίνεται κόκκινος ή κίτρινος όταν ωριμάσει. Το σχήμα του, ανάλογα με την ποικιλία, είναι κωνικό και μακρύ έως σφαιρικό ή τοματόμορφο. Χρησιμοποιείται κατά κόρον στην μαγειρική και τρώγεται είτα ωμή είτε μαγειρεμένη.','2018-05-13 00:09:05',39,'2018-05-18 00:37:34'),(9,'Πατάτες Θήβας','patates-thibas',1,1,2,1,0.4,1,77,28,12,'Η πατάτα γνωστή και ως \"γεώμηλο\", είναι φυτό που πλούσιο σε άμυλο και αποτελούν τροφή μεγάλης θρεπτικής αξίας. Αποτελεί τμήμα της βασικής διατροφής του πληθυσμού. Στην Ελλάδα την έφερε ο Ιωάννης Καποδίστριας, μάλιστα λόγω της επιφυλακτικότητας των Ελλήνων προς το νέο τρόφιμο τις κλείδωνε σε αποθήκες τις οποίες εσκεμμένα άφηνε αφύλακτες τη νύχτα, ώστε να μπορεί ο λαός να τις κλέψει νομίζοντας ότι είναι πολύτιμες. Τρώγεται τηγανιτή, βραστή ή ψητή.','2018-05-13 02:23:35',39,'2018-05-18 00:34:51'),(10,'Σπανάκι','spanaki',1,1,2,1,1,1.5,77,32,11,'Το σπανάκι είναι που καλλιεργείται για τα παχιά τριγωνικά φύλλα του. Αυτά βρίσκονται κοντά στη ρίζα έχουν χρώμα βαθύ πράσινο. Υπάρχουν αρκετές ποικιλίες σπανακιού. Το σπανάκι μπορεί να διατηρηθεί σε κοινούς καταψύκτες, αφού πρώτα ζεματιστεί, για πολύ μεγάλο χρονικό διάστημα. Τρώγεται μαγειρεμένο (σπανακόρυζο, σουπιές με σπανάκι), γίνεται διάφορες πίτες (σπανακόπιτα) ή ακόμα τρώγεται και ωμό σε διάφορες σαλάτες.','2018-05-13 00:06:53',39,'2018-05-17 22:27:45'),(11,'Ντομάτες Κρήτης','ntomates-kritis',1,1,2,1,1.1,1.9,77,40,10,'Η τομάτα, αλλιώς και ντομάτα, ζει μόνο μερικά χρόνια και συνήθως καλλιεργείται ως μονοετές φυτό. Τόσο ο βλαστός του φυτού όσο και τα φύλλα φέρουν τρίχωμα. Τα λουλούδια είναι κίτρινα με πέντε μυτερούς λοβούς και μεγαλώνουν σε ομάδες αποτελούμενες από 3-12. Η ντομάτα είναι ένα από τα πιο δημοφιλή λαχανικά του καλοκαιριού καθώς σπάνια λείπει από το τραπέζι η αγαπημένη μας χωριάτικη σαλάτα. Χρησιμοποιούνται στη μαγειρική αλλά και στην ζαχαροπλαστική (τσάτνεϊ, γλυκό του κουταλιού).','2018-05-13 00:05:44',39,'2018-05-18 00:37:34'),(12,'Κυδώνια','kudwnia',1,1,1,1,1.4,2.2,77,31,9,'Η κυδωνιά είναι οπωροφόρο δέντρο γνωστό από τα αρχαιότατα χρόνια, αφιερωμένο στην Αφροδίτη. Για το λόγο αυτό ονομάζεται και «Μήλο της Αφροδίτης». Είναι συγγενικό με τη μηλιά και την αχλαδιά. Ο καρπός του, το Κυδώνι, έχει χρώμα κίτρινο προς το χρυσό, όταν είναι ώριμο, και έχει σχήμα ακανόνιστο (αχλαδιού). Το εξωκάρπιο είναι αρχικά χνουδωτό και έπειτα λείο και γυαλιστερό. Η σάρκα του είναι λευκοκίτρινη και είναι πράσινα όταν είναι άγουρα. Χρησιμοποιούνται συχνά στη μαγειρική και τη ζαχαροπλαστική.','2018-05-12 23:58:42',39,'2018-05-18 00:14:41'),(13,'Φράουλες Εισαγωγής (Συσκ. 500γρ)','fraoules-eisagwgis-(susk.-500gr)',1,1,1,1,3.8,4.5,77,44,8,'Η φράουλα είναι πολυετές, έρπον κυρίως αλλά και αναρριχώμενο ποώδες φυτό με τριχωτά σύνθετα φύλλα που αποτελούνται από 3 φυλλάρια που τα περιθώρια τους είναι πριονωτά. Ο καρπός της φράουλας είναι σύνθετος και αποτελείται από μια ανθοδόχη που έχει στην επιφάνεια της πολλά μικρά σπόρια. Οι φράουλες καταναλώνονται ως νωπό φρούτο αλλά και επεξεργάζονται (κονσέρβες, χυμοί και άλλα). Χρησιμοποιούνται επίσης στη ζαχαροπλαστική, γίνονται μαρμελάδες, λικέρ, κομπόστες.','2018-05-12 23:55:55',39,'2018-05-17 22:27:45'),(14,'Μπανάνες DOLE','mpananes-DOLE',1,1,1,1,1.1,1.8,77,24,7,'Οι μπανάνες Dole είναι εγγυημένης ποιότητας καθώς η διαδικασία επιλογής τους διατηρεί τα αυστηρότερα κριτήρια. Καταναλώνονται νωπές, αφού αφαιρεθεί η φλούδα, χρησιμοποιούνται στη ζαχαροπλαστική, σε σαλάτες και αποτελούν εξαιρετική βρεφική τροφή.','2018-05-13 02:07:53',39,'2018-05-18 00:37:27'),(15,'Ροδάκινα Ελληνικά','Rodakina ellinika',1,1,1,1,1.2,1.6,77,25,6,'Το ροδάκινο έχει σφαιρικό ή ωοειδές σχήμα, ραφή στη ράχη και χνουδωτή φλούδα ανάλογα με τη ποικιλία σε διάφορους χρωματισμούς. Η σάρκα είναι χυμώδης, αρωματική με γλυκιά και υπόξινη γεύση. Ο πυρήνας του ροδάκινου (κουκούτσι) είναι μεγάλος κόκκινος με πολλές αυλακώσεις και μένει κολλημένος στη σάρκα ή ξεκολλάει εύκολα. Τα ροδάκινα εκτός από νωπά τρώγονται και ως κομπόστα, γίνονται μαρμελάδες χρησιμοποιούνται στη ζαχαροπλαστική, γίνονται λικέρ αναψυκτικά και χυμοί.','2018-05-14 21:32:38',39,'2018-05-17 22:27:45'),(16,'Ακτινίδια Ελληνικά','Aktinidia ellinika',1,1,2,1,1.8,2.2,77,55,5,'Το ακτινίδιο είναι θάμνος με άνθη χρώματος κόκκινου ή λευκού. Τα φύλλα του έχουν ωοειδές σχήμα και στο κάτω μέρος τους έχουν χνούδι. Ο καρπός του είναι ράγα και είναι εδώδιμος, με γλυκόξινη γεύση. Το εξωτερικό του μέρος είναι χρώματος καφέ και το εσωτερικό είναι χυμώδες, με πράσινο χρώμα και μικρά σποράκια, χρώματος μαύρου.','2018-05-15 18:32:18',39,'2018-05-17 22:27:45'),(17,'Μάνγκο (Τεμάχιο)','Mangko (Temaxio)',0,1,2,1,1.8,2.4,77,23,14,'Κατά πολλούς το Μάνγκο αποτελεί τον «βασιλιά των φρούτων», λόγω της μεγάλης του θρεπτικής αξίας, αλλά και της εκπληκτικής του γεύσης. Είναι ιδιαίτερα χορταστικό, χωρίς να μας φορτώνει με πολλές θερμίδες. Το «φρούτο της Ινδίας», όπως ονομάζεται μερικές φορές, έχει γλυκόξινη γεύση. Είναι συνήθως καμπυλωτό, στενόμακρο, με πράσινη, ροζέ κίτρινη ή κόκκινη φλούδα και σάρκα πολύ αρωματική σε έντονο πορτοκαλί χρώμα και έχει ένα πολύ μεγάλο, επίπεδο κουκούτσι που δεν τρώγεται.','2018-05-15 23:27:39',39,'2018-05-17 22:27:45'),(18,'Παντζάρια','Pantzaria',1,1,4,2,1.8,1.4,77,12,16,'Το παντζάρι ή κοκκινογούλι είναι φυτό του οποίου η ρίζα είναι εδώδιμη. Έχει μικρά άνθη και τα φύλλα του έχουν σχήμα καρδιάς. Το παντζάρι τρώγεται τόσο ωμό, όσο και μαγειρεμένο, ολόκληρο. Η ρίζα του ωμή είναι σκληρή, ενώ μαγειρεμένη είναι πιο μαλακή. Χρησιμοποιείται στις σαλάτες. Επίσης και τα φύλλα του είναι εδώδιμα τα οποία μπορούν να χρησιμοποιηθούν αντί του σπανακιού, όμως τα ωμά φύλλα μπορεί να αφήσουν πικρή γεύση στο στόμα.','2018-05-16 19:15:03',39,'2018-05-17 22:27:45'),(19,'Χόρτα Άγρια','Xorta Agria',1,1,4,2,1.4,1.1,77,33,17,'Τα άγρια χόρτα αποτελούν μια από τις πιο ολοκληρωμένες τροφές που προσφέρει η γη. Τα χόρτα συνδυάζονται αρμονικά με το κρέας, το ψάρι και τα ζυμαρικά.','2018-05-16 19:19:16',39,'2018-05-18 00:37:27'),(20,'Καρότα','Karota',1,1,3,2,1.2,0.8,77,34,18,'Το καρότο τρώγεται ωμό είτε σκέτο, είτε σε σαλάτες (κυρίως συνοδεύοντας το λάχανο), ή σαν τουρσί ή μαγειρεμένο (θεωρείται βασικό συστατικό στη φασολάδα, ενώ ταιριάζει πολύ καλά και με τον αρακά). Για να τα διατηρήσετε φρέσκα, ελαχιστοποιήστε την ποσότητα της υγρασίας που χάνουν. Για να το κάνετε αυτό, φροντίστε να τα αποθηκεύετε στο πιο δροσερό μέρος του ψυγείου, σε μια πλαστική σακούλα ή τυλιγμένα σε απορροφητικό χαρτί. Θα πρέπει να αποθηκεύετε τα καρότα μακριά από μήλα, αχλάδια, πατάτες και άλλα φρούτα και λαχανικά που παράγουν αέριο αιθυλενίου, δεδομένου ότι η έκθεση σε αυτό το αέριο, τα καθιστά πικρά.','2018-05-16 19:32:18',39,'2018-05-17 22:27:45'),(21,'Φάβα (Συσκευασία 500γρ)','Faba (Suskeuasia 500gr)',1,1,5,1,1.2,1,77,75,19,'Η γνωστή σε όλους μας «φάβα» είναι ένα προϊόν που υπάρχει σε πολλά μέρη της Ελλάδος, η πιο γνωστή είναι αυτή της Σαντορίνης. Παρασκευάζεται συνήθως από το φυτό λαθούρι αλλά και από μερικά ακόμη βρασμένα και αλεσμένα όσπρια, είτε ξερά μπιζέλια είτε κουκιά. Χρησιμοποιείται ως ορεκτικό ή συνοδευτικό.','2018-05-16 20:08:45',39,'2018-05-17 22:57:08'),(22,'Ρύζι Μπασμάτι (Συσκευασία 1kg)','Ruzi Mpasmati (Suskeuasia 1kg)',1,1,5,1,2.2,1.8,77,23,20,'Το ρύζι είναι ένα από τα βασικά διατροφικά είδη της ανθρωπότητας: τα δυο είδη του αποτελούν το ένα πέμπτο των συνολικά καταναλισκόμενων θερμίδων παγκοσμίως. Το χρώμα του Ρύζι Μπασμάτι είναι λευκό και το σχήμα του μακρύσπερμο. Είναι αρωματικό, χωρίς γλουτένη ρύζι που καλλιεργείται στις περιοχές των Ιμαλαίων. Το άρωμα του έχει σχέση με τα ιδιαίτερα συστατικά του υπεδάφους των περιοχών που καλλιεργείται. Ρύζι για κάθε χρήση, με ιδιαίτερη γεύση.','2018-05-16 20:10:52',39,'2018-05-17 22:27:45'),(23,'Πλιγούρι (Συσκευασία 500γρ)','Pligouri (Suskeuasia 500gr)',1,1,5,1,1.2,0.9,77,22,21,'Πλιγούρι, πουργκούρι, μπλουγούρι, χόντρος είναι δημητριακό. Το πλιγούρι είναι το βρασμένο σιτάρι, το οποίο αφού στεγνώσει αλέθεται σε τραχείς κόκκους. Παλιότερα το χρησιμοποιούσαν για να φτιάξουν πιλάφι και ντολμάδες.Τρώγεται ζεστό αλλά και κρύο σαν σαλάτα, με ψιλοκομμένη ντομάτα, μαϊντανό και ελιές.','2018-05-16 20:12:31',39,'2018-05-17 22:27:45'),(24,'Αντίδια','Antidia',1,1,4,2,1.3,0.9,77,42,22,'Τα αντίδια (ήμερα ραδίκια) καλλιεργούνται κατά την διάρκεια όλου του χρόνου και είναι πλατύφυλλα, με γλυκύτερη γεύση από τα άγρια ραδίκια. Τα χόρτα συνδυάζονται αρμονικά με το κρέας, το ψάρι και τα ζυμαρικά.','2018-05-16 20:15:06',39,'2018-05-18 00:20:18'),(25,'Μελιτζάνες','Melitzanes',1,1,3,2,74,1.4,77,12,23,'Αν και συγγενεύει στενά με την πατάτα και αναπτύσσεται παρόμοια με την ντομάτα, κερδίζει επάξια τον τίτλο του «βασιλιά των λαχανικών». Αγαπημένο λαχανικό του καλοκαιριού & της ελληνικής κουζίνας που πρωταγωνιστεί στον μουσακά -το κλασσικό εθνικό μας πιάτο- στα αγαπημένα παπουτσάκια ή το, ιμάμ-μπαϊλντί.','2018-05-16 20:19:07',39,'2018-05-18 00:34:54'),(26,'Πιπεριές Πράσινες','Piperies Prasines',1,1,3,2,1.9,1.3,77,32,24,'Η πιπεριά είναι ποώδες και θαμνώδες φυτό με βλαστούς που στην αρχή είναι τρυφεροί και στη συνέχεια ξυλώδεις με φύλλα σχετικά μικρά, ανοιχτοπράσινα, άνθη λευκά. Ο καρπός της πιπεριάς είναι πολύσπερμος πράσινος ή κιτρινοπράσινος, που γίνεται κόκκινος ή κίτρινος όταν ωριμάσει. Το σχήμα του, ανάλογα με την ποικιλία, είναι κωνικό και μακρύ έως σφαιρικό ή τοματόμορφο. Χρησιμοποιείται κατά κόρον στην μαγειρική και τρώγεται είτα ωμή είτε μαγειρεμένη.','2018-05-17 22:57:56',39,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produnit`
--

DROP TABLE IF EXISTS `produnit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produnit` (
  `PRODUNITID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unit Code',
  `NAME` varchar(50) NOT NULL COMMENT 'Unit Name',
  `ISACTIVE` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`PRODUNITID`),
  UNIQUE KEY `idx_produnit_SHORTCUT` (`NAME`),
  KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produnit`
--

LOCK TABLES `produnit` WRITE;
/*!40000 ALTER TABLE `produnit` DISABLE KEYS */;
INSERT INTO `produnit` VALUES (1,'τμχ',1),(2,'Kg',1),(3,'ΜΕΤΡΑ',0),(4,'ΩΡΕΣ',0);
/*!40000 ALTER TABLE `produnit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(2,'Πελάτης'),(3,'Προμηθερυτής');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `ROLEID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Role Code',
  `DESCR` varchar(255) NOT NULL COMMENT 'Role Name',
  `ISACTIVE` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ROLEID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Διαχειριστής',1),(2,'Πελάτης',1),(3,'Προμηθευτής',1);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscribe`
--

DROP TABLE IF EXISTS `subscribe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscribe` (
  `SUBSCRIBE` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Subscriber Code',
  `EMAIL` varchar(255) NOT NULL,
  `INSDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`SUBSCRIBE`),
  UNIQUE KEY `idx_subscribe_EMAIL` (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscribe`
--

LOCK TABLES `subscribe` WRITE;
/*!40000 ALTER TABLE `subscribe` DISABLE KEYS */;
INSERT INTO `subscribe` VALUES (1,'dsads@dsadwas.gr','2018-05-07 00:59:38'),(2,'mixalisverros@hotmail.gr','2018-05-07 01:00:20');
/*!40000 ALTER TABLE `subscribe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_role_roleid_idx` (`role_id`),
  CONSTRAINT `fk_user_role_roleid` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (43,1),(44,2),(42,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vat`
--

DROP TABLE IF EXISTS `vat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vat` (
  `VATID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Vat Code',
  `PERCNT` float NOT NULL COMMENT 'Vat Percentace',
  `ISACTIVE` smallint(6) NOT NULL,
  PRIMARY KEY (`VATID`),
  UNIQUE KEY `idx_vat_PERCNT` (`PERCNT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vat`
--

LOCK TABLES `vat` WRITE;
/*!40000 ALTER TABLE `vat` DISABLE KEYS */;
/*!40000 ALTER TABLE `vat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wishlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'poll'
--

--
-- Dumping routines for database 'poll'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-21 20:51:38
