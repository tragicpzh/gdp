-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: gdp
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_id` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL DEFAULT '123456',
  `name` varchar(50) NOT NULL DEFAULT 'User',
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `head_portrait` varchar(1023) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_id_UNIQUE` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (0,'1','123456','张三',NULL,NULL,NULL,'2020-06-17 14:01:21');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `college` (
  `id` varchar(3) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_numer` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES ('1','1','1','1','1');
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `id` varchar(6) NOT NULL,
  `college_id` varchar(3) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `major_college_id_idx` (`college_id`),
  CONSTRAINT `major_college_id` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES ('1','1','1','1','1','1');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL DEFAULT '123456',
  `name` varchar(50) NOT NULL DEFAULT 'User',
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `head_portrait` varchar(45) DEFAULT NULL,
  `major_id` varchar(6) NOT NULL,
  `state` varchar(20) NOT NULL DEFAULT 'NO_SELECTION',
  `subject_id` bigint DEFAULT NULL,
  `open_document` varchar(1023) DEFAULT NULL,
  `middle_document` varchar(1023) DEFAULT NULL,
  `conclusion_document` varchar(1023) DEFAULT NULL,
  `paper_document` varchar(1023) DEFAULT NULL,
  `open_score1` int DEFAULT NULL,
  `open_score2` int DEFAULT NULL,
  `open_score3` int DEFAULT NULL,
  `middle_score1` int DEFAULT NULL,
  `middle_score2` int DEFAULT NULL,
  `middle_score3` int DEFAULT NULL,
  `conclusion_score1` int DEFAULT NULL,
  `conclusion_score2` int DEFAULT NULL,
  `conclusion_score3` int DEFAULT NULL,
  `teacher_paper_score` int DEFAULT NULL,
  `student_paper_score` int DEFAULT NULL,
  `final_score` decimal(5,2) DEFAULT NULL,
  `open_evaluation1` varchar(1023) DEFAULT NULL,
  `open_evaluation2` varchar(1023) DEFAULT NULL,
  `open_evaluation3` varchar(1023) DEFAULT NULL,
  `middle_evaluation1` varchar(1023) DEFAULT NULL,
  `middle_evaluation2` varchar(1023) DEFAULT NULL,
  `middle_evaluation3` varchar(1023) DEFAULT NULL,
  `teacher_paper_evaluation` varchar(1023) DEFAULT NULL,
  `cross_paper_evaluation` varchar(45) DEFAULT NULL,
  `conclusion_evaluation1` varchar(45) DEFAULT NULL,
  `conclusion_evaluation2` varchar(45) DEFAULT NULL,
  `conclusion_evaluation3` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_id_UNIQUE` (`student_id`),
  KEY `student_major_id` (`major_id`),
  KEY `student_subject_id_idx` (`subject_id`),
  KEY `student_subject_id_id` (`subject_id`),
  CONSTRAINT `student_major_id` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`),
  CONSTRAINT `student_subject_id` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=325 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (0,'3','323456','王五','1','1','2020-06-17 13:23:47',NULL,'1','WaitOpenScore',NULL,'323\\openReport\\技术路线分析.docx','323\\middleReport\\技术路线分析.docx','323\\conclusionReport\\技术路线分析.docx','323\\paper\\技术路线分析.docx',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,99,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_teacher_id` varchar(20) NOT NULL,
  `cross_review_teacher` varchar(20) DEFAULT NULL,
  `major_id` varchar(6) NOT NULL,
  `name` varchar(50) NOT NULL,
  `direction` varchar(50) NOT NULL,
  `difficulty` decimal(2,2) NOT NULL,
  `technology` varchar(255) NOT NULL,
  `describe` varchar(1023) NOT NULL,
  `document` varchar(1023) DEFAULT NULL,
  `review_teacher_id1` varchar(20) DEFAULT NULL,
  `review_teacher_id2` varchar(20) DEFAULT NULL,
  `review_teacher_id3` varchar(20) DEFAULT NULL,
  `state` varchar(20) NOT NULL DEFAULT 'NEW',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `create_teacher_id_idx` (`create_teacher_id`),
  KEY `cross_review_teacher_id_idx` (`cross_review_teacher`),
  KEY `review_teacher_id1_idx` (`review_teacher_id1`),
  KEY `review_teacher_id2_idx` (`review_teacher_id2`),
  KEY `review_teacher_id3_idx` (`review_teacher_id3`),
  KEY `major_id_idx` (`major_id`),
  CONSTRAINT `create_teacher_id` FOREIGN KEY (`create_teacher_id`) REFERENCES `teacher` (`teacher_id`),
  CONSTRAINT `cross_review_teacher_id` FOREIGN KEY (`cross_review_teacher`) REFERENCES `teacher` (`teacher_id`),
  CONSTRAINT `major_id` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`),
  CONSTRAINT `review_teacher_id1` FOREIGN KEY (`review_teacher_id1`) REFERENCES `teacher` (`teacher_id`),
  CONSTRAINT `review_teacher_id2` FOREIGN KEY (`review_teacher_id2`) REFERENCES `teacher` (`teacher_id`),
  CONSTRAINT `review_teacher_id3` FOREIGN KEY (`review_teacher_id3`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `teacher_id` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL DEFAULT '123456',
  `name` varchar(50) NOT NULL DEFAULT 'User',
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `head_portrait` varchar(1023) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `college_id` varchar(3) NOT NULL,
  `direction` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `teacher_id_UNIQUE` (`teacher_id`),
  KEY `college_id_idx` (`college_id`),
  CONSTRAINT `teacher_college_id` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (0,'2','223456','李四',NULL,NULL,NULL,'2020-06-17 14:00:41','1',NULL);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-17 22:16:43
