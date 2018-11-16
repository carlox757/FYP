-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: projectdb
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artist` (
  `artistId` int(11) NOT NULL AUTO_INCREMENT,
  `artistName` varchar(50) DEFAULT NULL,
  `artistSurname` varchar(50) DEFAULT NULL,
  `artistPhoneNumber` varchar(50) DEFAULT NULL,
  `artistEmail` varchar(70) DEFAULT NULL,
  `gender` enum('Male','Female') DEFAULT 'Male',
  `dateOfBirth` date DEFAULT NULL,
  `typeOfArtist` enum('National','International') DEFAULT 'National',
  PRIMARY KEY (`artistId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES (1,'Carlos','Esparragoza','07475973192','carlox757@gmail.com','Male','1970-05-20','National'),(2,'Josan','Hernandez','07475979192','josan@gmail.com','Male','1993-10-06','National'),(4,'Yayo','Hernandez','074757856','ya@gmail.com','Male','1991-04-19','International'),(5,'Josan','Hernandez','07456589454','jo@hmail.com','Male','1993-10-06','International'),(7,'Test','One','07475989892','test@gmail.com','Male','1992-04-10','International');
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupartist`
--

DROP TABLE IF EXISTS `groupartist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groupartist` (
  `groupID` int(11) NOT NULL AUTO_INCREMENT,
  `artistId` int(11) NOT NULL,
  `songID` int(11) NOT NULL,
  `groupName` varchar(50) DEFAULT NULL,
  `numberOfTimesPlayed` int(11) DEFAULT NULL,
  PRIMARY KEY (`groupID`,`artistId`,`songID`),
  KEY `artistId_idx` (`artistId`),
  KEY `songID_idx` (`songID`),
  CONSTRAINT `artistId` FOREIGN KEY (`artistId`) REFERENCES `artist` (`artistId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `songID` FOREIGN KEY (`songID`) REFERENCES `song` (`songID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupartist`
--

LOCK TABLES `groupartist` WRITE;
/*!40000 ALTER TABLE `groupartist` DISABLE KEYS */;
INSERT INTO `groupartist` VALUES (1,1,1,'Carlos solo',50),(1,1,2,'Carlos solo',20);
/*!40000 ALTER TABLE `groupartist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `song` (
  `songID` int(11) NOT NULL AUTO_INCREMENT,
  `songName` varchar(50) DEFAULT NULL,
  `album` varchar(50) DEFAULT NULL,
  `dateCreated` date DEFAULT NULL,
  PRIMARY KEY (`songID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES (1,'Son1','hello','2017-03-04'),(2,'song2','bye','2016-05-17'),(4,'IDid','none','2017-04-03'),(5,'Call me','Help','2014-04-17'),(6,'TestSong','Testing','2017-03-27');
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-25  0:32:47
