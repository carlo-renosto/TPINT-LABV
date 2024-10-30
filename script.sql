CREATE DATABASE  IF NOT EXISTS `tpint_grupo_5_lab4` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tpint_grupo_5_lab4`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: tpint_grupo_5_lab4
-- ------------------------------------------------------
-- Server version	8.4.0

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
-- Table structure for table `especialidades`
--

DROP TABLE IF EXISTS `especialidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidades` (
  `Id` varchar(255) NOT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `Nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidades`
--

LOCK TABLES `especialidades` WRITE;
/*!40000 ALTER TABLE `especialidades` DISABLE KEYS */;
INSERT INTO `especialidades` VALUES ('1',1,'Pediatría'),('10',1,'Geriatría'),('11',1,'Ginecología'),('12',1,'Hebiatría'),('13',1,'Neonatología'),('14',1,'Andrología'),('15',1,'Alergología'),('2',1,'Cardiología'),('3',1,'Traumatología'),('4',1,'Hidrología'),('5',1,'Cirugía'),('6',1,'Farmacología'),('7',1,'Radiología'),('8',1,'Microbiología'),('9',1,'Nefrología');
/*!40000 ALTER TABLE `especialidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicos`
--

DROP TABLE IF EXISTS `medicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicos` (
  `legajo` int NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `dia` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `fecha_nac` varchar(255) DEFAULT NULL,
  `horario_fin` int DEFAULT NULL,
  `horario_inicio` int DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `especialidad` varchar(255) DEFAULT NULL,
  `usuario_c` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`legajo`),
  KEY `FK9B4BB42AB037930E` (`usuario_c`),
  KEY `FK9B4BB42A4C4C3ED6` (`especialidad`),
  CONSTRAINT `FK9B4BB42A4C4C3ED6` FOREIGN KEY (`especialidad`) REFERENCES `especialidades` (`Id`),
  CONSTRAINT `FK9B4BB42AB037930E` FOREIGN KEY (`usuario_c`) REFERENCES `usuarios` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicos`
--

