-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: nyam_road
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `article_comment`
--

DROP TABLE IF EXISTS `article_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL,
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `article_comment_user_FK` (`user_id`),
  KEY `article_comment_article_master_FK` (`article_id`),
  CONSTRAINT `article_comment_article_master_FK` FOREIGN KEY (`article_id`) REFERENCES `article_master` (`id`),
  CONSTRAINT `article_comment_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_comment`
--

LOCK TABLES `article_comment` WRITE;
/*!40000 ALTER TABLE `article_comment` DISABLE KEYS */;
INSERT INTO `article_comment` VALUES (1,15,'jsonsvalue','ㅋㅋ','Y','2025-05-26 12:16:31','2025-05-26 12:16:31'),(2,15,'jsonsvalue','네이마르 일식 좋아하누\n','Y','2025-05-26 12:18:11','2025-05-26 12:18:11'),(3,51,'jsonsvalue','ㅎ','Y','2025-05-26 12:21:19','2025-05-26 12:21:19'),(4,51,'jsonsvalue','????','Y','2025-05-26 12:21:29','2025-05-26 12:21:29'),(5,15,'jsonsvalue','???','Y','2025-05-26 12:21:49','2025-05-26 12:21:49'),(6,15,'jsonsvalue','아니 아까는 분명 위에 있었는데?','Y','2025-05-26 12:23:02','2025-05-26 12:23:02');
/*!40000 ALTER TABLE `article_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_detail`
--

DROP TABLE IF EXISTS `article_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL,
  `order` int NOT NULL,
  `image_id` int NOT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `article_detail_article_master_FK` (`article_id`),
  KEY `article_detail_image_FK` (`image_id`),
  CONSTRAINT `article_detail_article_master_FK` FOREIGN KEY (`article_id`) REFERENCES `article_master` (`id`),
  CONSTRAINT `article_detail_image_FK` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_detail`
--

LOCK TABLES `article_detail` WRITE;
/*!40000 ALTER TABLE `article_detail` DISABLE KEYS */;
INSERT INTO `article_detail` VALUES (4,9,1,125,'ㅁㄴㅇㄻㄴㅇㄻㄴㅇㄹ','Y','2025-05-23 16:17:33','2025-05-23 16:17:33'),(5,9,2,126,'ㄴㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹ','Y','2025-05-23 16:17:33','2025-05-23 16:17:33'),(6,10,1,129,'ㅇ\nㅇ\n\nㅇ\nㅇ\nㅇ\nㅇ\n\nㅇ\nㅇ\nㅇ\n\nㅇ\nㅇ\n','Y','2025-05-23 17:03:16','2025-05-23 17:03:16'),(7,10,2,128,'ㄹ\nㄹ\n\nㄹ\nㄹ\n','Y','2025-05-23 17:03:16','2025-05-23 17:03:16'),(8,10,3,130,'ㅁ\nㅁ\nㅁ\nㅁ\n\nㅁ\n','Y','2025-05-23 17:03:16','2025-05-23 17:03:16'),(9,11,1,100,'스파게티 좋아','Y','2025-05-25 21:49:26','2025-05-25 21:49:26'),(10,12,1,99,'뭘까이건','Y','2025-05-25 21:49:26','2025-05-25 21:49:26'),(11,13,1,98,'하하','Y','2025-05-25 21:49:26','2025-05-25 21:49:26'),(12,15,1,32,'큰일났네','Y','2025-05-25 21:49:26','2025-05-25 21:49:26'),(13,11,2,35,'스파게티진짜로좋아','Y','2025-05-25 21:49:26','2025-05-25 21:49:26');
/*!40000 ALTER TABLE `article_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_like`
--

DROP TABLE IF EXISTS `article_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_like` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL,
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `article_like_article_master_FK` (`article_id`),
  KEY `article_like_user_FK` (`user_id`),
  CONSTRAINT `article_like_article_master_FK` FOREIGN KEY (`article_id`) REFERENCES `article_master` (`id`),
  CONSTRAINT `article_like_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_like`
--

LOCK TABLES `article_like` WRITE;
/*!40000 ALTER TABLE `article_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_master`
--

DROP TABLE IF EXISTS `article_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_master` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(40) NOT NULL,
  `type` enum('eat','recipe') NOT NULL,
  `image_id` int NOT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ingredient` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `article_master_image_FK` (`image_id`),
  KEY `article_master_user_FK` (`user_id`),
  CONSTRAINT `article_master_image_FK` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `article_master_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_master`
