-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: carport
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `bills_of_material`
--

DROP TABLE IF EXISTS `bills_of_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bills_of_material` (
  `bom_id` int NOT NULL AUTO_INCREMENT,
  `material_id` int NOT NULL,
  `order_id` int NOT NULL,
  `quantity` int NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`bom_id`),
  KEY `fk_bills_of_material_order1_idx` (`order_id`),
  KEY `fk_bills_of_material_materials1_idx` (`material_id`),
  CONSTRAINT `fk_bills_of_material_materials1` FOREIGN KEY (`material_id`) REFERENCES `materials` (`material_id`),
  CONSTRAINT `fk_bills_of_material_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills_of_material`
--

LOCK TABLES `bills_of_material` WRITE;
/*!40000 ALTER TABLE `bills_of_material` DISABLE KEYS */;
/*!40000 ALTER TABLE `bills_of_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Træ'),(2,'Beslag & Skruer'),(3,'Tag - Fladt'),(4,'Tag - Rejst'),(5,'Tag - Rejst Ryg');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry`
--

DROP TABLE IF EXISTS `inquiry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry` (
  `inquiry_id` int NOT NULL AUTO_INCREMENT,
  `carp_width` int NOT NULL,
  `carp_ length` int NOT NULL,
  `roof_type` varchar(45) NOT NULL,
  `roof_slope` int NOT NULL DEFAULT '0',
  `shed_width` int NOT NULL DEFAULT '0',
  `shed_length` int NOT NULL DEFAULT '0',
  `timestamp` timestamp NOT NULL,
  PRIMARY KEY (`inquiry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry`
--

LOCK TABLES `inquiry` WRITE;
/*!40000 ALTER TABLE `inquiry` DISABLE KEYS */;
/*!40000 ALTER TABLE `inquiry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(200) NOT NULL,
  `height` int DEFAULT NULL,
  `width` int DEFAULT NULL,
  `length` int DEFAULT NULL,
  `unit` varchar(45) NOT NULL,
  `category_id` int NOT NULL,
  `angle` int DEFAULT NULL,
  `roll_length` int DEFAULT NULL,
  `amount_in_box` int DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`material_id`),
  KEY `fk_materials_category1_idx` (`category_id`),
  CONSTRAINT `fk_materials_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'25x150mm trykimp. Bræt',25,150,4800,'Stk',1,NULL,NULL,NULL,35),(2,'25x150mm. trykimp. Bræt',25,150,5400,'Stk',1,NULL,NULL,NULL,40),(3,'25x150mm. trykimp. Bræt',25,150,6000,'Stk',1,NULL,NULL,NULL,45),(4,'97x97 mm. trykimp. Stolpe',97,97,3000,'Stk',1,NULL,NULL,NULL,65),(5,'45x195 spærtræ ubh.',45,195,4800,'Stk',1,NULL,NULL,NULL,32.75),(6,'45x95 Reglar ubh.',45,95,2400,'Stk',1,NULL,NULL,NULL,22),(7,'45x95 Reglar ubh.',45,95,3600,'Stk',1,NULL,NULL,NULL,27.5),(8,'19x100mm. trykimp. Bræt',19,100,4800,'Stk',1,NULL,NULL,NULL,33),(9,'19x100mm. trykimp. Bræt',19,100,2400,'Stk',1,NULL,NULL,NULL,24),(10,'19x100mm. trykimp. Bræt',19,100,2100,'Stk',1,NULL,NULL,NULL,21),(11,'25x50 mm. trykimp. Bræt',25,50,5400,'Stk',1,NULL,NULL,NULL,37),(12,'38x73 mm. taglægte T1',38,73,5400,'Stk',1,NULL,NULL,NULL,39),(13,'38x73 mm. taglægte T1',38,73,4200,'Stk',1,NULL,NULL,NULL,36.5),(14,'færdigskåret (byg-selv spær)',0,0,0,'Sæt',1,NULL,NULL,NULL,650),(15,'universal 190 mm højre',0,0,0,'Stk',2,NULL,NULL,NULL,2.25),(16,'universal 190 mm venstre',0,0,0,'Stk',2,NULL,NULL,NULL,2.25),(17,'Stalddørsgreb 50x75',500,750,0,'Sæt',2,NULL,NULL,NULL,85),(18,'T-hængsel 390mm',0,390,0,'Stk',2,NULL,NULL,NULL,55.85),(19,'Vinkelbeslag',0,0,0,'Stk',2,NULL,NULL,NULL,12),(20,'4,5x60mm. Skruer (200 stk)',0,0,0,'Pakke',2,NULL,NULL,200,115),(21,'5,0x40mm. Beslagskruer (250 stk)',0,0,0,'Pakke',2,NULL,NULL,250,125),(22,'plastmo bundskruer (200 stk)',0,0,0,'Pakke',2,NULL,NULL,200,85),(23,'Hulbånd 1x20mm (10 mtr.)',1,20,10000,'Rulle',2,NULL,10,NULL,18.77),(24,'Bræddebolt 10x120 mm.',10,120,0,'Stk',2,NULL,NULL,NULL,3.75),(25,'Firkantskiver 40x40x11mm',40,40,11,'Stk',2,NULL,NULL,NULL,5.25),(26,'4,5x70mm. skruer (400 stk)',0,0,0,'Pakke',2,NULL,NULL,400,185),(27,'4,5x50mm Skruer (300 stk)',0,0,0,'Pakke',2,NULL,NULL,300,167.75),(28,'Plastmo Ecolite (Blåtonet)',0,1090,6000,'Stk',3,NULL,NULL,NULL,145),(29,'Plastmo Ecolite (Blåtonet)',0,1090,3600,'Stk',3,NULL,NULL,NULL,105),(30,'B & C Toplægte holder',0,0,0,'Stk',1,NULL,NULL,NULL,25),(31,'B & C Rygstensbeslag',0,0,0,'Stk',1,NULL,NULL,NULL,8),(32,'B & C tagstens binder/nakkekrog (kombi)',0,0,0,'Pakke',1,NULL,NULL,NULL,18.75),(33,'B & C Dobbelt Tagsten (Sort)',0,300,420,'Stk',4,NULL,NULL,NULL,15),(34,'B & C Dobbelt Tagsten (Grå)',0,310,390,'Stk',4,NULL,NULL,NULL,15),(35,'Eternit Tagsten(Teglrød)',0,1100,570,'Stk',4,NULL,NULL,NULL,18),(36,'B & C Dobbelt Tagsten (Rød)',0,300,420,'Stk',4,NULL,NULL,NULL,15),(37,'B & C Dobbelt Tagsten (Blå)',0,300,420,'Stk',4,NULL,NULL,NULL,15),(38,'B & C Dobbelt Tagsten (Sortblå)',0,310,390,'Stk',4,NULL,NULL,NULL,15),(39,'B & C Dobbelt Tagsten (Sunlux)',0,865,2000,'Stk',4,NULL,NULL,NULL,20),(40,'B & C Rygsten (Sort)',0,160,850,'Stk',5,NULL,NULL,NULL,20),(41,'B & C Rygsten Tagsten (Grå)',0,140,400,'Stk',5,NULL,NULL,NULL,20),(42,'Eternit Rygsten (Teglrød)',0,180,1200,'Stk',5,NULL,NULL,NULL,22),(43,'B & C Rygsten (Rød)',0,160,850,'Stk',5,NULL,NULL,NULL,23),(44,'B & C Rygsten (Blå)',0,160,850,'Stk',5,NULL,NULL,NULL,20),(45,'B & C Rygsten (Sortblå)',0,140,400,'Stk',5,NULL,NULL,NULL,20),(46,'B & C Rygsten (Sunlux)',0,140,400,'Stk',5,NULL,NULL,NULL,25),(47,'Tagpap',0,100,5400,'Stk',3,NULL,NULL,NULL,85),(48,'Tagpap',0,100,3600,'Stk',3,NULL,NULL,NULL,62.5),(49,'Træplader (Til tagpap)',0,0,0,'Pakke',1,NULL,NULL,NULL,1500),(50,'30x200mm alm. planke (Eg)',30,200,3600,'Stk',1,NULL,NULL,NULL,55),(51,'30x250mm alm. planke (Eg)',30,250,4800,'Stk',1,NULL,NULL,NULL,75),(52,'25x200mm alm. Bræt (Birk)',25,200,2400,'Stk',1,NULL,NULL,NULL,35),(53,'25x200mm alm. Bræt (Birk)',25,200,3200,'Stk',1,NULL,NULL,NULL,47.5),(54,'45x195 spærtræ ubh.',45,195,6000,'Stk',1,NULL,NULL,NULL,45),(55,'25x200 mm. trykimp. Bræt (Birk)',25,200,3600,'Stk',1,NULL,NULL,NULL,37.25),(56,'25x200 mm. trykimp. Bræt (Birk)',25,200,5400,'Stk',1,NULL,NULL,NULL,42.75),(57,'25x125 mm. trykimp. Bræt',25,125,3600,'Stk',1,NULL,NULL,NULL,36),(58,'25x125 mm. trykimp. Bræt',25,125,5400,'Stk',1,NULL,NULL,NULL,39.95),(59,'19x100mm. trykimp. Bræt',19,100,5400,'Stk',1,NULL,NULL,NULL,37),(60,'19x100mm. trykimp. Bræt',19,100,3600,'Stk',1,NULL,NULL,NULL,28),(61,'Stål',0,1090,6000,'Stk',3,NULL,NULL,NULL,145),(62,'Stål',0,1090,3600,'Stk',3,NULL,NULL,NULL,105);
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `request_id` int NOT NULL,
  `drawing` varchar(45) DEFAULT NULL,
  `cost_price` double NOT NULL,
  `final_price` double NOT NULL,
  `status_id` int NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`),
  KEY `fk_order_user1_idx` (`user_id`),
  KEY `fk_order_request1_idx` (`request_id`),
  KEY `fk_order_order_status1_idx` (`status_id`),
  CONSTRAINT `fk_order_order_status1` FOREIGN KEY (`status_id`) REFERENCES `order_status` (`status_id`),
  CONSTRAINT `fk_order_request1` FOREIGN KEY (`request_id`) REFERENCES `inquiry` (`inquiry_id`),
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `status_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (1,'Venter'),(2,'Færdig');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'bruger'),(2,'administrator');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role_id` int NOT NULL,
  `phone_no` int NOT NULL,
  `adresse` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_user_role_idx` (`role_id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Bo Bobsen','b@b.dk','1234',2,89898787,'adresse1'),(2,'Ib Ibsen','i@i.dk','1234',1,65656767,'adresse2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'carport'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-11 11:26:27