LOCK TABLES `medicos` WRITE;
/*!40000 ALTER TABLE `medicos` DISABLE KEYS */;
INSERT INTO `medicos` VALUES (145,'Lopez','Martes','Navarro 923','omarlopez@hotmail.com',1,'1970-01-17',18,6,'Tigre','Omar','Buenos Aires','M','1134218732','10','omarlopez1'),(146,'Martinez','Lunes','Rodriguez Peña 2943','veromtnz@gmail.com',1,'1981-08-16',16,10,'Rosario','Veronica','Santa Fe','F','1189432393','2','veromartinez'),(147,'Quiroga','Sabado','Monserrat 932','pepequiroga@gmail.com',1,'1955-12-01',9,8,'Montecristo','Pedro','Cordoba','M','1147238453','4','pepequiroga'),(148,'Marquez','Jueves','Ferreyra 2813','josephmarquez@yahoo.com',1,'1993-11-20',20,12,'Pilar','Jose','Buenos Aires','M','1198327643','7','josemarquez'),(149,'Ferreyra','Viernes','Romero 9533','luisaff40@yahoo.com',1,'1977-07-07',20,6,'Rafaela','Luisa','Santa Fe','F','1144938713','5','luisaferreyra'),(150,'Pescador','Domingo','Rincon 8329','martinpesca77@gmail.com',1,'1971-08-21',18,14,'Tigre','Martin','Buenos Aires','M','1167398393','1','martinpescador'),(151,'Ragnar','Martes','Cazón 9430','larsragnk20@gmail.com',1,'1980-09-24',18,8,'Tigre','Lars','Buenos Aires','M','1187329432','3','larsragnar'),(152,'Larralde','Miercoles','Monaco 37','lararalde19@gmail.com',1,'1986-04-11',14,10,'Rosario','Lara','Santa Fe','F','1157328743','3','laralarralde'),(153,'Astillan','Lunes','Butteler 1903','diegoastillan@gmail.com',1,'2000-09-13',18,6,'Tigre','Diego','Buenos Aires','M','1163938472','13','diegoastillan'),(154,'Alberti','Jueves','Rodriguez 9283','carloalberti@yahoo.com',1,'1992-02-05',14,12,'Montecristo','Carlo','Cordoba','M','1165255383','4','carloalberti'),(155,'Lopez','Viernes','Italia 291','rosariolp15@gmail.com',1,'1989-01-20',18,6,'Tigre','Rosario','Buenos Aires','F','1165908321','11','rosariolopez'),(156,'Tepes','Lunes','Martinez 934','rodritepes@hotmail.com',1,'1988-07-15',20,16,'Pilar','Rodrigo','Buenos Aires','M','1165909123','4','rodrigotepes'),(157,'Ramales','Martes','Las Piedras 89','miguelramales@gmail.com',1,'1986-06-04',12,10,'La Calera','Miguel','Cordoba','M','1148298732','15','miguelramales'),(158,'Roberti','Jueves','Montenegro 192','marinaroberti@gmail.com',1,'1979-11-23',16,10,'Benavidez','Marina','Buenos Aires','F','1153892313','4','marinaroberti'),(159,'Paredes','Viernes','Navarro 1356','sergioparedes10@hotmail.com',1,'1974-10-24',20,14,'Tigre','Sergio','Buenos Aires','M','1165902384','8','sergioparedes');
/*!40000 ALTER TABLE `medicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pacientes` (
  `DNI` varchar(255) NOT NULL,
  `Apellido` varchar(255) DEFAULT NULL,
  `Direccion` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `FechaNac` varchar(255) DEFAULT NULL,
  `Localidad` varchar(255) DEFAULT NULL,
  `Nombre` varchar(255) DEFAULT NULL,
  `Provincia` varchar(255) DEFAULT NULL,
  `Telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes`
--

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
INSERT INTO `pacientes` VALUES ('28743832','Rossi','Fassi 2493','pedrorossi@yahoo.com',1,'1949-04-16','Benavidez','Pedro','Buenos Aires','1183492432'),('29324932','Perez','Monserrat 1054','luismperez@hotmail.com.ar',1,'1952-04-10','La Calera','Luis','Cordoba','1193248723'),('33493243','Fussi','Don Bosco 9321','sofiafussi@yahoo.com',1,'1969-07-10','Rosario','Sofia','Santa Fe','1194324832'),('34832932','Rey','Albania 832','mrey90@yahoo.com',1,'1990-01-20','Los Cedros','Mariano','Cordoba','1193248432'),('38211921','Mendez','San Pedro 823','carlosmendez84@hotmail.com.ar',1,'1984-09-01','Rosario','Carlos','Santa Fe','1142328424'),('40128329','Aguilar','Italia 3708','martinaaguilar@gmail.com',1,'1998-05-03','Tigre','Martina','Buenos Aires','1149893289'),('41432953','Rodriguez','Cambaceres 3021','juanardgz@hotmail.com',1,'1970-07-27','Montecristo','Juana','Cordoba','1124321823'),('42982052','Marquez','Belgrano 136','marquezmartin@yahoo.com',1,'1979-02-11','Pilar','Martin','Buenos Aires','1143928472'),('44234832','Larsen','Tigre 1237','maxlarsen19@hotmail.com',1,'1996-02-03','Pilar','Maximo','Buenos Aires','1149235462'),('44724737','Merlo','Mendez 2934','thom15mendez@gmail.com',1,'2003-12-09','Rafaela','Thomas','Santa Fe','1147499882'),('44724987','Lopez','Alberdi 2045','jorge.lopez@gmail.com',1,'2001-03-10','Benavidez','Jorge','Buenos Aires','1165359373'),('44728943','Martinez','Cazón 2400','mariamartinez20@yahoo.com',1,'1995-11-22','Tigre','Maria','Buenos Aires','1123498432'),('45234934','Martin','Santa Fe 2048','josem@gmail.com',1,'2004-01-25','San Lorenzo','José','Santa Fe','1193248732'),('45832942','Rodriguez','Crisologo Larralde 1230','lissrodriguez15@gmail.com',1,'2004-02-06','Tigre','Lisa','Buenos Aires','1149523456'),('46329421','Pedri','Cedros 1232','matepedri18@gmail.com',1,'2006-01-04','La Calera','Mateo','Cordoba','1149230122');
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turnos`
--

DROP TABLE IF EXISTS `turnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turnos` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `dia` varchar(255) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `estado_turno` varchar(255) DEFAULT NULL,
  `hora_fin` varchar(255) DEFAULT NULL,
  `hora_inicio` varchar(255) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `medico_id` int DEFAULT NULL,
  `paciente_id` varchar(255) DEFAULT NULL,
  `diaFecha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK95FD4F4131B0C58C` (`paciente_id`),
  KEY `FK95FD4F416C6F0B0C` (`medico_id`),
  CONSTRAINT `FK95FD4F4131B0C58C` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`DNI`),
  CONSTRAINT `FK95FD4F416C6F0B0C` FOREIGN KEY (`medico_id`) REFERENCES `medicos` (`legajo`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turnos`
--

LOCK TABLES `turnos` WRITE;
/*!40000 ALTER TABLE `turnos` DISABLE KEYS */;
INSERT INTO `turnos` VALUES (7,'2024-02-10',1,'Presente','13','12','El paciente tuvo un control medico',155,'34832932',NULL),(8,'2024-01-13',1,'Presente','9','8','La paciente tuvo un control medico',155,'33493243',NULL),(9,'2024-03-01',1,'Ausente','18','17','',150,'45832942',NULL),(10,'2024-01-18',1,'Presente','11','10','El paciente se encuentra sin problemas',157,'38211921',NULL),(11,'2024-02-27',1,'Ausente','13','12','',153,'44728943',NULL),(12,'2024-07-28',1,'','17','16','',155,'44234832',NULL),(13,'2024-01-11',1,'Presente','13','12','La paciente vino por una molestia menor',158,'40128329',NULL),(14,'2024-02-02',1,'Presente','16','15','El paciente tuvo un control medico',146,'46329421',NULL),(15,'2024-02-04',1,'Ausente','13','12','',149,'45832942',NULL),(16,'2024-08-08',1,'','9','8','',145,'28743832',NULL),(17,'2024-03-01',1,'Presente','14','13','El paciente vino por una consulta',151,'29324932',NULL),(18,'2024-09-11',1,'','13','12','',146,'41432953',NULL),(19,'2024-07-12',1,'Presente','11','10','El paciente vino por una consulta',155,'42982052',NULL),(20,'2024-07-25',1,'','13','12','',154,'44724737',NULL),(21,'2024-07-31',1,'','15','14','',148,'45234934',NULL);
/*!40000 ALTER TABLE `turnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `usuario` varchar(255) NOT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('admin',1,'admin',1),('carloalberti',0,'carloalberti',1),('diegoastillan',0,'diegoastillan',1),('josemarquez',0,'josemarquez',1),('laralarralde',0,'laralarralde',1),('larsragnar',0,'larsragnar',1),('luisaferreyra',0,'luisaferreyra',1),('marinaroberti',0,'marinaroberti',1),('martinpescador',0,'martinpescador',1),('medico',0,'medico',1),('medico2',0,'medico2',1),('miguelramales',0,'miguelramales',1),('omarlopez1',0,'omarlopez1',1),('pepequiroga',0,'pepequiroga',1),('piki',0,'piki',0),('rodrigotepes',0,'rodrigotepes',1),('rosariolopez',0,'rosariolopez',1),('sergioparedes',0,'sergioparedes',1),('veromartinez',0,'veromartinez',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-10 13:59:35
