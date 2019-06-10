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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.aurreikuspena: ~0 rows (aproximadamente)
DELETE FROM `aurreikuspena`;
/*!40000 ALTER TABLE `aurreikuspena` DISABLE KEYS */;
INSERT INTO `aurreikuspena` (`ID_aurreikuspena`, `ID_mota`, `errepidea`, `probintzia`, `herria`, `astekoEguna`, `data`, `lat`, `lng`, `tenperatura`, `haizea`, `prezipitazioa`, `hasieraKm`, `amaieraKm`, `itIs`) VALUES
	(1, 2, 'N-1', 'GIPUZKOA', 'Beasain', 'AL', '2019-06-01', 43.04853, -2.20650, 18, 40, 0, 0, 50, 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.erabiltzailea: ~5 rows (aproximadamente)
DELETE FROM `erabiltzailea`;
/*!40000 ALTER TABLE `erabiltzailea` DISABLE KEYS */;
INSERT INTO `erabiltzailea` (`ID_erabiltzailea`, `izena`, `abizena`, `erabiltzailea`, `passwordHash`, `passwordSalt`, `eposta`, `telefonoa`, `aktibo`) VALUES
	(1, 'Maria', 'Magdalena', 'mamagdalena', '1234567890', '1234567890', 'mamagda@jisus.com', '112', 0),
	(2, 'Aitor', 'Aitor', 'Aitor', '$2a$16$IeawqpLoJBkn6x/cu6.5zeu73JO4E8APiw4QULFETb0ftz0aR4mLK', '$2a$16$IeawqpLoJBkn6x/cu6.5ze', 'Aitor', 'Aitor', 1),
	(3, 'E', 'E', 'E', '$2a$16$TjaHfXs4dKcfrXRBqudkOO7boAY1pXABSAzs2id533EFTzPGIyBhe', '$2a$16$TjaHfXs4dKcfrXRBqudkOO', 'E', 'E', 0),
	(4, 'A', 'A', 'A', '$2a$16$fOFoDtgrnAQAw4P0jk7vIOxN76dyroa1AF7IZOOm1Ym3aw.g85k6S', '$2a$16$fOFoDtgrnAQAw4P0jk7vIO', 'A', 'A', 1),
	(5, 'q', 'q', 'q', '$2a$16$zwVZQuqN.eG95uL/6DHCXu76QOjFnFkUzIm5Ehe6Hd8YSdazhS5UC', '$2a$16$zwVZQuqN.eG95uL/6DHCXu', 'q', 'q', 1),
	(6, 'w', 'w', 'w', '$2a$16$eN2iLFPMSZeBFalqoZOZqOMsMmLG/RVNFfQCh2oT7onGF98rYNu.W', '$2a$16$eN2iLFPMSZeBFalqoZOZqO', 'w', 'w', 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.intzidentzia_aktiboa: ~4 rows (aproximadamente)
DELETE FROM `intzidentzia_aktiboa`;
/*!40000 ALTER TABLE `intzidentzia_aktiboa` DISABLE KEYS */;
INSERT INTO `intzidentzia_aktiboa` (`ID_intzidentzia`, `ID_mota`, `probintzia`, `kausa`, `herria`, `intzidentziaData`, `maila`, `errepidea`, `zentzua`, `latitudea`, `longitudea`) VALUES
	(1, 1, 'BIZKAIA', 'Telefonoa erabiltzeagatik', 'Arrigorriaga', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.20952, -2.89450),
	(2, 2, 'GIPUZKOA', 'Euria', 'Arrasate', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.20954, -2.50421),
	(3, 3, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.26332, -2.95107),
	(4, 4, 'GIPUZKOA', 'Aaa', 'Tolosa', '2019-05-30', 'Beltza', 'T-48', 'Irun', 43.13747, -2.08692);
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.intzidentzia_amaitua: ~0 rows (aproximadamente)
DELETE FROM `intzidentzia_amaitua`;
/*!40000 ALTER TABLE `intzidentzia_amaitua` DISABLE KEYS */;
/*!40000 ALTER TABLE `intzidentzia_amaitua` ENABLE KEYS */;

-- Volcando estructura para tabla bidezain_db.intzidentzia_mota
CREATE TABLE IF NOT EXISTS `intzidentzia_mota` (
  `ID_intzidentzia_mota` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `intzidentzia_mota` varchar(30) NOT NULL,
  PRIMARY KEY (`ID_intzidentzia_mota`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

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
	(9, 'Aurreikuspena');
/*!40000 ALTER TABLE `intzidentzia_mota` ENABLE KEYS */;

-- Volcando estructura para tabla bidezain_db.kontaktua
CREATE TABLE IF NOT EXISTS `kontaktua` (
  `ID_kontaktua` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `kontaktua` varchar(30) NOT NULL,
  `deskribapena` varchar(250) NOT NULL,
  PRIMARY KEY (`ID_kontaktua`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.kontaktua: ~0 rows (aproximadamente)
DELETE FROM `kontaktua`;
/*!40000 ALTER TABLE `kontaktua` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bidezain_db.langilea: ~11 rows (aproximadamente)
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
	(37, 'bhjbluhybolugby', 'vgvutgvhjkl', 'sae<fdsgsde', '$2a$16$5K6zd.bVXEJieYAYbaYFLO58pwqr3DG/uGHh6v7Wj1gddtrdXDPIK', '$2a$16$5K6zd.bVXEJieYAYbaYFLO', 'frofgdssdga@froga.com', '', 1, 1);
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
