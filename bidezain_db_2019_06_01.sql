DROP DATABASE IF EXISTS bidezain_db;
CREATE DATABASE bidezain_db;

USE bidezain_db;

DROP TABLE IF EXISTS erabiltzailea;
CREATE TABLE erabiltzailea(
	ID_erabiltzailea SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
	izena VARCHAR(30) NOT NULL,
	abizena VARCHAR(30) NOT NULL,
	erabiltzailea VARCHAR(30) NOT NULL,
	passwordHash BINARY(64) NOT NULL,
	passwordSalt BINARY(64) NOT NULL,
	eposta VARCHAR(40) NOT NULL,
	telefonoa VARCHAR(9) DEFAULT NULL,
	aktibo BOOL DEFAULT TRUE,
   PRIMARY KEY (`ID_erabiltzailea`)
);

DROP TABLE IF EXISTS kontaktua;
CREATE TABLE kontaktua(
	ID_kontaktua SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
	kontaktua VARCHAR(30) NOT NULL,
	deskribapena VARCHAR(250) NOT NULL,
   PRIMARY KEY (`ID_kontaktua`)
);



DROP TABLE IF EXISTS intzidentzia_mota;
CREATE TABLE intzidentzia_mota(
	ID_intzidentzia_mota SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
	intzidentzia_mota VARCHAR(30) NOT NULL,
   PRIMARY KEY (`ID_intzidentzia_mota`)
);


DROP TABLE IF EXISTS aurreikuspena;
CREATE TABLE aurreikuspena(
	ID_aurreikuspena SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
	ID_mota SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	errepidea VARCHAR(30) NOT NULL,
	probintzia ENUM ('GIPUZKOA','BIZKAIA','ARABA') NOT NULL,
	herria VARCHAR(150) NOT NULL,
	astekoEguna ENUM('AL','AR','AZ','OG','OR','LR','IG'),
	`data` VARCHAR(30) NOT NULL,
	lat DECIMAL(8,5) NOT NULL,
	lng DECIMAL(8,5) NOT NULL,
	tenperatura FLOAT NOT NULL,
	haizea FLOAT NOT NULL,
	prezipitazioa FLOAT NOT NULL,
	hasieraKm FLOAT NOT NULL,
	amaieraKm FLOAT NOT NULL,
	itIs BOOL DEFAULT TRUE,
   PRIMARY KEY (`ID_aurreikuspena`),
   KEY `AU_MOT_FK` (`ID_mota`),
   CONSTRAINT `AU_MOT_FK` FOREIGN KEY (`ID_mota`) REFERENCES `intzidentzia_mota` (`ID_intzidentzia_mota`)
);