--

LOCK TABLES `article_master` WRITE;
/*!40000 ALTER TABLE `article_master` DISABLE KEYS */;
INSERT INTO `article_master` VALUES (6,'hell','eat',121,'11111','','Y','2025-05-23 15:49:33','2025-05-23 15:49:33'),(7,'hell','eat',122,'2222','','Y','2025-05-23 15:49:44','2025-05-23 15:49:44'),(8,'hell','recipe',123,'33333','','Y','2025-05-23 15:49:58','2025-05-23 15:49:58'),(9,'hell','recipe',124,'내용내용내용내용내용','','Y','2025-05-23 16:17:33','2025-05-23 16:17:33'),(10,'hell','eat',127,'내용\n내용\n내용\n내용','','Y','2025-05-23 17:03:16','2025-05-23 17:03:16'),(11,'jsonsvalue','eat',128,'국밥',NULL,'Y','2025-05-25 20:51:24','2025-05-25 20:51:24'),(12,'jsonsvalue','recipe',129,'스파게티',NULL,'Y','2025-05-25 20:51:55','2025-05-25 20:51:55'),(13,'jsonsvalue','recipe',130,'김밥',NULL,'Y','2025-05-25 20:52:58','2025-05-25 20:52:58'),(15,'jsonsvalue','recipe',27,'일식',NULL,'Y','2025-05-25 20:53:45','2025-05-25 20:53:45'),(16,'jsonsvalue','recipe',28,'1',NULL,'Y','2025-05-25 23:19:15','2025-05-25 23:19:15'),(17,'jsonsvalue','recipe',12,'2',NULL,'Y','2025-05-25 23:19:15','2025-05-25 23:19:15'),(18,'jsonsvalue','recipe',13,'3',NULL,'Y','2025-05-25 23:19:15','2025-05-25 23:19:15'),(19,'jsonsvalue','recipe',14,'4',NULL,'Y','2025-05-25 23:19:15','2025-05-25 23:19:15'),(20,'jsonsvalue','recipe',15,'5',NULL,'Y','2025-05-25 23:19:15','2025-05-25 23:19:15'),(21,'jsonsvalue','recipe',16,'6',NULL,'Y','2025-05-25 23:19:15','2025-05-25 23:19:15'),(22,'jsonsvalue','recipe',17,'7',NULL,'Y','2025-05-25 23:19:15','2025-05-25 23:19:15'),(23,'jsonsvalue','recipe',18,'8',NULL,'Y','2025-05-25 23:19:15','2025-05-25 23:19:15'),(24,'jsonsvalue','recipe',19,'9',NULL,'Y','2025-05-25 23:19:15','2025-05-25 23:19:15'),(25,'jsonsvalue','recipe',20,'11',NULL,'Y','2025-05-25 23:19:15','2025-05-25 23:19:15'),(26,'jsonsvalue','recipe',32,'10',NULL,'Y','2025-05-25 23:19:15','2025-05-25 23:19:15'),(27,'jsonsvalue','recipe',21,'12',NULL,'Y','2025-05-25 23:26:59','2025-05-25 23:26:59'),(28,'jsonsvalue','recipe',22,'13',NULL,'Y','2025-05-25 23:26:59','2025-05-25 23:26:59'),(29,'jsonsvalue','recipe',23,'14',NULL,'Y','2025-05-25 23:26:59','2025-05-25 23:26:59'),(30,'jsonsvalue','recipe',24,'15',NULL,'Y','2025-05-25 23:26:59','2025-05-25 23:26:59'),(31,'jsonsvalue','recipe',25,'16',NULL,'Y','2025-05-25 23:26:59','2025-05-25 23:26:59'),(32,'jsonsvalue','recipe',26,'17',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(33,'jsonsvalue','recipe',27,'18',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(34,'jsonsvalue','recipe',28,'19',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(35,'jsonsvalue','recipe',29,'20',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(36,'jsonsvalue','recipe',30,'21',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(37,'jsonsvalue','recipe',31,'22',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(38,'jsonsvalue','recipe',32,'23',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(39,'jsonsvalue','recipe',33,'24',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(40,'jsonsvalue','recipe',34,'25',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(41,'jsonsvalue','recipe',35,'26',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(42,'jsonsvalue','recipe',36,'27',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(43,'jsonsvalue','recipe',37,'28',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(44,'jsonsvalue','recipe',38,'29',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(45,'jsonsvalue','recipe',39,'30',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(46,'jsonsvalue','recipe',40,'31',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(47,'jsonsvalue','recipe',41,'32',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(48,'jsonsvalue','recipe',42,'33',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(49,'jsonsvalue','recipe',43,'34',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(50,'jsonsvalue','recipe',44,'35',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00'),(51,'jsonsvalue','eat',35,'26',NULL,'Y','2025-05-25 23:27:00','2025-05-25 23:27:00');
/*!40000 ALTER TABLE `article_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(40) NOT NULL,
  `follow_id` varchar(40) NOT NULL,
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `follow_idx_user_id` (`user_id`),
  KEY `follow_idx_follow_id` (`follow_id`),
  CONSTRAINT `follow_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (5,'json123','jsonsvalue','Y','2025-05-25 19:38:56','2025-05-25 19:38:56'),(6,'hell','jsonsvalue','Y','2025-05-25 19:38:56','2025-05-25 19:38:56'),(7,'test','jsonsvalue','Y','2025-05-25 19:38:56','2025-05-25 19:38:56'),(8,'hell1','jsonsvalue','Y','2025-05-25 19:38:56','2025-05-25 19:38:56'),(9,'hell123','jsonsvalue','Y','2025-05-25 19:38:56','2025-05-25 19:38:56'),(10,'hell1357','jsonsvalue','Y','2025-05-25 19:38:56','2025-05-25 19:38:56'),(11,'jsonsvalue','json123','Y','2025-05-25 19:39:42','2025-05-25 19:39:42'),(12,'jsonsvalue','solvinsRecipe','Y','2025-05-25 19:40:11','2025-05-25 19:40:11'),(28,'jsonsvalue','hell','Y','2025-05-26 16:50:05','2025-05-26 16:50:05'),(59,'jsonsvalue','undefined','Y','2025-05-26 17:51:14','2025-05-26 17:51:14'),(60,'jsonsvalue','undefined','Y','2025-05-26 17:51:16','2025-05-26 17:51:16'),(61,'jsonsvalue','undefined','Y','2025-05-26 17:51:17','2025-05-26 17:51:17'),(62,'jsonsvalue','undefined','Y','2025-05-26 17:51:17','2025-05-26 17:51:17'),(63,'jsonsvalue','undefined','Y','2025-05-26 17:51:18','2025-05-26 17:51:18'),(64,'jsonsvalue','undefined','Y','2025-05-26 17:51:18','2025-05-26 17:51:18'),(65,'jsonsvalue','undefined','Y','2025-05-26 17:51:18','2025-05-26 17:51:18'),(66,'jsonsvalue','undefined','Y','2025-05-26 17:51:19','2025-05-26 17:51:19'),(67,'jsonsvalue','undefined','Y','2025-05-26 17:51:19','2025-05-26 17:51:19'),(68,'jsonsvalue','undefined','Y','2025-05-26 17:51:19','2025-05-26 17:51:19'),(69,'jsonsvalue','undefined','Y','2025-05-26 17:51:19','2025-05-26 17:51:19'),(70,'jsonsvalue','undefined','Y','2025-05-26 17:51:19','2025-05-26 17:51:19');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `org_file` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (12,'94e8167b-aa2e-4496-8adc-2cdc78978736.png','Y','2025-05-22 16:57:09','2025-05-22 16:57:09'),(13,'28465e00-7d7e-4fee-8671-31f1951a215e.png','Y','2025-05-22 16:57:14','2025-05-22 16:57:14'),(14,'02dd41a6-3ad5-4a33-a2b7-9266015c6fd3.png','Y','2025-05-22 16:57:22','2025-05-22 16:57:22'),(15,'5f76c876-9659-4ab8-b0f9-1444ddd3ded0.png','Y','2025-05-22 17:02:54','2025-05-22 17:02:54'),(16,'e6a3e0e1-980d-40fc-b82f-f7e6500eac62.png','Y','2025-05-22 17:03:24','2025-05-22 17:03:24'),(17,'769415eb-5c33-422e-8213-46d66c863e96.png','Y','2025-05-22 17:09:41','2025-05-22 17:09:41'),(18,'7fad9d49-8765-4b1f-a627-7c011f4f68f5.png','Y','2025-05-22 17:15:07','2025-05-22 17:15:07'),(19,'86ca9dae-7146-448a-89eb-2d093886583f.png','Y','2025-05-22 17:16:17','2025-05-22 17:16:17'),(20,'950fe47a-ed1a-4975-be66-c04799d66aa3.png','Y','2025-05-22 17:16:51','2025-05-22 17:16:51'),(21,'ca6a883f-496e-4d60-a078-93bbe95d7dd2.png','Y','2025-05-22 17:18:09','2025-05-22 17:18:09'),(22,'951601e5-4abf-4b07-89c7-c97b191448db.png','Y','2025-05-22 17:18:33','2025-05-22 17:18:33'),(23,'a78ff259-e14a-4c70-a7c9-732f2a79b87f.png','Y','2025-05-22 17:18:54','2025-05-22 17:18:54'),(24,'bb681263-1232-43f3-bbe5-da5181971a55.png','Y','2025-05-22 17:19:17','2025-05-22 17:19:17'),(25,'31979a89-3a72-4636-b21b-85c6841582f0.png','Y','2025-05-22 17:19:47','2025-05-22 17:19:47'),(26,'f494afc8-6824-4c66-b54f-585450390d3c.png','Y','2025-05-22 17:21:33','2025-05-22 17:21:33'),(27,'ebc3ba9a-268a-42d0-8a75-b07893a9d952.png','Y','2025-05-22 17:24:56','2025-05-22 17:24:56'),(28,'ae05f392-5a1f-4842-b5c5-6406b974cf1f.png','Y','2025-05-22 17:28:52','2025-05-22 17:28:52'),(29,'fe2e4a9b-3c03-4872-96e1-91ce48a46b43.png','Y','2025-05-22 17:33:15','2025-05-22 17:33:15'),(30,'c3b43d5b-04b0-47a7-a421-24c6a0df6a2d.png','Y','2025-05-22 17:37:35','2025-05-22 17:37:35'),(31,'52bd48c0-9d41-4153-9b4c-2f1169625587.png','Y','2025-05-22 17:39:10','2025-05-22 17:39:10'),(32,'24d307a5-c1bd-463b-9fc6-0bcad8b12e13.png','Y','2025-05-22 17:54:04','2025-05-22 17:54:04'),(33,'99e8c998-a393-456c-ae6e-a1d01bf4e2c0.png','Y','2025-05-22 17:54:25','2025-05-22 17:54:25'),(34,'68550019-332a-4b0a-a7c6-8916d6f371c1.png','Y','2025-05-22 17:55:25','2025-05-22 17:55:25'),(35,'fdc8dbd3-9a10-43d4-b854-0ebcd9db63f5.png','Y','2025-05-22 17:57:33','2025-05-22 17:57:33'),(36,'0826907d-9440-48c3-b0c3-7616c1273cfb.png','Y','2025-05-23 08:28:10','2025-05-23 08:28:10'),(37,'f351dfa8-346c-4404-9f79-6a49a418a7f3.png','Y','2025-05-23 08:37:40','2025-05-23 08:37:40'),(38,'5cbe1ba8-077a-45ea-a8ea-529d7f6f5916.png','Y','2025-05-23 08:39:53','2025-05-23 08:39:53'),(39,'f0a3b8ed-6461-4dd9-b354-ecb470d277b9.png','Y','2025-05-23 08:40:28','2025-05-23 08:40:28'),(40,'e8a52685-e839-4bd0-a876-75b4a3907f33.png','Y','2025-05-23 08:43:12','2025-05-23 08:43:12'),(41,'f974c194-db75-4fab-875a-86ea867507fe.png','Y','2025-05-23 08:45:02','2025-05-23 08:45:02'),(42,'c0b7b78a-9a13-4f17-b254-c57c767445f8.png','Y','2025-05-23 08:48:03','2025-05-23 08:48:03'),(43,'5463d589-1138-46d1-9c40-b586252ef437.png','Y','2025-05-23 09:10:58','2025-05-23 09:10:58'),(44,'1f879959-0610-47a4-90ec-fd5c2842e925.png','Y','2025-05-23 09:19:35','2025-05-23 09:19:35'),(45,'82b2327e-e891-446f-aae3-8353aefa9633.png','Y','2025-05-23 09:58:25','2025-05-23 09:58:25'),(46,'2fc43342-008c-4e27-b5db-91eb7888bfcb.png','Y','2025-05-23 09:58:40','2025-05-23 09:58:40'),(47,'afe97194-5952-425a-9224-66b0aede4fd7.png','Y','2025-05-23 09:58:43','2025-05-23 09:58:43'),(48,'05df913b-97d9-48bb-82e5-3a71afcd51a9.png','Y','2025-05-23 09:59:26','2025-05-23 09:59:26'),(49,'215ea4de-ec6e-4d5f-8a0a-8888ba68482e.png','Y','2025-05-23 09:59:34','2025-05-23 09:59:34'),(50,'f1b685c0-1ee5-4643-9c04-ce329129e641.png','Y','2025-05-23 10:31:56','2025-05-23 10:31:56'),(51,'96562979-b4c2-4424-9298-8c5d0d0e2533.png','Y','2025-05-23 10:36:28','2025-05-23 10:36:28'),(52,'1253c903-a8af-4298-bd05-79d6ed52d12d.png','Y','2025-05-23 10:36:36','2025-05-23 10:36:36'),(53,'dee0d02b-a816-424a-a6cf-d45421fda2d3.png','Y','2025-05-23 10:40:07','2025-05-23 10:40:07'),(54,'122e2572-4a0b-41f6-9234-b742eaa48f8b.png','Y','2025-05-23 10:40:28','2025-05-23 10:40:28'),(55,'3f8330ac-a530-4671-a835-01611fcbd0be.png','Y','2025-05-23 10:40:37','2025-05-23 10:40:37'),(56,'c84f7f07-f5fb-44a3-87e3-6221af05662e.png','Y','2025-05-23 10:43:56','2025-05-23 10:43:56'),(57,'cb72c489-df6a-45d7-8579-d4568b43df08.png','Y','2025-05-23 10:44:00','2025-05-23 10:44:00'),(58,'98f2bdca-e340-472d-81c3-76d2a7034d89.png','Y','2025-05-23 10:44:03','2025-05-23 10:44:03'),(59,'8fea53c9-4343-4191-ac78-b3526fd7d62b.png','Y','2025-05-23 10:44:05','2025-05-23 10:44:05'),(60,'2a789703-31b5-4688-b7da-b0c19e7f4a42.png','Y','2025-05-23 10:44:16','2025-05-23 10:44:16'),(61,'999e51eb-a110-41d6-aa98-0951c9794da4.png','Y','2025-05-23 10:47:23','2025-05-23 10:47:23'),(62,'4a8a1b72-92c8-47cd-b31d-b1d2960fbbe1.png','Y','2025-05-23 10:47:26','2025-05-23 10:47:26'),(63,'38075901-fc0e-4247-89ee-3653e11e3e63.png','Y','2025-05-23 10:48:08','2025-05-23 10:48:08'),(64,'19de1ec2-a4be-4557-9f53-76df74662865.png','Y','2025-05-23 10:48:11','2025-05-23 10:48:11'),(65,'c0515dcc-02b5-487c-86bb-bd7a2a344d24.png','Y','2025-05-23 10:54:56','2025-05-23 10:54:56'),(66,'a1d0f8bf-7e25-4590-a452-f0fb4ee81c8d.png','Y','2025-05-23 10:55:16','2025-05-23 10:55:16'),(67,'2f61faea-5bf8-447a-bc40-9b632e684567.png','Y','2025-05-23 10:56:16','2025-05-23 10:56:16'),(68,'2b12c3ab-0a48-4484-9c3f-6c31a20b4994.png','Y','2025-05-23 10:57:00','2025-05-23 10:57:00'),(69,'df84fe42-ec1f-4471-8387-a5874a7457a9.png','Y','2025-05-23 10:57:50','2025-05-23 10:57:50'),(70,'c7874e94-a0e7-4008-af39-d7608e0afcc7.png','Y','2025-05-23 10:57:59','2025-05-23 10:57:59'),(71,'4a781b59-2014-4007-8268-46f947146693.png','Y','2025-05-23 10:58:03','2025-05-23 10:58:03'),(72,'9954bc27-f473-4f83-9324-d5f9d1c0356f.png','Y','2025-05-23 10:58:06','2025-05-23 10:58:06'),(73,'4bab18fe-f47e-497b-9bf7-8e007a89ac79.png','Y','2025-05-23 10:58:23','2025-05-23 10:58:23'),(74,'796ccc52-5943-4dfa-9856-7dbe767648b5.png','Y','2025-05-23 10:58:59','2025-05-23 10:58:59'),(75,'9a06bbc4-205a-4dfd-9851-9a3e3e337247.png','Y','2025-05-23 10:59:04','2025-05-23 10:59:04'),(76,'77a272b1-6f4f-4f7d-bd65-9a0afbe992d2.png','Y','2025-05-23 11:00:23','2025-05-23 11:00:23'),(77,'ab025187-a912-4b8b-8562-61859e8a9fd7.png','Y','2025-05-23 11:02:34','2025-05-23 11:02:34'),(78,'bb649228-6354-41f3-ba65-edd4706b51e3.png','Y','2025-05-23 11:03:12','2025-05-23 11:03:12'),(79,'7cf7f6e1-f540-4554-bf26-a549bf222851.png','Y','2025-05-23 11:03:14','2025-05-23 11:03:14'),(80,'bc8fba3f-8174-4ecd-8a22-a1fc4e75cc0f.png','Y','2025-05-23 11:03:26','2025-05-23 11:03:26'),(81,'9bf08d1a-e152-4036-b5a2-b4541025d9ab.png','Y','2025-05-23 11:03:30','2025-05-23 11:03:30'),(82,'1b329b54-e416-4e79-b8d1-054744a43d0c.png','Y','2025-05-23 11:03:44','2025-05-23 11:03:44'),(83,'a301343b-7d81-423b-88d9-de987c8ba1ca.png','Y','2025-05-23 11:04:17','2025-05-23 11:04:17'),(84,'55d17b80-e65a-4753-83c5-8fd70c95f014.png','Y','2025-05-23 11:04:23','2025-05-23 11:04:23'),(85,'ef1e4229-7a63-4b8d-a64b-c347df0a9117.png','Y','2025-05-23 11:05:38','2025-05-23 11:05:38'),(86,'e7761456-aa41-4e1b-a05a-a1cc915687ed.png','Y','2025-05-23 11:05:42','2025-05-23 11:05:42'),(87,'3fbce070-216f-4586-a6bc-4076ee7f7a28.png','Y','2025-05-23 11:06:07','2025-05-23 11:06:07'),(88,'b5c0eb2e-9091-45eb-978a-8f0e88b7a605.png','Y','2025-05-23 11:08:23','2025-05-23 11:08:23'),(89,'7f816da7-4cd1-40b1-9294-ec1572ccc981.png','Y','2025-05-23 11:08:26','2025-05-23 11:08:26'),(90,'2511938a-78a8-447d-91a2-e403a24fcc7c.png','Y','2025-05-23 11:09:46','2025-05-23 11:09:46'),(91,'4464cc44-9426-4d61-b057-688134d58635.png','Y','2025-05-23 11:10:22','2025-05-23 11:10:22'),(92,'dc4374c4-205e-4338-b33e-7861fcba707b.png','Y','2025-05-23 11:11:03','2025-05-23 11:11:03'),(93,'72469d5d-2d84-4938-bb85-21db3d175a7c.png','Y','2025-05-23 11:13:00','2025-05-23 11:13:00'),(94,'f7537f0e-2c7f-4ebb-8e6a-4f6d8e784673.png','Y','2025-05-23 11:13:07','2025-05-23 11:13:07'),(95,'6e7717b3-6b2a-4e25-8ed1-71aba4c59d2e.png','Y','2025-05-23 11:13:24','2025-05-23 11:13:24'),(96,'e0df2f89-10fe-43c8-89dc-5f36dc01e766.png','Y','2025-05-23 11:13:28','2025-05-23 11:13:28'),(97,'87f1b311-9f8c-4bed-98af-0652b4996039.png','Y','2025-05-23 11:16:09','2025-05-23 11:16:09'),(98,'069a6644-0038-4328-8d69-637dc6cb2570.png','Y','2025-05-23 11:16:12','2025-05-23 11:16:12'),(99,'d4c40476-3d15-440c-914d-00a3cb5996f4.png','Y','2025-05-23 11:16:27','2025-05-23 11:16:27'),(100,'62eefd43-446c-40ea-8ad2-5779ac60f572.png','Y','2025-05-23 11:16:33','2025-05-23 11:16:33'),(101,'bd3eeaff-2d67-4167-9041-a89c05c998e6.png','Y','2025-05-23 11:17:12','2025-05-23 11:17:12'),(102,'a63abb31-cba1-43c8-b75a-1f33c7b60423.png','Y','2025-05-23 11:26:09','2025-05-23 11:26:09'),(103,'18d16a13-3225-4d1d-91c0-42552b3a5073.png','Y','2025-05-23 11:50:49','2025-05-23 11:50:49'),(104,'40fe51f3-45a9-4dee-802c-8a891fbf7cb5.png','Y','2025-05-23 11:51:05','2025-05-23 11:51:05'),(105,'8bc4ca7a-e7b7-48c9-a826-2012e1d52472.png','Y','2025-05-23 11:51:35','2025-05-23 11:51:35'),(106,'eade75f1-6f2c-49d2-b437-d1f50cac53f1.png','Y','2025-05-23 11:51:38','2025-05-23 11:51:38'),(107,'d9ecc7aa-1a78-4da6-9e39-53c8252f7b03.png','Y','2025-05-23 12:07:19','2025-05-23 12:07:19'),(108,'24c635f3-c95d-45c1-8ea8-ccab01252e4c.png','Y','2025-05-23 12:07:26','2025-05-23 12:07:26'),(109,'37b909a8-df64-47a5-b5cd-239998a0e675.png','Y','2025-05-23 12:08:01','2025-05-23 12:08:01'),(110,'ecb0ec5d-e262-49df-98f5-6fcb4e522e7b.png','Y','2025-05-23 12:08:04','2025-05-23 12:08:04'),(111,'dfe6df7a-2eae-465d-bca1-d35535884960.png','Y','2025-05-23 12:19:58','2025-05-23 12:19:58'),(112,'d32f46e2-75d2-4f32-80fb-02b9e582692e.png','Y','2025-05-23 12:20:02','2025-05-23 12:20:02'),(113,'bfcab553-d46b-4457-a160-bd48805693c6.png','Y','2025-05-23 12:21:10','2025-05-23 12:21:10'),(114,'3c1d877f-fe3b-4663-b7f4-80a780ed30a5.png','Y','2025-05-23 12:21:14','2025-05-23 12:21:14'),(115,'58225b6e-90b1-41b0-853c-301e20b95235.png','Y','2025-05-23 12:23:31','2025-05-23 12:23:31'),(116,'da8325cd-7e01-4eda-be86-08255027fa71.png','Y','2025-05-23 12:23:34','2025-05-23 12:23:34'),(117,'15852b8d-e36e-47b6-931a-0438f802c7d1.png','Y','2025-05-23 12:24:11','2025-05-23 12:24:11'),(118,'2fdb6861-4833-44ad-8416-ba1e0af46bc3.png','Y','2025-05-23 12:24:14','2025-05-23 12:24:14'),(119,'f13dcb19-f269-47e5-89d5-8138b04a845c.png','Y','2025-05-23 12:28:33','2025-05-23 12:28:33'),(120,'9e308b8c-3a8f-4318-be87-26ab16e76694.png','Y','2025-05-23 12:28:38','2025-05-23 12:28:38'),(121,'d2bdb182-dc47-488d-9122-d4b1593298b8.png','Y','2025-05-23 15:49:31','2025-05-23 15:49:31'),(122,'c0967638-ca36-4775-954c-c60ec13815f3.png','Y','2025-05-23 15:49:43','2025-05-23 15:49:43'),(123,'a720f59c-b8dd-46b9-a66d-627b3b0dd034.png','Y','2025-05-23 15:49:56','2025-05-23 15:49:56'),(124,'e30aeee1-6ba2-4b8a-b22f-5da427143e51.png','Y','2025-05-23 16:17:18','2025-05-23 16:17:18'),(125,'5855478d-2036-406a-8e8f-ed77f1ecddea.png','Y','2025-05-23 16:17:25','2025-05-23 16:17:25'),(126,'d40be5b2-0a2f-4fc0-b490-625a3a263386.png','Y','2025-05-23 16:17:28','2025-05-23 16:17:28'),(127,'7fa20954-bcb7-4538-abc6-f0313714a5e0.png','Y','2025-05-23 17:02:50','2025-05-23 17:02:50'),(128,'f1cb1cdf-1526-4c07-afee-a8e906a47e63.png','Y','2025-05-23 17:03:06','2025-05-23 17:03:06'),(129,'99fe9e19-a5a9-4fe9-9f49-7b1e28dc8b0d.png','Y','2025-05-23 17:03:12','2025-05-23 17:03:12'),(130,'ef90b063-a74b-48a1-834d-6e9be6626c3d.png','Y','2025-05-23 17:03:15','2025-05-23 17:03:15'),(131,'d5f7fa19-5c23-4d99-8b46-606b7b45f3d1.png','Y','2025-05-26 14:27:43','2025-05-26 14:27:43');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` enum('admin','user') DEFAULT 'user',
  `image_id` int DEFAULT NULL,
  `intro` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `feed_type` enum('rec','plc','all') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'rec',
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `user_image_FK` (`image_id`),
  CONSTRAINT `user_image_FK` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('bado','bad','bad@naver.com','$2a$10$D0k.NWLwS318Epugly1BMOygeAGcA7tUShvZcqP6PnGT7aKrkCei6','user',NULL,NULL,'rec','Y','2025-05-22 17:18:37','2025-05-22 17:18:37'),('goodo','good','good@naver.com','$2a$10$JSAcLbVC8hQytOZvpuGmIe.YtkiiyL2u1Ov3tnp2n7/7mG05FAgye','user',NULL,NULL,'rec','Y','2025-05-22 17:19:45','2025-05-22 17:19:45'),('hell','이재석','hellojaeseok1234@naver.com','$2a$10$Z9yVip41NXyyIWlOLdKJh.us9hUTGpKh.Tc7ups3.0X1qxk1oKQ2u','user',NULL,'변경사항이 있어도 잘 되나?!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!가즈아','all','Y','2025-05-22 17:13:45','2025-05-25 16:32:36'),('hell1','hello','hell@naver.com','$2a$10$ZsW5NeqQC02xBE0K1Cad7.COl331nzPZso.tjpxPHGu6IupdHavD.','user',NULL,NULL,'rec','Y','2025-05-22 17:16:23','2025-05-22 17:16:23'),('hell123','hello','hell1234@naver.com','$2a$10$OFTBboa7uLrjJAAMjpLCPe6ptphaEcgDyvuSjN.t4FZ4Bb1nmrRgy','user',NULL,NULL,'rec','Y','2025-05-22 17:16:56','2025-05-22 17:16:56'),('hell1357','hello','hellO@naver.com','$2a$10$4o3F.ffuHXfPjiXaQfcvCePHuX8taCGG0Gy255lBNvFC3bTfZrFZy','user',NULL,NULL,'rec','Y','2025-05-22 17:17:33','2025-05-22 17:17:33'),('hellno','hellono','hellno@naver.com','$2a$10$m1mCTH/zW4SO0HsMGfCGceUZaf8zygrzC8yMp3eGaeeQdEsa0rbmK','user',NULL,NULL,'rec','Y','2025-05-22 17:20:41','2025-05-22 17:20:41'),('json123','ㅁㄴㅇㄹ','hellojaeseok12@naver.com','$2a$10$Uc20w/cneL6cqbbqk9EsEOVR9oFgQH466NCh3NnpGgu1EqRwj8jd6','user',NULL,NULL,'rec','Y','2025-05-22 16:58:13','2025-05-22 16:58:13'),('jsonsvalue','이재석','hellojaeseok@naver.com','$2a$10$QhL7Fl0hmLrLwGc3rCyAV.jAk3FfOglY7hKA4mYl0F3y0BUtZNfSm','user',48,'이거 업데이트 됨?\nㅇㅇ 업데이트 됨','all','Y','2025-05-24 22:23:02','2025-05-26 17:19:18'),('jsonsvalues0','이재석','hellojaeseok123@naver.com','$2a$10$j5ofjLouYMKGOkLX1xTvbukUPXc2Q.iTvydWqcxdno4676QZP3L4.','user',NULL,NULL,'rec','Y','2025-05-22 16:53:40','2025-05-22 16:53:40'),('jsonsvaluess','jsons','hellojaeseok1@naver.com','$2a$10$RjclppY4qM0VZ8K8/FC0deVdfuIbL.kUYdb1YrbSeJ6PwakuLmVQG',NULL,NULL,NULL,'rec','Y','2025-05-22 16:21:04','2025-05-22 16:21:04'),('ohno','ohnooo','ohno@naver.com','$2a$10$DoyPX1B1I3Dk2bKYVzguAOfP2QRJUFrcyIbdpbxXlK4XfJaBLD.ba','user',NULL,NULL,'rec','Y','2025-05-22 17:21:14','2025-05-22 17:21:14'),('solvinsRecipes','solvins','solvins@naver.com','56789','user',NULL,'','rec','Y','2025-05-20 12:56:41','2025-05-20 12:56:41'),('solvinsRecipese','solvinse','solvinse@naver.com','567890','user',NULL,NULL,'rec','Y','2025-05-20 12:57:08','2025-05-20 12:57:08'),('test','test','test','$2a$10$2BmO3c5lRaDnpmZP54HzNelYm4lFk5GT2wEDk4mEpgSuc0Ya7Iwgq','user',NULL,NULL,'rec','Y','2025-05-23 14:55:48','2025-05-23 14:55:48');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'nyam_road'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-26 17:57:57
