-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.39 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para bidezain_db
CREATE DATABASE IF NOT EXISTS `bidezain_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bidezain_db`;

-- Volcando estructura para tabla bidezain_db.aktiboa
CREATE TABLE IF NOT EXISTS `aktiboa` (
  `ID_aktiboa` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `ID_mota` smallint(5) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_aktiboa`),
  KEY `AK_MOT_FK` (`ID_mota`),
  CONSTRAINT `AK_MOT_FK` FOREIGN KEY (`ID_mota`) REFERENCES `aktibo_mota` (`ID_aktibo_mota`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.aktiboa: ~0 rows (aproximadamente)
DELETE FROM `aktiboa`;
/*!40000 ALTER TABLE `aktiboa` DISABLE KEYS */;
/*!40000 ALTER TABLE `aktiboa` ENABLE KEYS */;

-- Volcando estructura para tabla bidezain_db.aktibo_mota
CREATE TABLE IF NOT EXISTS `aktibo_mota` (
  `ID_aktibo_mota` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `aktibo_mota` varchar(30) NOT NULL,
  `deskribapena` varchar(250) NOT NULL,
  PRIMARY KEY (`ID_aktibo_mota`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.aktibo_mota: ~0 rows (aproximadamente)
DELETE FROM `aktibo_mota`;
/*!40000 ALTER TABLE `aktibo_mota` DISABLE KEYS */;
/*!40000 ALTER TABLE `aktibo_mota` ENABLE KEYS */;

-- Volcando estructura para tabla bidezain_db.aurreikuspena
CREATE TABLE IF NOT EXISTS `aurreikuspena` (
  `ID_aurreikuspena` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `ID_mota` smallint(5) unsigned NOT NULL DEFAULT '0',
  `errepidea` varchar(30) NOT NULL,
  `probintzia` enum('GIPUZKOA','BIZKAIA','ARABA') NOT NULL,
  `herria` varchar(150) NOT NULL,
  `astekoEguna` enum('AL','AR','AZ','OG','OR','LR','IG') DEFAULT NULL,
  `data` varchar(30) NOT NULL,
  `lat` decimal(8,5) NOT NULL,
  `lng` decimal(8,5) NOT NULL,
  `tenperatura` float NOT NULL,
  `haizea` float NOT NULL,
  `prezipitazioa` float NOT NULL,
  `hasieraKm` float NOT NULL,
  `amaieraKm` float NOT NULL,
  `itIs` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ID_aurreikuspena`),
  KEY `AU_MOT_FK` (`ID_mota`),
  CONSTRAINT `AU_MOT_FK` FOREIGN KEY (`ID_mota`) REFERENCES `intzidentzia_mota` (`ID_intzidentzia_mota`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.aurreikuspena: ~3 rows (aproximadamente)
DELETE FROM `aurreikuspena`;
/*!40000 ALTER TABLE `aurreikuspena` DISABLE KEYS */;
INSERT INTO `aurreikuspena` (`ID_aurreikuspena`, `ID_mota`, `errepidea`, `probintzia`, `herria`, `astekoEguna`, `data`, `lat`, `lng`, `tenperatura`, `haizea`, `prezipitazioa`, `hasieraKm`, `amaieraKm`, `itIs`) VALUES
	(1, 2, 'N-1', 'GIPUZKOA', 'Beasain', 'AL', '2019-06-01', 43.04853, -2.20650, 18, 40, 0, 0, 50, 1),
	(8, 9, 'BU-530', 'GIPUZKOA', 'Lantaron', 'AL', '2019-06-11 10:45:00.220106', 0.00000, 0.00000, 0, 0, 0, 0, 0, 1),
	(9, 9, 'E-5', 'GIPUZKOA', 'Elgoibar', 'AL', '2019-06-11 21:17:44.148151', 0.00000, 0.00000, 13, 4, 0, 0, 0, 1),
	(10, 9, 'AP-1', 'GIPUZKOA', 'Eskoriatza', 'AL', '2019-06-11 21:17:45.831243', 0.00000, 0.00000, 10.8, 2.36, 0.3, 0, 0, 1),
	(11, 9, 'AP-1', 'GIPUZKOA', 'Eskoriatza', 'AL', '2019-06-11 21:17:47.578575', 0.00000, 0.00000, 10.8, 2.36, 0.3, 0, 0, 1),
	(12, 9, 'AP-1', 'GIPUZKOA', 'Aretxabaleta', 'AL', '2019-06-11 21:17:49.320886', 0.00000, 0.00000, 11.3, 0.97, 0.2, 0, 0, 1),
	(13, 9, 'Jaitzubia Auzoa', 'GIPUZKOA', 'Hondarribia', 'AL', '2019-06-11 21:17:52.834486', 0.00000, 0.00000, 14.5, 5.16, 0, 0, 0, 1),
	(14, 9, 'AP-1', 'GIPUZKOA', 'Bergara', 'AL', '2019-06-11 21:17:57.133779', 0.00000, 0.00000, 12.3, 3.22, 0, 0, 0, 1),
	(15, 9, 'E-5', 'GIPUZKOA', 'Deba', 'AL', '2019-06-1122:14:51.298404', 0.00000, 0.00000, 13, 3.41, 0, 0, 0, 1),
	(16, 9, 'E-5', 'GIPUZKOA', 'Deba', 'AL', '2019-06-1122:14:52.718125', 0.00000, 0.00000, 13, 3.41, 0, 0, 0, 1),
	(17, 9, 'AP-1', 'GIPUZKOA', 'Arrasate', 'AL', '2019-06-1122:14:54.189971', 0.00000, 0.00000, 10.5, 1.19, 0.4, 0, 0, 1),
	(18, 9, 'E-5', 'GIPUZKOA', 'Deva', 'AL', '2019-06-1122:14:56.329421', 0.00000, 0.00000, 13, 3.41, 0, 0, 0, 1),
	(19, 9, 'A-15', 'GIPUZKOA', 'Donostia', 'AL', '2019-06-1122:14:58.339183', 0.00000, 0.00000, 13.4, 1.72, 0, 0, 0, 1),
	(20, 9, 'AP-1', 'GIPUZKOA', 'Vitoria-Gasteiz', 'AL', '2019-06-1122:14:59.946103', 0.00000, 0.00000, 10.3, 2.44, 0, 0, 0, 1),
	(42, 9, 'SagardotegiZeharraAldea', 'GIPUZKOA', 'Astigarraga', 'AL', '2019-06-1122:15:01.531902', 0.00000, 0.00000, 12, 1.86, 0, 0, 0, 1),
	(43, 9, 'E-5', 'GIPUZKOA', 'Deba', 'AL', '2019-06-1122:14:51.298404', 0.00000, 0.00000, 13, 3.41, 0, 0, 0, 1),
	(44, 9, 'E-5', 'GIPUZKOA', 'Deba', 'AL', '2019-06-1122:14:52.718125', 0.00000, 0.00000, 13, 3.41, 0, 0, 0, 1),
	(45, 9, 'AP-1', 'GIPUZKOA', 'Arrasate', 'AL', '2019-06-1122:14:54.189971', 0.00000, 0.00000, 10.5, 1.19, 0.4, 0, 0, 1),
	(46, 9, 'E-5', 'GIPUZKOA', 'Deva', 'AL', '2019-06-1122:14:56.329421', 0.00000, 0.00000, 13, 3.41, 0, 0, 0, 1),
	(47, 9, 'A-15', 'GIPUZKOA', 'Donostia', 'AL', '2019-06-1122:14:58.339183', 0.00000, 0.00000, 13.4, 1.72, 0, 0, 0, 1),
	(48, 9, 'AP-1', 'GIPUZKOA', 'Vitoria-Gasteiz', 'AL', '2019-06-1122:14:59.946103', 0.00000, 0.00000, 10.3, 2.44, 0, 0, 0, 1),
	(49, 9, 'AitorZeharraAldea', 'GIPUZKOA', 'Astigarraga', 'AL', '2019-06-1122:15:01.531902', 0.00000, 0.00000, 12, 1.86, 0, 0, 0, 1),
	(50, 9, 'A-4128', 'GIPUZKOA', 'Vitoria-Gasteiz', 'AL', '2019-06-1122:19:02.748113', 0.00000, 0.00000, 10.3, 2.44, 0, 0, 0, 1),
	(51, 9, 'VentadelaEstrellaKalea', 'GIPUZKOA', 'Vitoria-Gasteiz', 'AL', '2019-06-1122:19:04.672996', 0.00000, 0.00000, 10.3, 2.44, 0, 0, 0, 1),
	(52, 9, 'A-1', 'GIPUZKOA', 'Idiazabal', 'AL', '2019-06-1122:19:06.170590', 0.00000, 0.00000, 10.8, 2.47, 0.8, 0, 0, 1),
	(53, 9, 'A-1', 'GIPUZKOA', 'Andoain', 'AL', '2019-06-1122:19:10.733757', 0.00000, 0.00000, 11.3, 1.86, 0, 0, 0, 1);
/*!40000 ALTER TABLE `aurreikuspena` ENABLE KEYS */;

-- Volcando estructura para tabla bidezain_db.erabiltzailea
CREATE TABLE IF NOT EXISTS `erabiltzailea` (
  `ID_erabiltzailea` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `izena` varchar(30) NOT NULL,
  `abizena` varchar(30) NOT NULL,
  `erabiltzailea` varchar(30) NOT NULL,
  `passwordHash` longtext NOT NULL,
  `passwordSalt` longtext,
  `eposta` varchar(40) NOT NULL,
  `telefonoa` varchar(9) DEFAULT NULL,
  `aktibo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ID_erabiltzailea`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.erabiltzailea: ~8 rows (aproximadamente)
DELETE FROM `erabiltzailea`;
/*!40000 ALTER TABLE `erabiltzailea` DISABLE KEYS */;
INSERT INTO `erabiltzailea` (`ID_erabiltzailea`, `izena`, `abizena`, `erabiltzailea`, `passwordHash`, `passwordSalt`, `eposta`, `telefonoa`, `aktibo`) VALUES
	(1, 'Maria', 'Magdalena', 'mamagdalena', '1234567890', '1234567890', 'mamagda@jisus.com', '112', 0),
	(2, 'Aitor', 'Aitor', 'Aitor', '$2a$16$IeawqpLoJBkn6x/cu6.5zeu73JO4E8APiw4QULFETb0ftz0aR4mLK', '$2a$16$IeawqpLoJBkn6x/cu6.5ze', 'Aitor', 'Aitor', 0);
/*!40000 ALTER TABLE `erabiltzailea` ENABLE KEYS */;

-- Volcando estructura para tabla bidezain_db.intzidentzia_aktiboa
CREATE TABLE IF NOT EXISTS `intzidentzia_aktiboa` (
  `ID_intzidentzia` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `ID_mota` smallint(5) unsigned NOT NULL DEFAULT '0',
  `probintzia` enum('GIPUZKOA','BIZKAIA','ARABA') NOT NULL,
  `kausa` varchar(300) NOT NULL,
  `herria` varchar(150) NOT NULL,
  `intzidentziaData` varchar(30) NOT NULL,
  `maila` enum('Beltza','Horia','Zuria') NOT NULL,
  `errepidea` varchar(150) NOT NULL,
  `zentzua` varchar(150) NOT NULL,
  `latitudea` decimal(8,5) NOT NULL,
  `longitudea` decimal(8,5) NOT NULL,
  PRIMARY KEY (`ID_intzidentzia`),
  KEY `IN_AK_MOT_FK` (`ID_mota`),
  CONSTRAINT `IN_AK_MOT_FK` FOREIGN KEY (`ID_mota`) REFERENCES `intzidentzia_mota` (`ID_intzidentzia_mota`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.intzidentzia_aktiboa: ~4 rows (aproximadamente)
DELETE FROM `intzidentzia_aktiboa`;
/*!40000 ALTER TABLE `intzidentzia_aktiboa` DISABLE KEYS */;
INSERT INTO `intzidentzia_aktiboa` (`ID_intzidentzia`, `ID_mota`, `probintzia`, `kausa`, `herria`, `intzidentziaData`, `maila`, `errepidea`, `zentzua`, `latitudea`, `longitudea`) VALUES
	(1, 1, 'BIZKAIA', 'Telefonoa erabiltzeagatik', 'Arrigorriaga', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.20952, -2.89450),
	(2, 2, 'GIPUZKOA', 'Euria', 'Arrasate', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.20954, -2.50421),
	(3, 3, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.26332, -2.95107),
	(5, 1, 'GIPUZKOA', 'Euria', 'Arrasate', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.20954, -2.50421),
	(8, 1, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.26332, -2.95107),
	(9, 1, 'BIZKAIA', 'Telefonoa erabiltzeagatik', 'Arrigorriaga', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.20952, -2.89450),
	(11, 3, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.26332, -2.95107),
	(14, 2, 'GIPUZKOA', 'Euria', 'Arrasate', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.20954, -2.50421),
	(16, 3, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.03827, -2.49474),
	(18, 1, 'BIZKAIA', 'Telefonoa erabiltzeagatik', 'Arrigorriaga', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.20952, -2.89450),
	(21, 2, 'GIPUZKOA', 'Euria', 'Aia', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.03827, -2.49474),
	(23, 4, 'GIPUZKOA', 'Aaa', 'Tolosa', '2019-05-30', 'Beltza', 'T-48', 'Irun', 43.13747, -2.08692),
	(24, 3, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.26332, -2.95107),
	(25, 3, 'BIZKAIA', 'Ataskoa', 'Markina-Xemein', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.03827, -2.49474),
	(30, 3, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.26332, -2.95107),
	(33, 10, 'BIZKAIA', 'Obras', 'Amorebieta-Etxano', '2019-06-11 21:30:19', 'Horia', 'N-634', 'SANTANDER', 43.18818, -2.68492);
/*!40000 ALTER TABLE `intzidentzia_aktiboa` ENABLE KEYS */;

-- Volcando estructura para tabla bidezain_db.intzidentzia_amaitua
CREATE TABLE IF NOT EXISTS `intzidentzia_amaitua` (
  `ID_intzidentzia` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `ID_mota` smallint(5) unsigned NOT NULL DEFAULT '0',
  `probintzia` enum('GIPUZKOA','BIZKAIA','ARABA') NOT NULL,
  `kausa` varchar(300) NOT NULL,
  `herria` varchar(150) NOT NULL,
  `intzidentziaData` varchar(30) NOT NULL,
  `maila` enum('Beltza','Horia','Zuria') NOT NULL,
  `errepidea` varchar(150) NOT NULL,
  `zentzua` varchar(150) NOT NULL,
  `latitudea` decimal(8,5) NOT NULL,
  `longitudea` decimal(8,5) NOT NULL,
  PRIMARY KEY (`ID_intzidentzia`),
  KEY `IN_AM_MOT_FK` (`ID_mota`),
  CONSTRAINT `IN_AM_MOT_FK` FOREIGN KEY (`ID_mota`) REFERENCES `intzidentzia_mota` (`ID_intzidentzia_mota`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.intzidentzia_amaitua: ~0 rows (aproximadamente)
DELETE FROM `intzidentzia_amaitua`;
/*!40000 ALTER TABLE `intzidentzia_amaitua` DISABLE KEYS */;
INSERT INTO `intzidentzia_amaitua` (`ID_intzidentzia`, `ID_mota`, `probintzia`, `kausa`, `herria`, `intzidentziaData`, `maila`, `errepidea`, `zentzua`, `latitudea`, `longitudea`) VALUES
	(1, 1, 'BIZKAIA', 'Telefonoa erabiltzeagatik', 'Arrigorriaga', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.20952, -2.89450),
	(2, 2, 'GIPUZKOA', 'Euria', 'Arrasate', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.20954, -2.50421),
	(3, 3, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.26332, -2.95107),
	(5, 1, 'GIPUZKOA', 'Euria', 'Arrasate', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.20954, -2.50421),
	(6, 1, 'BIZKAIA', 'Telefonoa erabiltzeagatik', 'Arrigorriaga', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.20952, -2.89450),
	(8, 1, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.26332, -2.95107),
	(9, 1, 'BIZKAIA', 'Telefonoa erabiltzeagatik', 'Arrigorriaga', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.20952, -2.89450),
	(10, 2, 'GIPUZKOA', 'Euria', 'Arrasate', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.20954, -2.50421),
	(11, 3, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.26332, -2.95107),
	(14, 2, 'GIPUZKOA', 'Euria', 'Arrasate', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.20954, -2.50421),
	(15, 1, 'BIZKAIA', 'Telefonoa erabiltzeagatik', 'Arrigorriaga', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.20952, -2.89450),
	(16, 3, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.03827, -2.49474),
	(17, 3, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.26332, -2.95107),
	(18, 1, 'BIZKAIA', 'Telefonoa erabiltzeagatik', 'Arrigorriaga', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.20952, -2.89450),
	(20, 2, 'GIPUZKOA', 'Euria', 'Arrasate', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.20954, -2.50421),
	(21, 2, 'GIPUZKOA', 'Euria', 'Aia', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.03827, -2.49474),
	(22, 1, 'BIZKAIA', 'Telefonoa erabiltzeagatik', 'Arrigorriaga', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.20952, -2.89450),
	(23, 4, 'GIPUZKOA', 'Aaa', 'Tolosa', '2019-05-30', 'Beltza', 'T-48', 'Irun', 43.13747, -2.08692),
	(24, 3, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.26332, -2.95107),
	(25, 3, 'BIZKAIA', 'Ataskoa', 'Markina-Xemein', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.03827, -2.49474),
	(27, 1, 'BIZKAIA', 'Telefonoa erabiltzeagatik', 'Arrigorriaga', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.20952, -2.89450),
	(28, 2, 'GIPUZKOA', 'Euria', 'Arrasate', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.20954, -2.50421),
	(30, 3, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.26332, -2.95107),
	(31, 2, 'GIPUZKOA', 'Euria', 'Arrasate', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.20954, -2.50421),
	(32, 1, 'BIZKAIA', 'Telefonoa erabiltzeagatik', 'Arrigorriaga', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.20952, -2.89450),
	(33, 10, 'BIZKAIA', 'Obras', 'Amorebieta-Etxano', '2019-06-11 21:30:19', 'Horia', 'N-634', 'SANTANDER', 43.18818, -2.68492);
/*!40000 ALTER TABLE `intzidentzia_amaitua` ENABLE KEYS */;

-- Volcando estructura para tabla bidezain_db.intzidentzia_mota
CREATE TABLE IF NOT EXISTS `intzidentzia_mota` (
  `ID_intzidentzia_mota` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `intzidentzia_mota` varchar(30) NOT NULL,
  PRIMARY KEY (`ID_intzidentzia_mota`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.intzidentzia_mota: ~8 rows (aproximadamente)
DELETE FROM `intzidentzia_mota`;
/*!40000 ALTER TABLE `intzidentzia_mota` DISABLE KEYS */;
INSERT INTO `intzidentzia_mota` (`ID_intzidentzia_mota`, `intzidentzia_mota`) VALUES
	(1, 'Meteorológica'),
	(2, 'Accidente'),
	(3, 'Retención'),
	(4, 'Seguridad vial'),
	(5, 'Otras incidencias'),
	(6, 'Puertos de montaña'),
	(7, 'Vialidad invernal tramos'),
	(8, 'Pruebas deportivas'),
	(9, 'Aurreikuspena'),
	(10, 'Obras');
/*!40000 ALTER TABLE `intzidentzia_mota` ENABLE KEYS */;

-- Volcando estructura para tabla bidezain_db.kontaktua
CREATE TABLE IF NOT EXISTS `kontaktua` (
  `ID_kontaktua` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `kontaktua` varchar(30) NOT NULL,
  `deskribapena` varchar(250) NOT NULL,
  PRIMARY KEY (`ID_kontaktua`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.kontaktua: ~0 rows (aproximadamente)
DELETE FROM `kontaktua`;
/*!40000 ALTER TABLE `kontaktua` DISABLE KEYS */;
INSERT INTO `kontaktua` (`ID_kontaktua`, `kontaktua`, `deskribapena`) VALUES
	(1, '112', 'Larrialdi deia'),
	(2, '011', 'Errepideetako larrialdiak');
/*!40000 ALTER TABLE `kontaktua` ENABLE KEYS */;

-- Volcando estructura para tabla bidezain_db.langilea
CREATE TABLE IF NOT EXISTS `langilea` (
  `ID_langilea` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `izena` varchar(30) NOT NULL,
  `abizena` varchar(30) NOT NULL,
  `erabiltzailea` varchar(30) NOT NULL,
  `passwordHash` longtext NOT NULL,
  `passwordSalt` longtext NOT NULL,
  `eposta` varchar(40) NOT NULL,
  `telefonoa` varchar(9) DEFAULT NULL,
  `ID_mota` smallint(5) unsigned NOT NULL DEFAULT '0',
  `aktibo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ID_langilea`),
  KEY `LAN_MOT_FK` (`ID_mota`),
  CONSTRAINT `LAN_MOT_FK` FOREIGN KEY (`ID_mota`) REFERENCES `langile_mota` (`ID_langile_mota`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.langilea: ~16 rows (aproximadamente)
DELETE FROM `langilea`;
/*!40000 ALTER TABLE `langilea` DISABLE KEYS */;
INSERT INTO `langilea` (`ID_langilea`, `izena`, `abizena`, `erabiltzailea`, `passwordHash`, `passwordSalt`, `eposta`, `telefonoa`, `ID_mota`, `aktibo`) VALUES
	(22, 'Peio', 'Joxepe', 'pejoxepe', '$2a$12$xIfrxqwa3GoWVyq4ipQw6ujKlwpQ37PWJegIyGhcBgeRC8E7P8jrG', '$2a$12$xIfrxqwa3GoWVyq4ipQw6u', 'pejoxepe@mail.com', '', 1, 1),
	(24, 'Miren', 'Amuriza', 'mamuriza', '$2a$16$fmY5JPSV0aX72WnQvxqoM.wTrnsSkmEyjZhKqcQw943eo2wo39I7O', '$2a$16$fmY5JPSV0aX72WnQvxqoM.', 'mamuriza@emaila.com', '', 1, 1),
	(25, 'Xabier', 'Lizaso', 'xalizaso', '$2a$16$lI9pFTUxx5Zcm2dMkEuw6u2gTk6UYpvrQJ2uWGyfPb6H7ZX0vsmvO', '$2a$16$lI9pFTUxx5Zcm2dMkEuw6u', 'xalizaso@mail.com', '', 1, 1),
	(26, 'Sustrai', 'Colina', 'scolina', '$2a$16$ybzimt86Fu.lYqYAqler1e.Q.dnv5Ruin6E2xk9QaHdEUYw6Ftohm', '$2a$16$ybzimt86Fu.lYqYAqler1e', 'scolina@posta.eus', '', 1, 1),
	(27, 'Julio', 'Soto', 'jsoto', '$2a$16$cF7Pd9K5bdRkHTSbWfGO/eS90.aKnqbYdJHX9YnXOv.INz0BAhyUi', '$2a$16$cF7Pd9K5bdRkHTSbWfGO/e', 'jsoto@email.com', '', 1, 1),
	(30, 'Julia', 'Sota', 'jsote', '$2a$16$zPSEQA8JZ7PMjYUJFDkUVebML2hWb8yH6K84sinmaAmrYr9Rl6j3y', '$2a$16$zPSEQA8JZ7PMjYUJFDkUVe', 'jsote@email.com', '123456789', 1, 1),
	(31, 'Julia', 'Sota', 'jsota', '$2a$16$4utG/Juta91okL6IeK2DuuGoLYEtg8XiaH0N0H5a6jLJ5y1cPO2Hu', '$2a$16$4utG/Juta91okL6IeK2Duu', 'jsota@email.com', '123456789', 1, 1),
	(32, 'Julia', 'Sota', 'aaaaa', '$2a$16$E4rGqWbxvPsiw2VqAG9p6.bcq3MbT1rhigA3r6YQUPJQpwEfIuDKC', '$2a$16$E4rGqWbxvPsiw2VqAG9p6.', 'bbbb@email.com', '123456789', 1, 1),
	(33, 'Julia', 'Sota', 'asadaaaa', '$2a$16$EDhSYmYrLs1byVBU9pUHJeLdIB4nEZ7BlZkqK3ZMPHRmoYio94g/e', '$2a$16$EDhSYmYrLs1byVBU9pUHJe', 'bbsadbb@email.com', '123456789', 1, 1),
	(34, 'Alaia', 'Martin', 'amartin', '$2a$16$tCmaECyb/NCA6cJ.VlpRGOYDMcuNF6ITy5JiSeLfnKlfN0zPAxWbe', '$2a$16$tCmaECyb/NCA6cJ.VlpRGO', 'amartin@eposta.com', '', 1, 1),
	(35, 'sdfsf', 'sfdzvg', 'dsfszd', '$2a$16$fu3K5PKqhUM.dfo2S8G0/eof/U315tZZYYy0IQJRCAEDspbSD9vLK', '$2a$16$fu3K5PKqhUM.dfo2S8G0/e', 'froga@froga.com', '', 1, 1),
	(36, 'A', 'B', 'ab', '$2a$16$DrRrOl/NF5/uDqBjaftYWeHctRXTC.ySn0Rgy3t37LdIU97qpURAa', '$2a$16$DrRrOl/NF5/uDqBjaftYWe', 'AB@mail.com', '', 1, 1),
	(37, 'bhjbluhybolugby', 'vgvutgvhjkl', 'sae<fdsgsde', '$2a$16$5K6zd.bVXEJieYAYbaYFLO58pwqr3DG/uGHh6v7Wj1gddtrdXDPIK', '$2a$16$5K6zd.bVXEJieYAYbaYFLO', 'frofgdssdga@froga.com', '', 1, 1),
	(38, 'Q', 'q', 'qqqq', '$2a$16$gyXMnIahCXqDHH3uLRPA2ubVk2fetbbBZcrj/PvLpcj1Q2sYlAFVO', '$2a$16$gyXMnIahCXqDHH3uLRPA2u', 'froga@frsdffoga.com', '', 1, 1),
	(39, 'scdz', 'xv', 'xz', '$2a$16$1zqk7nuHTGn9F9xStB3sluRIn5KM95DjxXDT3iV7nZfYvI1BWJiiu', '$2a$16$1zqk7nuHTGn9F9xStB3slu', 'froga@froga.comxxv', '', 1, 1),
	(40, 'XS<z', 'dzfdv', 'vzv', '$2a$16$IR0/9M0t6KPpAULWU.u33uFBkr1rDRXsHlVDBV0HStZpx1pHxnJVW', '$2a$16$IR0/9M0t6KPpAULWU.u33u', 'frcdfvoga@froga.com', '', 1, 1),
	(41, 'Inge', 'Ruiz de Azua', 'iruizdeazua', '$2a$16$4Tli2ZPT7Om0l/nPcI.xCuft3/WEGKZrUuhKPokWA4Mo8W43CyJ/G', '$2a$16$4Tli2ZPT7Om0l/nPcI.xCu', 'iruizdeazua@mail.eus', '', 1, 1),
	(42, 'Txema', 'Apruebanos', 'txapruebanos', '$2a$16$aP.jp5PzGtddBzPM6nJfGOqopE5gZByvpdxnmm/qZ3DYEmPxgFvq.', '$2a$16$aP.jp5PzGtddBzPM6nJfGO', 'txapruebanos@mail.com', '', 1, 1);
/*!40000 ALTER TABLE `langilea` ENABLE KEYS */;

-- Volcando estructura para tabla bidezain_db.langileak_aktiboa
CREATE TABLE IF NOT EXISTS `langileak_aktiboa` (
  `ID_aktiboa` smallint(5) unsigned NOT NULL DEFAULT '0',
  `ID_langilea` smallint(5) unsigned NOT NULL DEFAULT '0',
  `hasiera_data` varchar(50) DEFAULT NULL,
  `amaiera_data` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID_aktiboa`,`ID_langilea`),
  KEY `LAN_FK` (`ID_langilea`),
  CONSTRAINT `AK_FK` FOREIGN KEY (`ID_aktiboa`) REFERENCES `aktiboa` (`ID_aktiboa`),
  CONSTRAINT `LAN_FK` FOREIGN KEY (`ID_langilea`) REFERENCES `langilea` (`ID_langilea`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.langileak_aktiboa: ~0 rows (aproximadamente)
DELETE FROM `langileak_aktiboa`;
/*!40000 ALTER TABLE `langileak_aktiboa` DISABLE KEYS */;
/*!40000 ALTER TABLE `langileak_aktiboa` ENABLE KEYS */;

-- Volcando estructura para tabla bidezain_db.langile_mota
CREATE TABLE IF NOT EXISTS `langile_mota` (
  `ID_langile_mota` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `langile_mota` varchar(30) NOT NULL,
  `deskribapena` varchar(250) NOT NULL,
  PRIMARY KEY (`ID_langile_mota`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.langile_mota: ~4 rows (aproximadamente)
DELETE FROM `langile_mota`;
/*!40000 ALTER TABLE `langile_mota` DISABLE KEYS */;
INSERT INTO `langile_mota` (`ID_langile_mota`, `langile_mota`, `deskribapena`) VALUES
	(1, 'Gerencia', ''),
	(2, 'Marketing y ventas', ''),
	(3, 'SAT', ''),
	(4, 'Sistemas de Información', '');
/*!40000 ALTER TABLE `langile_mota` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