DROP TABLE IF EXISTS intzidentzia_amaitua;
CREATE TABLE intzidentzia_amaitua(
	ID_intzidentzia SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
	ID_mota SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	probintzia ENUM ('GIPUZKOA','BIZKAIA','ARABA') NOT NULL,
	kausa VARCHAR(300) NOT NULL,
	herria VARCHAR(150) NOT NULL,
	intzidentziaData VARCHAR(30) NOT NULL,
	maila ENUM ('Beltza','Horia','Zuria') NOT NULL,
	errepidea VARCHAR(150) NOT NULL,
	zentzua VARCHAR(150) NOT NULL,
	latitudea DECIMAL(8,5) NOT NULL,
	longitudea DECIMAL(8,5) NOT NULL,
   PRIMARY KEY (`ID_intzidentzia`),
	KEY `IN_AM_MOT_FK` (`ID_mota`),
   CONSTRAINT `IN_AM_MOT_FK` FOREIGN KEY (`ID_mota`) REFERENCES `intzidentzia_mota` (`ID_intzidentzia_mota`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS intzidentzia_aktiboa;
CREATE TABLE intzidentzia_aktiboa(
	ID_intzidentzia SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
	ID_mota SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	probintzia ENUM ('GIPUZKOA','BIZKAIA','ARABA') NOT NULL,
	kausa VARCHAR(300) NOT NULL,
	herria VARCHAR(150) NOT NULL,
	intzidentziaData VARCHAR(30) NOT NULL,
	maila ENUM ('Beltza','Horia','Zuria') NOT NULL,
	errepidea VARCHAR(150) NOT NULL,
	zentzua VARCHAR(150) NOT NULL,
	latitudea DECIMAL(8,5) NOT NULL,
	longitudea DECIMAL(8,5) NOT NULL,
   PRIMARY KEY (`ID_intzidentzia`),
	KEY `IN_AK_MOT_FK` (`ID_mota`),
   CONSTRAINT `IN_AK_MOT_FK` FOREIGN KEY (`ID_mota`) REFERENCES `intzidentzia_mota` (`ID_intzidentzia_mota`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS langile_mota;
CREATE TABLE langile_mota(
	ID_langile_mota SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
	langile_mota VARCHAR(30) NOT NULL,
	deskribapena VARCHAR(250) NOT NULL,
   PRIMARY KEY (`ID_langile_mota`)
);

DROP TABLE IF EXISTS langilea;
CREATE TABLE langilea(
	ID_langilea SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
	izena VARCHAR(30) NOT NULL,
	abizena VARCHAR(30) NOT NULL,
	erabiltzailea VARCHAR(30) NOT NULL,
	passwordHash BINARY(64) NOT NULL,
	passwordSalt BINARY(64) NOT NULL,
	eposta VARCHAR(40) NOT NULL,
	telefonoa VARCHAR(9) DEFAULT NULL,
	ID_mota SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	aktibo BOOL DEFAULT TRUE,
   PRIMARY KEY (`ID_langilea`),
	KEY `LAN_MOT_FK` (`ID_mota`),
   CONSTRAINT `LAN_MOT_FK` FOREIGN KEY (`ID_mota`) REFERENCES `langile_mota` (`ID_langile_mota`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS aktibo_mota;
CREATE TABLE aktibo_mota(
	ID_aktibo_mota SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
	aktibo_mota VARCHAR(30) NOT NULL,
	deskribapena VARCHAR(250) NOT NULL,
   PRIMARY KEY (`ID_aktibo_mota`)
);

DROP TABLE IF EXISTS aktiboa;
CREATE TABLE aktiboa(
	ID_aktiboa SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
	ID_mota SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
   PRIMARY KEY (`ID_aktiboa`),
	KEY `AK_MOT_FK` (`ID_mota`),
   CONSTRAINT `AK_MOT_FK` FOREIGN KEY (`ID_mota`) REFERENCES `aktibo_mota` (`ID_aktibo_mota`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS langileak_aktiboa;
CREATE TABLE langileak_aktiboa(
	ID_aktiboa SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	ID_langilea SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
   PRIMARY KEY (`ID_aktiboa`, `ID_langilea`),
   CONSTRAINT `AK_FK` FOREIGN KEY (`ID_aktiboa`) REFERENCES `aktiboa` (`ID_aktiboa`),
   CONSTRAINT `LAN_FK` FOREIGN KEY (`ID_langilea`) REFERENCES `langilea` (`ID_langilea`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `erabiltzailea` (`ID_erabiltzailea`, `izena`, `abizena`, `erabiltzailea`, `passwordHash`, `passwordSalt`, `eposta`, `telefonoa`, `aktibo`) VALUES (1, 'Jon', 'Braun', 'indiotxikia', 0x31000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 0x31000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 'jonB@gmail.com', '324124514', 1);
INSERT INTO `erabiltzailea` (`ID_erabiltzailea`, `izena`, `abizena`, `erabiltzailea`, `passwordHash`, `passwordSalt`, `eposta`, `telefonoa`, `aktibo`) VALUES (2, 'Ane', 'Barrero', 'abarrero', 0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 'abarrero@gmail.com', '456123478', 1);
INSERT INTO `erabiltzailea` (`ID_erabiltzailea`, `izena`, `abizena`, `erabiltzailea`, `passwordHash`, `passwordSalt`, `eposta`, `telefonoa`, `aktibo`) VALUES (3, 'Aitor', 'Barrero', 'aibarrero', 0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 'aibarrero@gmail.com', '456123478', 1);
INSERT INTO `erabiltzailea` (`ID_erabiltzailea`, `izena`, `abizena`, `erabiltzailea`, `passwordHash`, `passwordSalt`, `eposta`, `telefonoa`, `aktibo`) VALUES (4, 'Leire', 'Barrero', 'lbarrero', 0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 'lbarrero@gmail.com', '456123478', 1);
INSERT INTO `erabiltzailea` (`ID_erabiltzailea`, `izena`, `abizena`, `erabiltzailea`, `passwordHash`, `passwordSalt`, `eposta`, `telefonoa`, `aktibo`) VALUES (5, 'Inge', 'Barrero', 'ibarrero', 0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 'ibarrero@gmail.com', '456123478', 1);
INSERT INTO `erabiltzailea` (`ID_erabiltzailea`, `izena`, `abizena`, `erabiltzailea`, `passwordHash`, `passwordSalt`, `eposta`, `telefonoa`, `aktibo`) VALUES (6, 'Joseba', 'Barrero', 'jbarrero', 0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 'jbarrero@gmail.com', '456123478', 1);

INSERT INTO intzidentzia_mota (intzidentzia_mota) VALUES ("Meteorológica"), ("Accidente"), ("Retención"), ("Seguridad vial"),("Otras incidencias"),("Puertos de montaña"), ("Vialidad invernal tramos"),("Pruebas deportivas");

INSERT INTO `intzidentzia_aktiboa` (`ID_intzidentzia`, `ID_mota`, `probintzia`, `kausa`, `herria`, `intzidentziaData`, `maila`, `errepidea`, `zentzua`, `latitudea`, `longitudea`) VALUES (1, 1, 'BIZKAIA', 'Telefonoa erabiltzeagatik', 'Arrigorriaga', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.20952, -2.89450);
INSERT INTO `intzidentzia_aktiboa` (`ID_intzidentzia`, `ID_mota`, `probintzia`, `kausa`, `herria`, `intzidentziaData`, `maila`, `errepidea`, `zentzua`, `latitudea`, `longitudea`) VALUES (2, 2, 'GIPUZKOA', 'Euria', 'Arrasate', '2019-05-30', 'Zuria', 'N-2', 'Donostia', 43.20954, -2.50421);
INSERT INTO `intzidentzia_aktiboa` (`ID_intzidentzia`, `ID_mota`, `probintzia`, `kausa`, `herria`, `intzidentziaData`, `maila`, `errepidea`, `zentzua`, `latitudea`, `longitudea`) VALUES (3, 3, 'BIZKAIA', 'Ataskoa', 'Bilbo', '2019-05-30', 'Horia', 'A-4', 'Bilbo', 43.26332, -2.95107);
INSERT INTO `intzidentzia_aktiboa` (`ID_intzidentzia`, `ID_mota`, `probintzia`, `kausa`, `herria`, `intzidentziaData`, `maila`, `errepidea`, `zentzua`, `latitudea`, `longitudea`) VALUES (4, 4, 'GIPUZKOA', 'Aaa', 'Tolosa', '2019-05-30', 'Beltza', 'T-48', 'Irun', 43.13747, -2.08692);


INSERT INTO `langile_mota` (`ID_langile_mota`, `langile_mota`, `deskribapena`) VALUES (1, 'Gerencia', '');
INSERT INTO `langile_mota` (`ID_langile_mota`, `langile_mota`, `deskribapena`) VALUES (2, 'Marketing y ventas', '');
INSERT INTO `langile_mota` (`ID_langile_mota`, `langile_mota`, `deskribapena`) VALUES (3, 'SAT', '');
INSERT INTO `langile_mota` (`ID_langile_mota`, `langile_mota`, `deskribapena`) VALUES (4, 'Sistemas de Información', '');


INSERT INTO langilea (izena,abizena,erabiltzailea,passwordHash,passwordSalt,eposta,telefonoa,ID_mota) 
VALUES ("Amaia", "Samaniego", "asamaniego",0x31000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 0x31000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, "asamaniego@gmail.com", "687154211", 1), 
("Enara", "Salinas", "esalinas",0x31000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 0x31000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, "esalinas@gmail.com", "247154211", 2)