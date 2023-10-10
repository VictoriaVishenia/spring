CREATE DATABASE IF NOT EXISTS `todo`;
USE `todo`;
DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `description` varchar(100) NOT NULL,
                        `status` int(11) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;


LOCK TABLES `task` WRITE;
INSERT IGNORE INTO `task` VALUES (1,'aaa',1),(2,'bbb',2),(3,'ccc',0),(4,'ddd',1),(5,'eee',2),(6,'fff',0),(7,'ggg',1),(8,'hhh',2),(9,'jjj',0),(10,'kkk',1),(11,'lll',2),(12,'mmm',0),(13,'nnn',1),(14,'ooo',2),(15,'ppp',0);
UNLOCK TABLES;