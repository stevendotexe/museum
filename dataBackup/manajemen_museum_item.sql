-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: musem-database-timunseum.j.aivencloud.com    Database: manajemen_museum
-- ------------------------------------------------------
-- Server version	8.0.35

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'cb331058-4056-11f0-8b3d-862ccfb07967:1-106';

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (2,'Velociraptor','London Underground Tunnel','Prehistoric Animals','This is a bone of a dead prehistoric animal.',NULL,'2025-06-04 14:58:02','2025-06-04 14:58:02',0,NULL),(3,'Ancient Vase','Egyptian Pyramid','Artifacts','A beautifully preserved ceramic vase from ancient Egypt',NULL,'2025-06-04 15:03:45','2025-06-06 01:06:17',0,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmMxc3mUozk1GW3CP5wE6DXfm-7Q6ozYXYuA&s'),(4,'Viking Sword','Norwegian Fjord','Weapons','An iron sword with intricate Norse engravings',NULL,'2025-06-04 15:03:45','2025-06-06 01:06:17',0,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXW7tji2hdP5k6vgqpl6IdgQ_6L3CvZfLN1A&s'),(5,'Mayan Calendar','Chichen Itza','Cultural Items','Stone calendar depicting Mayan time cycles',NULL,'2025-06-04 15:03:45','2025-06-08 01:18:55',1,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlA4i8PxcfGn-6283vJrle2403XUdyAitSKA&s'),(6,'T-Rex Tooth','Montana Badlands','Prehistoric Animals','A fossilized tooth from a Tyrannosaurus Rex',NULL,'2025-06-04 15:03:45','2025-06-06 01:06:17',0,'https://www.skullsunlimited.com/cdn/shop/products/T-Rex-Tooth--Main__TR-02__1_1200x1200.jpg?v=1695925707'),(7,'Roman Coin','Pompeii Ruins','Currency','A silver denarius from the Roman Empire',NULL,'2025-06-04 15:03:45','2025-06-08 01:19:03',1,'https://shop.getty.edu/cdn/shop/products/ROM_02_obv.jpg?v=1628623622'),(8,'Samurai Armor','Kyoto Temple','Armor','Complete set of traditional Japanese warrior armor',NULL,'2025-06-04 15:03:45','2025-06-04 15:03:45',0,NULL),(9,'Stone Tools','African Savanna','Tools','Primitive tools used by early human ancestors',NULL,'2025-06-04 15:03:46','2025-06-06 01:06:16',0,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKA2wJQscWnp1TS8prHn1lXeyAQzNBqe4ofg&s'),(10,'Bronze Mirror','Chinese Tomb','Artifacts','Ancient bronze mirror with detailed patterns',NULL,'2025-06-04 15:03:46','2025-06-06 01:06:17',0,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDGptkrCis4GmfpXnQmcCa-YJyxLMZVg9Mcw&s'),(11,'Aztec Mask','Mexican Temple','Ceremonial Items','Golden mask used in ancient Aztec ceremonies',NULL,'2025-06-04 15:03:46','2025-06-06 01:06:17',0,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmUlKa86MafRwYSfXfU7XFdzFo9JyKZJmRZA&s');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-09 11:03:30
