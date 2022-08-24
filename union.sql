-- MySQL dump 10.13  Distrib 5.7.33, for Linux (x86_64)
--
-- Host: localhost    Database: unionn
-- ------------------------------------------------------
-- Server version	5.7.33-0ubuntu0.16.04.1-log

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
-- Table structure for table `achievement_rate_month`
--

DROP TABLE IF EXISTS `achievement_rate_month`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `achievement_rate_month` (
  `goal_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `day_of_week` varchar(30) NOT NULL,
  `goal_rate` double DEFAULT NULL,
  KEY `fk_parent` (`goal_id`),
  CONSTRAINT `achievement_rate_month_ibfk_1` FOREIGN KEY (`goal_id`) REFERENCES `goal_create` (`goal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievement_rate_month`
--

LOCK TABLES `achievement_rate_month` WRITE;
/*!40000 ALTER TABLE `achievement_rate_month` DISABLE KEYS */;
INSERT INTO `achievement_rate_month` VALUES (10,'2022-06-21','火曜日',0),(10,'2022-06-28','火曜日',100),(10,'2022-07-05','火曜日',83),(10,'2022-07-12','火曜日',83),(10,'2022-07-19','火曜日',100),(10,'2022-07-21','木曜日',100),(20,'2022-08-10','水曜日',100),(20,'2022-08-17','水曜日',100),(20,'2022-08-24','水曜日',100),(20,'2022-08-31','水曜日',100),(20,'2022-09-07','水曜日',100),(20,'2022-09-10','土曜日',100);
/*!40000 ALTER TABLE `achievement_rate_month` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `achievement_rate_week`
--

DROP TABLE IF EXISTS `achievement_rate_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `achievement_rate_week` (
  `goal_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `day_of_week` varchar(30) NOT NULL,
  `goal_rate` double DEFAULT NULL,
  KEY `fk_parent` (`goal_id`),
  CONSTRAINT `achievement_rate_week_ibfk_1` FOREIGN KEY (`goal_id`) REFERENCES `goal_create` (`goal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievement_rate_week`
--

LOCK TABLES `achievement_rate_week` WRITE;
/*!40000 ALTER TABLE `achievement_rate_week` DISABLE KEYS */;
INSERT INTO `achievement_rate_week` VALUES (19,'2022-07-16','土曜日',28),(19,'2022-07-17','日曜日',28),(19,'2022-07-18','月曜日',28),(19,'2022-07-19','火曜日',28),(22,'2022-08-13','土曜日',20),(22,'2022-08-14','日曜日',20),(22,'2022-08-15','月曜日',20),(22,'2022-08-16','火曜日',20);
/*!40000 ALTER TABLE `achievement_rate_week` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `achievement_rate_year`
--

DROP TABLE IF EXISTS `achievement_rate_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `achievement_rate_year` (
  `goal_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `day_of_week` varchar(30) NOT NULL,
  `goal_rate` double DEFAULT NULL,
  KEY `fk_parent` (`goal_id`),
  CONSTRAINT `achievement_rate_year_ibfk_1` FOREIGN KEY (`goal_id`) REFERENCES `goal_create` (`goal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievement_rate_year`
--

LOCK TABLES `achievement_rate_year` WRITE;
/*!40000 ALTER TABLE `achievement_rate_year` DISABLE KEYS */;
INSERT INTO `achievement_rate_year` VALUES (11,'2022-07-03','日曜日',50),(11,'2022-08-03','水曜日',100),(11,'2022-09-03','土曜日',100),(11,'2022-10-03','月曜日',100),(11,'2022-11-03','木曜日',100),(11,'2022-12-03','土曜日',100),(11,'2023-01-03','火曜日',100),(11,'2023-02-03','金曜日',100),(11,'2023-03-03','金曜日',100),(11,'2023-04-03','月曜日',100),(11,'2023-05-03','水曜日',100),(11,'2023-06-03','土曜日',100),(11,'2023-07-03','月曜日',100),(21,'2022-08-17','水曜日',100),(21,'2022-09-17','土曜日',100),(21,'2022-10-17','月曜日',100),(21,'2022-11-17','木曜日',100),(21,'2022-12-17','土曜日',100),(21,'2023-01-17','火曜日',100),(21,'2023-02-17','金曜日',100),(21,'2023-03-17','金曜日',100),(21,'2023-04-17','月曜日',100),(21,'2023-05-17','水曜日',100),(21,'2023-06-17','土曜日',100),(21,'2023-07-17','月曜日',100),(21,'2023-08-17','木曜日',100);
/*!40000 ALTER TABLE `achievement_rate_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal_create`
--

DROP TABLE IF EXISTS `goal_create`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goal_create` (
  `goal_id` int(11) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `goal_type` tinyint(4) NOT NULL,
  `goal_start_date` date NOT NULL,
  `goal_end_date` date NOT NULL,
  PRIMARY KEY (`goal_id`),
  KEY `fk_parent` (`user_id`),
  CONSTRAINT `goal_create_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal_create`
--

LOCK TABLES `goal_create` WRITE;
/*!40000 ALTER TABLE `goal_create` DISABLE KEYS */;
INSERT INTO `goal_create` VALUES (7,'ok2',1,'2022-05-02','2022-05-07'),(10,'Yoshiki',2,'2022-06-21','2022-07-21'),(11,'Yoshiki',3,'2022-07-03','2023-07-03'),(19,'Yoshiki',1,'2022-07-16','2022-07-19'),(20,'Yoshiki',2,'2022-08-10','2022-09-10'),(21,'Yoshiki',3,'2022-08-17','2023-08-17'),(22,'Yoshiki',1,'2022-08-13','2022-08-16');
/*!40000 ALTER TABLE `goal_create` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal_month_child`
--

DROP TABLE IF EXISTS `goal_month_child`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goal_month_child` (
  `child_id` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `goal_id` int(11) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `goal` varchar(255) DEFAULT NULL,
  `achieved_goal` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`child_id`),
  KEY `fk_parent` (`parent_id`),
  CONSTRAINT `goal_month_child_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `goal_month_parent` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal_month_child`
--

LOCK TABLES `goal_month_child` WRITE;
/*!40000 ALTER TABLE `goal_month_child` DISABLE KEYS */;
INSERT INTO `goal_month_child` VALUES (1,1,10,'Yoshiki','テスト1',1),(2,2,10,'Yoshiki','test2',1),(3,3,10,'Yoshiki','テスト1',1),(4,4,10,'Yoshiki',NULL,1),(5,5,20,'Yoshiki','テスト1',1),(6,6,20,'Yoshiki','テスト22',1);
/*!40000 ALTER TABLE `goal_month_child` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal_month_parent`
--

DROP TABLE IF EXISTS `goal_month_parent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goal_month_parent` (
  `goal_id` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `goal` varchar(255) DEFAULT NULL,
  `achieved_goals` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`parent_id`),
  KEY `fk_parent` (`goal_id`),
  CONSTRAINT `goal_month_parent_ibfk_1` FOREIGN KEY (`goal_id`) REFERENCES `goal_create` (`goal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal_month_parent`
--

LOCK TABLES `goal_month_parent` WRITE;
/*!40000 ALTER TABLE `goal_month_parent` DISABLE KEYS */;
INSERT INTO `goal_month_parent` VALUES (10,1,'Yoshiki','test',100),(10,2,'Yoshiki','test2',100),(10,3,'Yoshiki',NULL,100),(10,4,'Yoshiki','テスト1',100),(20,5,'Yoshiki','テスト1',NULL),(20,6,'Yoshiki','テスト2',100);
/*!40000 ALTER TABLE `goal_month_parent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal_week`
--

DROP TABLE IF EXISTS `goal_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goal_week` (
  `goal_id` int(11) NOT NULL,
  `goal_week_id` int(11) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `goal_type` tinyint(4) NOT NULL,
  `goal` varchar(255) DEFAULT NULL,
  `goal_condition` tinyint(4) DEFAULT NULL,
  `goal_count` tinyint(4) DEFAULT NULL,
  `goal_rate` double DEFAULT NULL,
  PRIMARY KEY (`goal_week_id`),
  KEY `fk_parent` (`goal_id`),
  CONSTRAINT `goal_week_ibfk_1` FOREIGN KEY (`goal_id`) REFERENCES `goal_create` (`goal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal_week`
--

LOCK TABLES `goal_week` WRITE;
/*!40000 ALTER TABLE `goal_week` DISABLE KEYS */;
INSERT INTO `goal_week` VALUES (19,1,'Yoshiki',1,'テスト1',7,2,0),(19,2,'Yoshiki',1,'mm',4,4,0),(22,3,'Yoshiki',1,'テスト1',5,1,0);
/*!40000 ALTER TABLE `goal_week` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal_year_child`
--

DROP TABLE IF EXISTS `goal_year_child`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goal_year_child` (
  `child_id` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `goal_id` int(11) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `goal` varchar(255) DEFAULT NULL,
  `achieved_goal` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`child_id`),
  KEY `fk_parent` (`parent_id`),
  CONSTRAINT `goal_year_child_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `goal_year_parent` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal_year_child`
--

LOCK TABLES `goal_year_child` WRITE;
/*!40000 ALTER TABLE `goal_year_child` DISABLE KEYS */;
INSERT INTO `goal_year_child` VALUES (1,1,11,'Yoshiki','Yoshiki jr',1),(2,1,11,'Yoshiki','テスト1',1),(3,2,11,'Yoshiki','mm',1),(4,3,21,'Yoshiki','テスト11',1);
/*!40000 ALTER TABLE `goal_year_child` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal_year_parent`
--

DROP TABLE IF EXISTS `goal_year_parent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goal_year_parent` (
  `goal_id` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `goal` varchar(255) DEFAULT NULL,
  `achieved_goals` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`parent_id`),
  KEY `fk_parent` (`goal_id`),
  CONSTRAINT `goal_year_parent_ibfk_1` FOREIGN KEY (`goal_id`) REFERENCES `goal_create` (`goal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal_year_parent`
--

LOCK TABLES `goal_year_parent` WRITE;
/*!40000 ALTER TABLE `goal_year_parent` DISABLE KEYS */;
INSERT INTO `goal_year_parent` VALUES (11,1,'Yoshiki','テスト1',100),(11,2,'Yoshiki','テスト1',100),(21,3,'Yoshiki','テスト1',100);
/*!40000 ALTER TABLE `goal_year_parent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` varchar(30) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('ã¦ã¼ã¶ã¼å','ã¦ã¼ã¶ã¼ID','$2a$10$7S4vRYx14Re8hKWW6Qac1eZpKmZfTEyt6kXFchnic35wYvKoBVEue'),('GGG','murata','$2a$10$20A0pgjv0NNn9xT7.qsAeOSpRsTCaTMnAetEEILIhm0EaB2CjA22y'),('hikakin','youtube','$2a$10$qB.vFRNv8GCuJISxjqJQyeFH0.u/afFiwDvRHHZ1V8xZRqNjuYana'),('id','name','$2a$10$3gYqIUkD/LbHlbhF4bcN4uVsUfve6i.Plfj0Omw3ym0tV3pXlaFi.'),('iddd','name','$2a$10$w33LT5AdC5FaLUYm8l9pJ.NgkBA83bdI6L.qN34uETqJJs6FMO7Ja'),('murata','GGG','$2a$10$GKQ7s0RoDwb39lxc3AVif.6GVeTAM6ZpGzzh0g3.qaKROoH5UJ4R.'),('NAME','ID','$2a$10$840MXUIoV/ilmkesu3nXUOZbh57nwf7IIuZsUY4P.6Sh94.8c2mdC'),('namee','idd','$2a$10$FIbuHHLo7/U2PWC7nNT9qu05L7zli1zJjUk0d/Y6RM/30NprVMnzS'),('ok','ããã¯ã','$2a$10$dCMWGhxqidzpdrzbT2tQK.UbUGH.NPs4lYolQXGoJaqILUfaeFBRi'),('ok2','Yoshiki2','$2a$10$Wz.jhaBLjpuB7Eh81ZI1Se8AIalakruEDpHJtd3hfv8vdLipjloYS'),('user_name','user_id','$2a$10$43O/eFUx8eUOoh7mKXiYluzOF3fmEOEtag8intbdaMU5e8YrCgas2'),('Yoshiki','ok','$2a$10$jf8BgtOKkpXMk//REgiCOu4KVwan2IR7KFubzf5MbDqVtnGTvXD/G'),('æ°´','ããã¯ã','$2a$10$oN.3LWTN2ExOYcD8qNKaH.yqJcl59ZRTQQYRloDWDRWxKiBBfzREq');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-16 19:03:12
