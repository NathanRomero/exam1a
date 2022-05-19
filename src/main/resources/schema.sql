/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
DROP TABLE IF EXISTS `cities`;

CREATE TABLE `cities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `country` varchar(30) COLLATE utf8mb4_spanish_ci NOT NULL,
  `lat` float NOT NULL,
  `lng` float NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_spanish_ci NOT NULL,
  `state` varchar(30) COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci