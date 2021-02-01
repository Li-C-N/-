-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbo
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_activity`
--

DROP TABLE IF EXISTS `t_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_activity` (
  `a_id` int NOT NULL AUTO_INCREMENT,
  `a_title` varchar(30) NOT NULL,
  `a_type` int NOT NULL,
  `a_start_time` datetime DEFAULT NULL,
  `a_end_time` datetime DEFAULT NULL,
  `a_address` varchar(100) DEFAULT NULL,
  `a_information` text,
  `a_photo_index_big_url` varchar(100) DEFAULT NULL,
  `a_photo_index_sm_url` varchar(100) DEFAULT NULL,
  `a_photo_url` varchar(100) DEFAULT NULL,
  `a_introduce` varchar(100) DEFAULT NULL,
  `a_flag` int DEFAULT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_activity`
--

LOCK TABLES `t_activity` WRITE;
/*!40000 ALTER TABLE `t_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_admin`
--

DROP TABLE IF EXISTS `t_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_username` varchar(20) DEFAULT NULL,
  `admin_password` varchar(20) DEFAULT NULL,
  `admin_caption_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_admin`
--

LOCK TABLES `t_admin` WRITE;
/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_browse`
--

DROP TABLE IF EXISTS `t_browse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_browse` (
  `browse_id` int NOT NULL AUTO_INCREMENT,
  `browse_type_id` int NOT NULL,
  `browse_user_id` int NOT NULL,
  PRIMARY KEY (`browse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_browse`
--

LOCK TABLES `t_browse` WRITE;
/*!40000 ALTER TABLE `t_browse` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_browse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_exchange`
--

DROP TABLE IF EXISTS `t_exchange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_exchange` (
  `ex_id` int NOT NULL AUTO_INCREMENT,
  `ex_goods_id` int NOT NULL,
  `ex_time` datetime NOT NULL,
  `ex_goods_name` varchar(50) NOT NULL,
  `ex_goods_integral` int NOT NULL,
  `ex_num` int NOT NULL,
  `ex_user_id` int NOT NULL,
  PRIMARY KEY (`ex_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_exchange`
--

LOCK TABLES `t_exchange` WRITE;
/*!40000 ALTER TABLE `t_exchange` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_exchange` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_goods`
--

DROP TABLE IF EXISTS `t_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_goods` (
  `goods_id` int NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(20) NOT NULL,
  `goods_type_id` int NOT NULL,
  `goods_integral` int NOT NULL,
  `goods_photo_index_big_url` varchar(100) DEFAULT NULL,
  `goods_photo_index_sm_url` varchar(100) DEFAULT NULL,
  `goods_photo_url` varchar(100) DEFAULT NULL,
  `goods_start_time` datetime DEFAULT NULL,
  `goods_end_time` datetime DEFAULT NULL,
  `goods_introduce` varchar(100) DEFAULT NULL,
  `goods_infromation` text,
  `goods_flag` int DEFAULT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_goods`
--

LOCK TABLES `t_goods` WRITE;
/*!40000 ALTER TABLE `t_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_praise`
--

DROP TABLE IF EXISTS `t_praise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_praise` (
  `praise_id` int NOT NULL AUTO_INCREMENT,
  `praise_type_id` int NOT NULL,
  `praise_user_id` int NOT NULL,
  PRIMARY KEY (`praise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_praise`
--

LOCK TABLES `t_praise` WRITE;
/*!40000 ALTER TABLE `t_praise` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_praise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_study`
--

DROP TABLE IF EXISTS `t_study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_study` (
  `stu_id` int NOT NULL AUTO_INCREMENT,
  `stu_title` varchar(50) NOT NULL,
  `stu_introduce` varchar(100) DEFAULT NULL,
  `stu_information` text,
  `stu_type_id` int DEFAULT NULL,
  `stu_browse_number` int DEFAULT NULL,
  `stu_praise_number` int DEFAULT NULL,
  `stu_photo_index_big__url` varchar(100) DEFAULT NULL,
  `stu_photo_index_sm_url` varchar(100) DEFAULT NULL,
  `stu_photo_url` varchar(100) DEFAULT NULL,
  `stu_start_time` datetime DEFAULT NULL,
  `stu_end_time` datetime DEFAULT NULL,
  `stu_flag` int DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_study`
--

LOCK TABLES `t_study` WRITE;
/*!40000 ALTER TABLE `t_study` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_type`
--

DROP TABLE IF EXISTS `t_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_type` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_flag` int NOT NULL,
  `type_name` varchar(50) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_type`
--

LOCK TABLES `t_type` WRITE;
/*!40000 ALTER TABLE `t_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_username` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_phone_number` char(11) NOT NULL,
  `user_caption_url` varchar(100) DEFAULT NULL,
  `user_birthday` datetime DEFAULT NULL,
  `user_integral` int DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-01 11:13:33
