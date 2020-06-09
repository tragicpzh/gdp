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
  `id` varchar(20) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `admin_user_id` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` varchar(20) NOT NULL,
  `major_id` varchar(6) NOT NULL,
  `state` varchar(20) NOT NULL DEFAULT 'NO_SELECTION',
  `email` varchar(50) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `subject_id` bigint DEFAULT NULL,
  `cross_student_id` varchar(20) DEFAULT NULL,
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
  PRIMARY KEY (`id`),
  KEY `student_cross_student_id_idx` (`cross_student_id`),
  KEY `student_major_id` (`major_id`),
  CONSTRAINT `student_cross_student_id` FOREIGN KEY (`cross_student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `student_major_id` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`),
  CONSTRAINT `student_user_id` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_teacher_id` varchar(20) NOT NULL,
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
  KEY `subject_teacher_id0_idx` (`create_teacher_id`),
  KEY `subject_teacher_id1_idx` (`review_teacher_id1`),
  KEY `subject_teacher_id2_idx` (`review_teacher_id2`),
  KEY `subject_teacher_id3_idx` (`review_teacher_id3`),
  KEY `subject_major_id_idx` (`major_id`),
  CONSTRAINT `subject_major_id` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`),
  CONSTRAINT `subject_teacher_id0` FOREIGN KEY (`create_teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `subject_teacher_id1` FOREIGN KEY (`review_teacher_id1`) REFERENCES `teacher` (`id`),
  CONSTRAINT `subject_teacher_id2` FOREIGN KEY (`review_teacher_id2`) REFERENCES `teacher` (`id`),
  CONSTRAINT `subject_teacher_id3` FOREIGN KEY (`review_teacher_id3`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `id` varchar(20) NOT NULL,
  `college_id` varchar(3) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `direction` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `college_id_idx` (`college_id`),
  CONSTRAINT `teacher_college_id` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`),
  CONSTRAINT `teacher_user_id` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL DEFAULT '',
  `role` varchar(3) NOT NULL,
  `name` varchar(50) NOT NULL DEFAULT '',
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-09 17:22:31
