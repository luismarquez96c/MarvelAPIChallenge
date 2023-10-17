DROP TABLE IF EXISTS `granted_permission`;


CREATE TABLE `granted_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `permission_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKevk2nql3oc417rlr4mt8gsjpx` (`permission_id`),
  KEY `FKm4v6hvxf7972y0liwhbfe7x6a` (`role_id`),
  CONSTRAINT `FKevk2nql3oc417rlr4mt8gsjpx` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `FKm4v6hvxf7972y0liwhbfe7x6a` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



LOCK TABLES `granted_permission` WRITE;

INSERT INTO `granted_permission` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,1,2),(7,2,2),(8,3,2),(9,4,2),(10,5,2),(11,6,2),(12,7,2);

UNLOCK TABLES;


DROP TABLE IF EXISTS `permission`;


CREATE TABLE `permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



LOCK TABLES `permission` WRITE;

INSERT INTO `permission` VALUES (1,'character:read-all'),(2,'character:read-detail'),(3,'comic:read-all'),(4,'comic:read-by-id'),(5,'user-interaction:read-my-interactions'),(6,'user-interaction:read-all'),(7,'user-interaction:read-by-username');

UNLOCK TABLES;


DROP TABLE IF EXISTS `role`;


CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` enum('AUDITOR','CUSTOMER') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



LOCK TABLES `role` WRITE;

INSERT INTO `role` VALUES (1,'CUSTOMER'),(2,'AUDITOR');

UNLOCK TABLES;


DROP TABLE IF EXISTS `user`;


CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `credentials_expired` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



LOCK TABLES `user` WRITE;

INSERT INTO `user` VALUES (1,_binary '\0',_binary '\0',_binary '\0',_binary '','$2a$10$ZK9Y2UfgLivxy37YPiAQWOLMsujY3XJHkRXGYz4Cv7trCZLPLqudi','lmarquez',1),(2,_binary '\0',_binary '\0',_binary '\0',_binary '','$2a$10$juOXaule5VGy1KogEFCu5eFBSmZ54Wv0x1iIbaN7TpcouueD1epKy','gcanas',2);

UNLOCK TABLES;


DROP TABLE IF EXISTS `user_interaction_log`;


CREATE TABLE `user_interaction_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `http_method` varchar(255) DEFAULT NULL,
  `remote_address` varchar(255) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



LOCK TABLES `user_interaction_log` WRITE;

INSERT INTO `user_interaction_log` VALUES (1,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:32:43.978598','http://localhost:8080/characters','lmarquez'),(2,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:32:53.784168','http://localhost:8080/characters/1011334/info','lmarquez'),(3,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:32:59.518049','http://localhost:8080/characters/1011334','lmarquez'),(4,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:33:06.731739','http://localhost:8080/comics','lmarquez'),(5,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:33:21.980260','http://localhost:8080/comics','lmarquez'),(6,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:34:05.180794','http://localhost:8080/comics','lmarquez'),(7,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:35:43.877228','http://localhost:8080/comics','lmarquez'),(8,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:36:00.317637','http://localhost:8080/comics','lmarquez'),(9,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:37:50.028751','http://localhost:8080/comics','lmarquez'),(10,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:38:03.458212','http://localhost:8080/comics/82967','lmarquez'),(11,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:47:00.716146','http://localhost:8080/users-interactions','gcanas'),(12,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:48:46.566548','http://localhost:8080/users-interactions/lmarquez','gcanas'),(13,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:48:53.547657','http://localhost:8080/users-interactions/gcanas','gcanas'),(14,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:49:21.971738','http://localhost:8080/users-interactions/gcanas','gcanas'),(15,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:49:24.770264','http://localhost:8080/users-interactions/gcanas','gcanas'),(16,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:50:02.026762','http://localhost:8080/users-interactions','lmarquez'),(17,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:50:11.035293','http://localhost:8080/users-interactions/lmarquez','lmarquez'),(18,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:50:17.827503','http://localhost:8080/users-interactions/gcanas','lmarquez'),(19,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:51:19.597764','http://localhost:8080/users-interactions/gcanas','lmarquez'),(20,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:51:28.309714','http://localhost:8080/users-interactions/lmarquez','lmarquez'),(21,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:52:53.012562','http://localhost:8080/users-interactions/lmarquez','lmarquez'),(22,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:53:22.121374','http://localhost:8080/users-interactions/lmarquez','lmarquez'),(23,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:53:41.053751','http://localhost:8080/users-interactions/lmarquez','lmarquez'),(24,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:54:18.936453','http://localhost:8080/users-interactions/lmarquez','lmarquez'),(25,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:54:34.035227','http://localhost:8080/users-interactions/gcanas','lmarquez'),(26,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:57:37.883034','http://localhost:8080/users-interactions/gcanas','gcanas'),(27,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:57:44.865061','http://localhost:8080/users-interactions/lmarquez','gcanas'),(28,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:57:53.996710','http://localhost:8080/users-interactions/lmarquez','gcanas'),(29,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:58:50.513664','http://localhost:8080/users-interactions/lmarquez','gcanas'),(30,'GET','0:0:0:0:0:0:0:1','2023-10-10 13:58:55.652764','http://localhost:8080/users-interactions/gcanas','gcanas');

UNLOCK TABLES;