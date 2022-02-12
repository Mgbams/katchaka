-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: katchaka
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
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'homme'),(2,'femme');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interet`
--

DROP TABLE IF EXISTS `interet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interet` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interet`
--

LOCK TABLES `interet` WRITE;
/*!40000 ALTER TABLE `interet` DISABLE KEYS */;
INSERT INTO `interet` VALUES (1,'danser'),(2,'voyager'),(3,'exercise'),(4,'sports');
/*!40000 ALTER TABLE `interet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitation`
--

DROP TABLE IF EXISTS `invitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invitation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dateEnvoi` datetime DEFAULT NULL,
  `dateLecture` datetime DEFAULT NULL,
  `estAccepte` bit(1) NOT NULL,
  `destinataire_id` bigint DEFAULT NULL,
  `expediteur_id` bigint DEFAULT NULL,
  `vieCommune_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkatk7jdq9kxde45mwy266fr61` (`destinataire_id`),
  KEY `FK41d83kxxivcwubdrl08pxtqsc` (`expediteur_id`),
  KEY `FKskbqrwc8eei1n42oxkpdi09tl` (`vieCommune_id`),
  CONSTRAINT `FK41d83kxxivcwubdrl08pxtqsc` FOREIGN KEY (`expediteur_id`) REFERENCES `personne` (`id`),
  CONSTRAINT `FKkatk7jdq9kxde45mwy266fr61` FOREIGN KEY (`destinataire_id`) REFERENCES `personne` (`id`),
  CONSTRAINT `FKskbqrwc8eei1n42oxkpdi09tl` FOREIGN KEY (`vieCommune_id`) REFERENCES `viecommune` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitation`
--

LOCK TABLES `invitation` WRITE;
/*!40000 ALTER TABLE `invitation` DISABLE KEYS */;
INSERT INTO `invitation` VALUES (5,'2021-12-23 01:02:49','2021-12-25 00:20:00',_binary '\0',5,1,NULL),(15,'2021-12-25 01:06:47','2021-12-25 01:07:00',_binary '',1,6,NULL),(16,'2022-01-03 14:53:15','2022-01-03 17:30:00',_binary '',1,7,NULL),(17,'2022-01-03 17:27:41',NULL,_binary '\0',2,7,NULL);
/*!40000 ALTER TABLE `invitation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contenu` varchar(255) DEFAULT NULL,
  `dateEnvoi` date DEFAULT NULL,
  `dateLecture` date DEFAULT NULL,
  `destinataire_id` bigint DEFAULT NULL,
  `expediteur_id` bigint DEFAULT NULL,
  `vieCommune_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaxlwxml74k9f1muejhf26cybd` (`destinataire_id`),
  KEY `FKq1eiqwk9gqgn6g51qq8qouq05` (`expediteur_id`),
  KEY `FKpp6cmhe9hm3am8t7qvxj7odwo` (`vieCommune_id`),
  CONSTRAINT `FKaxlwxml74k9f1muejhf26cybd` FOREIGN KEY (`destinataire_id`) REFERENCES `personne` (`id`),
  CONSTRAINT `FKpp6cmhe9hm3am8t7qvxj7odwo` FOREIGN KEY (`vieCommune_id`) REFERENCES `viecommune` (`id`),
  CONSTRAINT `FKq1eiqwk9gqgn6g51qq8qouq05` FOREIGN KEY (`expediteur_id`) REFERENCES `personne` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personne`
--

DROP TABLE IF EXISTS `personne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personne` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bio` longtext,
  `dateDeNaissance` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `estFumeur` bit(1) NOT NULL,
  `motDePasse` varchar(255) DEFAULT NULL,
  `nbCredits` int NOT NULL,
  `pseudo` varchar(255) DEFAULT NULL,
  `genre_id` bigint NOT NULL,
  `genreRecherche_id` bigint NOT NULL,
  `statut_id` bigint NOT NULL,
  `ville_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7pyd2pqhn649wq72rdpwtfn7d` (`genre_id`),
  KEY `FKr65m5kcl9koou47ahav32am3b` (`genreRecherche_id`),
  KEY `FK2623p9rlxgj2ch5bg9m2j7sls` (`statut_id`),
  KEY `FKhk062ej5w6j970u9bdey622n9` (`ville_id`),
  CONSTRAINT `FK2623p9rlxgj2ch5bg9m2j7sls` FOREIGN KEY (`statut_id`) REFERENCES `statut` (`id`),
  CONSTRAINT `FK7pyd2pqhn649wq72rdpwtfn7d` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`),
  CONSTRAINT `FKhk062ej5w6j970u9bdey622n9` FOREIGN KEY (`ville_id`) REFERENCES `ville` (`id`),
  CONSTRAINT `FKr65m5kcl9koou47ahav32am3b` FOREIGN KEY (`genreRecherche_id`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personne`
--

LOCK TABLES `personne` WRITE;
/*!40000 ALTER TABLE `personne` DISABLE KEYS */;
INSERT INTO `personne` VALUES (1,'I am a sports lover and i am in lookout for a sexy lady.','1989-07-06','a@a.fr',_binary '\0','password',500,'king',1,2,1,1),(2,'Les rencontres en ligne ne sont-elles pas géniales? D\'accord, peut être que cela peut parfois être difficile, mais c\'est toujours excitant d\'avoir la chance de rencontrer quelqu\'un de spécial.','1986-06-12','b@b.fr',_binary '\0','password',500,'Roman',1,2,2,2),(3,'si je devais choisir trois mots pour me d\'écrire, ils seraient positifs, un peu timides et quelqu\'un qui aime rire.','1983-07-15','c@c.fr',_binary '\0','password',500,'Kierran',1,2,1,2),(4,'Je ne suis pas le meilleur pour me d\'écrire, alors j\'ai contacté quelques amis et leur ai demandé de me dire comment je suis afin que je puisse partager cela avec vous.','1980-02-15','d@d.fr',_binary '\0','password',500,'John',1,2,4,1),(5,'Pendant que je suis encore en train de comprendre tout cela, voici quelque chose dont je suis ici : je suis heureux d\'être ici !','1984-02-25','e@e.fr',_binary '\0','password',500,'Jennifer',2,1,3,1),(6,'Je suis un fan de vin, de films, de randonnée et de sortir de la ville pour le week-end. Discutons et voyons si nous sommes peut être un bon ajustement !','1991-06-26','f@f.fr',_binary '\0','password',500,'Cassandra',2,1,1,2),(7,'Je suis un fan de vin, de films, de randonnée et de sortir de la ville pour le week-end. Discutons et voyons si nous sommes peut être un bon ajustement !','1981-06-17','g@g.fr',_binary '\0','password',500,'Cynthia',2,1,1,2),(8,'People who feel the need to type some version of \"I\'m not looking for any drama\" in their dating app bios are likely no stranger to drama. Perhaps they\'ve caused or attracted drama in the past, or perhaps this is code for \"I\'m going to gaslight you and treat you like crap, but I don\'t want to be called out on it.\" Either option seems bad','1989-05-04','h@h.fr',_binary '\0','password',500,'Falentina',2,1,3,2),(9,'Several people who responded to my call for red flags said they\'re definitely swiping left on anyone who has more than one topless or revealing profile photo. One shirtless pic? OK. But if your profile looks like a collection of press images from Magic Mike, it\'s a left swipe. However, if you were in Magic Mike, right swipe.','1990-06-08','j@j.fr',_binary '\0','password',500,'Marine',2,1,2,1),(10,'Thinking of listing your education as something quirky like \"graduated from the school of life\" or \"the school of hard knocks?\" The consensus seems to be that it\'s not as funny as you think it is, and it\'s better to be honest about your education.','1985-06-06','k@k.fr',_binary '\0','password',500,'Gerald',1,2,1,2),(11,'Women simply don\'t have time for this nonsense. Semi-related, if a man\'s bio is a straightforward list of requirements he\'d like to find in a woman, such as, \"I\'m looking for a girl who likes to take care of herself,\" \"Must be physically fit,\" or \"looking for a girl who can hold a conversation\" that\'s also a major turnoff.','1989-07-13','m@m.fr',_binary '\0','password',500,'Bernard',1,2,3,1),(12,'A bio that states a love of tacos, pizza, sushi, or coffee? Who doesn\'t love those things? This is your chance to be original, not say, \"Hi, my name\'s Nicole and I, like so many others on this planet, love pizza.\"','1980-06-04','n@n.fr',_binary '\0','password',500,'Benoit',1,2,4,2),(13,'If you only have one photo on your dating app bio, I\'m sorry, it\'s a left swipe. Adjacent complaints include \"one far away pic and four nature pics\" and \"when their first pic isn\'t their face.\" Please take note.','1986-01-29','p@p.fr',_binary '\0','password',500,'paul',1,2,1,1);
/*!40000 ALTER TABLE `personne` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personne_interets`
--

DROP TABLE IF EXISTS `personne_interets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personne_interets` (
  `personnes_id` bigint NOT NULL,
  `interets_id` bigint NOT NULL,
  KEY `FK7abtljlvrfhlny0xyedah0lsw` (`interets_id`),
  KEY `FKch3nr5benpuo548e7ge0u6s0p` (`personnes_id`),
  CONSTRAINT `FK7abtljlvrfhlny0xyedah0lsw` FOREIGN KEY (`interets_id`) REFERENCES `interet` (`id`),
  CONSTRAINT `FKch3nr5benpuo548e7ge0u6s0p` FOREIGN KEY (`personnes_id`) REFERENCES `personne` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personne_interets`
--

LOCK TABLES `personne_interets` WRITE;
/*!40000 ALTER TABLE `personne_interets` DISABLE KEYS */;
INSERT INTO `personne_interets` VALUES (1,4),(2,1),(3,3),(4,3),(4,4),(5,1),(5,2),(5,3),(6,3),(6,4),(7,2),(7,3),(8,1),(8,2),(9,3),(9,4),(10,2),(11,2),(12,3),(13,1),(13,2);
/*!40000 ALTER TABLE `personne_interets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statut`
--

DROP TABLE IF EXISTS `statut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statut` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statut`
--

LOCK TABLES `statut` WRITE;
/*!40000 ALTER TABLE `statut` DISABLE KEYS */;
INSERT INTO `statut` VALUES (1,'célibataire'),(2,'séparé(e)'),(3,'divorcé(e)'),(4,'veuf/veuve');
/*!40000 ALTER TABLE `statut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viecommune`
--

DROP TABLE IF EXISTS `viecommune`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `viecommune` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dateDebut` date DEFAULT NULL,
  `dateFin` date DEFAULT NULL,
  `nbCredits` int NOT NULL,
  `invitation_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmytqpybcvh5xxewbep0100ujv` (`invitation_id`),
  CONSTRAINT `FKmytqpybcvh5xxewbep0100ujv` FOREIGN KEY (`invitation_id`) REFERENCES `invitation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viecommune`
--

LOCK TABLES `viecommune` WRITE;
/*!40000 ALTER TABLE `viecommune` DISABLE KEYS */;
INSERT INTO `viecommune` VALUES (16,'2021-12-25',NULL,500,15),(17,'2021-12-25',NULL,500,15),(18,'2022-01-03',NULL,500,16),(19,'2022-01-03',NULL,500,16);
/*!40000 ALTER TABLE `viecommune` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ville`
--

DROP TABLE IF EXISTS `ville`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ville` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ville`
--

LOCK TABLES `ville` WRITE;
/*!40000 ALTER TABLE `ville` DISABLE KEYS */;
INSERT INTO `ville` VALUES (1,'lyon'),(2,'grenoble');
/*!40000 ALTER TABLE `ville` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ville_personnes`
--

DROP TABLE IF EXISTS `ville_personnes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ville_personnes` (
  `Ville_id` bigint NOT NULL,
  `personnes_id` bigint NOT NULL,
  UNIQUE KEY `UK_ijvs1wwhbyglxrsthg498bt9b` (`personnes_id`),
  KEY `FKhrdifgj019rshm7d0hwvsfgdg` (`Ville_id`),
  CONSTRAINT `FKhrdifgj019rshm7d0hwvsfgdg` FOREIGN KEY (`Ville_id`) REFERENCES `ville` (`id`),
  CONSTRAINT `FKpde4lhsoojvp7a8yvjuqiomv0` FOREIGN KEY (`personnes_id`) REFERENCES `personne` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ville_personnes`
--

LOCK TABLES `ville_personnes` WRITE;
/*!40000 ALTER TABLE `ville_personnes` DISABLE KEYS */;
INSERT INTO `ville_personnes` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `ville_personnes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-12  4:25:56
