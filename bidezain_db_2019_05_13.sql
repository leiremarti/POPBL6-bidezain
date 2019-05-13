CREATE DATABASE bidezain_db;

USE bidezain_db;

DROP TABLE IF EXISTS erabiltzailea;
CREATE TABLE erabiltzailea(
	ID_erabiltzailea SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	izena VARCHAR(30) NOT NULL,
	abizena VARCHAR(30) NOT NULL,
	erabiltzailea VARCHAR(30) NOT NULL,
	passwordHash BINARY(64) NOT NULL,
	passwordSalt BINARY(64) NOT NULL,
	eposta VARCHAR(40) NOT NULL,
	telefonoa VARCHAR(9) DEFAULT NULL,
   PRIMARY KEY (`ID_erabiltzailea`)
);

DROP TABLE IF EXISTS kontaktuak;
CREATE TABLE kontaktuak(
	ID_kontaktua SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	kontaktua VARCHAR(30) NOT NULL,
	deskribapena VARCHAR(250) NOT NULL,
   PRIMARY KEY (`ID_kontaktua`)
);

DROP TABLE IF EXISTS aurreikuspenak;
CREATE TABLE aurreikuspenak(
	ID_aurreikuspena SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	azalera VARCHAR(30) NOT NULL,
	probabilitatea FLOAT NOT NULL,
   PRIMARY KEY (`ID_aurreikuspena`)
);

DROP TABLE IF EXISTS intzidentzia_motak;
CREATE TABLE intzidentzia_motak(
	ID_intzidentzia_mota SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	intzidentzia_mota VARCHAR(30) NOT NULL,
	deskribapena VARCHAR(250) NOT NULL,
   PRIMARY KEY (`ID_intzidentzia_mota`)
);

DROP TABLE IF EXISTS intzidentziak;
CREATE TABLE intzidentziak(
	ID_intzidentzia SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	intzidentziaData VARCHAR(30) NOT NULL,
	deskribapena VARCHAR(300) NOT NULL,
	latitudea DECIMAL(8,2) NOT NULL,
	longitudea DECIMAL(8,2) NOT NULL,
	amaituta BIT NOT NULL DEFAULT 0,
	ID_mota SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
   PRIMARY KEY (`ID_intzidentzia`),
	KEY `IN_MOT_FK` (`ID_mota`),
   CONSTRAINT `IN_MOT_FK` FOREIGN KEY (`ID_mota`) REFERENCES `intzidentzia_motak` (`ID_intzidentzia_mota`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS langile_motak;
CREATE TABLE langile_motak(
	ID_langile_mota SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	langile_mota VARCHAR(30) NOT NULL,
	deskribapena VARCHAR(250) NOT NULL,
   PRIMARY KEY (`ID_langile_mota`)
);

DROP TABLE IF EXISTS langileak;
CREATE TABLE langileak(
	ID_langilea SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	izena VARCHAR(30) NOT NULL,
	abizena VARCHAR(30) NOT NULL,
	erabiltzailea DECIMAL(8,2) NOT NULL,
	passwordHash BINARY(64) NOT NULL,
	passwordSalt BINARY(64) NOT NULL,
	eposta VARCHAR(40) NOT NULL,
	telefonoa VARCHAR(9) DEFAULT NULL,
	ID_mota SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
   PRIMARY KEY (`ID_langilea`),
	KEY `LAN_MOT_FK` (`ID_mota`),
   CONSTRAINT `LAN_MOT_FK` FOREIGN KEY (`ID_mota`) REFERENCES `langile_motak` (`ID_langile_mota`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS aktibo_motak;
CREATE TABLE aktibo_motak(
	ID_aktibo_mota SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	aktibo_mota VARCHAR(30) NOT NULL,
	deskribapena VARCHAR(250) NOT NULL,
   PRIMARY KEY (`ID_aktibo_mota`)
);

DROP TABLE IF EXISTS aktiboak;
CREATE TABLE aktiboak(
	ID_aktiboa SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	ID_mota SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
   PRIMARY KEY (`ID_aktiboa`),
	KEY `AK_MOT_FK` (`ID_mota`),
   CONSTRAINT `AK_MOT_FK` FOREIGN KEY (`ID_mota`) REFERENCES `aktibo_motak` (`ID_aktibo_mota`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS langileak_aktiboak;
CREATE TABLE langileak_aktiboak(
	ID_aktiboa SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	ID_langilea SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
   PRIMARY KEY (`ID_aktiboa`, `ID_langilea`),
   CONSTRAINT `AK_FK` FOREIGN KEY (`ID_aktiboa`) REFERENCES `aktiboak` (`ID_aktiboa`),
   CONSTRAINT `LAN_FK` FOREIGN KEY (`ID_langilea`) REFERENCES `langileak` (`ID_langilea`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
