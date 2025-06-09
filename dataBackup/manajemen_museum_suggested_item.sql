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
-- Dumping data for table `suggested_item`
--

LOCK TABLES `suggested_item` WRITE;
/*!40000 ALTER TABLE `suggested_item` DISABLE KEYS */;
INSERT INTO `suggested_item` VALUES (1,'Vintage Compass','Old Attic, Main Street','Antiques','A brass compass from the early 20th century, still functional. Shows some signs of wear but has a beautiful patina.','2023-03-15','2025-06-08 01:12:10','2025-06-08 01:15:46','https://example.com/images/compass.jpg','accepted',NULL),(2,'Mysterious Orb','Beneath the Old Oak Tree, Whispering Woods','Curiosities','A smooth, dark purple orb emitting a faint, cool glow. Its origin is unknown.','2024-01-20','2025-06-08 01:12:10','2025-06-08 01:25:49','https://example.com/images/orb.jpg','accepted',NULL),(3,'Ancient Scroll Fragment','Hidden Chamber, Pyramid of Giza','Artifacts','A small, brittle piece of papyrus with undecipherable hieroglyphs. Possibly part of a larger ancient text.','2022-11-01','2025-06-08 01:12:10','2025-06-08 04:14:09','https://example.com/images/scroll.jpg','accepted',NULL),(4,'Unusual Seed Pod','Rainforest Floor, Amazon','Flora','A large, intricately patterned seed pod unlike any known species. It occasionally vibrates softly.','2023-09-10','2025-06-08 01:12:10','2025-06-08 01:12:10','https://example.com/images/seedpod.jpg','pending',NULL),(5,'Glimmering Stone','Riverbed, Crystal River','Minerals','A smooth, iridescent stone that shimmers with all colors of the rainbow when exposed to light.','2024-05-05','2025-06-08 01:12:10','2025-06-08 01:12:10','https://example.com/images/stone.jpg','pending',NULL),(6,'Ancient Coin','Riverbed, Old Town','Numismatics','A weathered bronze coin, possibly Roman era, with an illegible engraving.','2025-01-10','2025-06-08 01:15:06','2025-06-08 01:15:38','https://example.com/images/coin.jpg','accepted',NULL),(7,'Crystal Shard','Mountain Cave, Shadow Peaks','Minerals','A sharp, translucent crystal shard pulsating with a faint blue light.','2024-12-01','2025-06-08 01:15:06','2025-06-08 01:15:06','https://example.com/images/crystal.jpg','approved',NULL),(8,'123','123','12312','123','2000-10-10','2025-06-08 02:38:15','2025-06-08 02:38:15','https://google.com/','pending',NULL),(9,'123','123','123','123','1000-10-10','2025-06-08 02:44:04','2025-06-08 02:44:04','','pending','123123123123');
/*!40000 ALTER TABLE `suggested_item` ENABLE KEYS */;
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

-- Dump completed on 2025-06-09 11:03:26
