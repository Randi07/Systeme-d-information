-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 08 Décembre 2016 à 18:20
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `sirmania`
--
CREATE DATABASE IF NOT EXISTS `sirmania` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `sirmania`;

-- --------------------------------------------------------

--
-- Structure de la table `commune`
--

DROP TABLE IF EXISTS `commune`;
CREATE TABLE IF NOT EXISTS `commune` (
  `ID_COM` varchar(10) NOT NULL,
  `NOM_COM` varchar(24) DEFAULT NULL,
  `HISTORIQUE_COM` varchar(800) DEFAULT NULL,
  `DRN7` varchar(5) DEFAULT NULL,
  `DRN35` varchar(5) DEFAULT NULL,
  `DRN41` varchar(5) DEFAULT NULL,
  `DDIST` varchar(5) DEFAULT NULL,
  `DREG` varchar(5) DEFAULT NULL,
  `SUPERFICIE` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_COM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vider la table avant d'insérer `commune`
--

TRUNCATE TABLE `commune`;
--
-- Contenu de la table `commune`
--

INSERT INTO `commune` (`ID_COM`, `NOM_COM`, `HISTORIQUE_COM`, `DRN7`, `DRN35`, `DRN41`, `DDIST`, `DREG`, `SUPERFICIE`) VALUES
('ABS_C1', 'Alakamisy Ambohijato', 'Niforona taminy 1923', '', '', '', '', '', 0),
('ABS_C12', 'Fahizay', '', '', '', '', '', '', 0),
('ABS_C2', 'Ambalamanakana', '', '', '', '', '', '', 0),
('ABS_C3', 'Ambatofitorahana', '', '', '', '', '', '', 0),
('ABS_C7', 'Ambositra', '', '', '', '', '', '', 0),
('ABT_C1', 'Ambatofinandrahana', '', '', '', '', '', '', 0),
('ABT_C2', 'Ambatomifanongoa', '', '', '', '', '', '', 0),
('ABT_C3', 'Ambondromisotra', '', '', '', '', '', '', 0),
('ABT_C4', 'Amboropotsy', '', '', '', '', '', '', 0),
('ABT_C5', 'Fenoarivo', '', '', '', '', '', '', 0),
('FND_C1', 'Alakamisy Ambohimahazo', '', '', '', '', '', '', 0),
('FND_C10', 'Sahamadio Fisakana', '', '', '', '', '', '', 0),
('FND_C2', 'Ankarinoro', 'inconnue', '', '', '', '', '', 0),
('FND_C3', 'Betsimisotra', '', '', '', '', '', '', 0),
('FND_C4', 'Fandriana', '', '', '', '', '', '', 0),
('FND_C5', 'Fiadanana', '', '', '', '', '', '', 0),
('FND_C6', 'Imito', '', '', '', '', '', '', 0),
('FND_C7', 'Mahazoarivo', '', '', '', '', '', '', 0),
('FND_C8', 'Milamaina', '', '', '', '', '', '', 0),
('FND_C9', 'Miarinavaratra', '', '', '', '', '', '', 0),
('MND_C1', 'AMBATOMARINA', '', '', '', '', '', '', 6),
('MND_C2', 'Ambohimahazo', '', '', '', '', '', '', 0),
('MND_C3', 'Ambohimilanja', '', '', '', '', '', '', 0),
('MND_C4', 'Ambohipo', '', '', '', '', '', '', 0),
('MND_C5', 'Ambovombe Centre', 'inconnu', '', '', '', '', '', 13);

-- --------------------------------------------------------

--
-- Structure de la table `district`
--

DROP TABLE IF EXISTS `district`;
CREATE TABLE IF NOT EXISTS `district` (
  `ID_DIST` char(32) NOT NULL,
  `NOM_DIST` char(24) NOT NULL,
  `HISTORIQUE_DIST` char(32) DEFAULT NULL,
  `SUPERFICIE` int(6) NOT NULL,
  PRIMARY KEY (`ID_DIST`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vider la table avant d'insérer `district`
--

TRUNCATE TABLE `district`;
--
-- Contenu de la table `district`
--

INSERT INTO `district` (`ID_DIST`, `NOM_DIST`, `HISTORIQUE_DIST`, `SUPERFICIE`) VALUES
('ABS', 'Ambositra', 'Chef Lieu de Region', 2939),
('ABT', 'Ambatofinandrahana', 'Inconnue', 10283),
('FND', 'Fandriana', 'Inconnue', 2351),
('MND', 'Manandriana', 'inconnue', 923);

-- --------------------------------------------------------

--
-- Structure de la table `fokontany`
--

DROP TABLE IF EXISTS `fokontany`;
CREATE TABLE IF NOT EXISTS `fokontany` (
  `ID_FKT` char(32) NOT NULL,
  `NOM_FKT` char(24) NOT NULL,
  `HISTORIQUE_FKT` char(32) DEFAULT NULL,
  `SUPERFICIE` int(11) DEFAULT '0',
  PRIMARY KEY (`ID_FKT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vider la table avant d'insérer `fokontany`
--

TRUNCATE TABLE `fokontany`;
--
-- Contenu de la table `fokontany`
--

INSERT INTO `fokontany` (`ID_FKT`, `NOM_FKT`, `HISTORIQUE_FKT`, `SUPERFICIE`) VALUES
('ABT_C1_F1', 'Ikalalao', '', 4),
('FND_C1_F1', 'Alakamisy', 'inconnue', 12),
('FND_C1_F2', 'Ambohimahazo', '', 0),
('FND_C2_F1', 'Ampahibe', '', 0),
('FND_C2_F8', 'Irondroala', '', 0),
('MND_C1_F1', 'Tsiakato', '', 0),
('MND_C1_F2', 'Ambohibary', 'tsy fantatra', 0),
('MND_C2_F1', 'Ambohimahazo', '', 0),
('MND_C2_F2', 'Antapia', '', 0),
('MND_C5_F1', 'Ambanikalalao', 'inconnu', 2);

-- --------------------------------------------------------

--
-- Structure de la table `population`
--

DROP TABLE IF EXISTS `population`;
CREATE TABLE IF NOT EXISTS `population` (
  `ID_POPULATION` int(11) NOT NULL AUTO_INCREMENT,
  `ID_FKT` char(32) NOT NULL,
  `ZERO` int(11) DEFAULT NULL,
  `SIX` int(11) DEFAULT NULL,
  `DIXHUIT` int(11) DEFAULT NULL,
  `SOIXANTE` int(11) DEFAULT NULL,
  `EFFECTIF_MASCULAIN` int(11) DEFAULT NULL,
  `EFFECTIF_FEMININ` int(11) DEFAULT NULL,
  `EFFECTIF_TOTAL` int(11) DEFAULT NULL,
  `ANNEE` int(4) NOT NULL DEFAULT '2016',
  PRIMARY KEY (`ID_POPULATION`),
  UNIQUE KEY `as` (`ANNEE`,`ID_FKT`),
  KEY `I_FK_POPULATION_FOKONTANY` (`ID_FKT`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Vider la table avant d'insérer `population`
--

TRUNCATE TABLE `population`;
--
-- Contenu de la table `population`
--

INSERT INTO `population` (`ID_POPULATION`, `ID_FKT`, `ZERO`, `SIX`, `DIXHUIT`, `SOIXANTE`, `EFFECTIF_MASCULAIN`, `EFFECTIF_FEMININ`, `EFFECTIF_TOTAL`, `ANNEE`) VALUES
(1, 'FND_C1_F2', 12, 14, 15, 10, 30, 21, 51, 2007),
(2, 'FND_C1_F1', 10, 11, 12, 13, 23, 22, 46, 2008),
(3, 'MND_C1_F2', 13, 14, 15, 17, 29, 30, 59, 2009),
(4, 'MND_C2_F1', 50, 40, 55, 90, 133, 102, 235, 2010),
(5, 'MND_C1_F1', 123, 123, 78, 98, 200, 222, 422, 2011),
(6, 'FND_C1_F1', 22, 23, 33, 44, 60, 66, 122, 2012),
(7, 'FND_C2_F8', 213, 123, 123, 123, 300, 282, 582, 2013),
(8, 'FND_C1_F1', 123, 421, 123, 123, 385, 405, 790, 2014),
(9, 'MND_C5_F1', 100, 70, 34, 12, 80, 136, 216, 2015),
(10, 'ABT_C1_F1', 23, 45, 12, 3, 38, 45, 83, 2016);

-- --------------------------------------------------------

--
-- Structure de la table `region`
--

DROP TABLE IF EXISTS `region`;
CREATE TABLE IF NOT EXISTS `region` (
  `ID_REG` varchar(10) NOT NULL,
  `NOM_REG` varchar(24) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `HISTORIQUE_REG` varchar(500) DEFAULT NULL,
  `DRN7` varchar(5) DEFAULT NULL,
  `DRN35` varchar(5) DEFAULT NULL,
  `DRN41` varchar(5) DEFAULT NULL,
  `DDIST` varchar(5) DEFAULT NULL,
  `SIEGE` varchar(40) DEFAULT NULL,
  `SUPERFICIE` int(6) NOT NULL,
  `ANNEE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_REG`),
  UNIQUE KEY `as1` (`ANNEE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vider la table avant d'insérer `region`
--

TRUNCATE TABLE `region`;
--
-- Contenu de la table `region`
--

INSERT INTO `region` (`ID_REG`, `NOM_REG`, `HISTORIQUE_REG`, `DRN7`, `DRN35`, `DRN41`, `DDIST`, `SIEGE`, `SUPERFICIE`, `ANNEE`) VALUES
('203', 'Amoroni Mania', 'Pays', '00', '35', '02', '00', 'Ambositra', 16497, 2016);

-- --------------------------------------------------------

--
-- Structure de la table `sanitaire`
--

DROP TABLE IF EXISTS `sanitaire`;
CREATE TABLE IF NOT EXISTS `sanitaire` (
  `ID_SANITAIRE` int(11) NOT NULL AUTO_INCREMENT,
  `ID_FKT` char(32) NOT NULL,
  `INFRAS_SANITAIRE` char(32) DEFAULT NULL,
  `NOMBRE_INFRAS_SANITAIRE` int(11) DEFAULT '0',
  `NATURE_INFRAS_SANITAIRE` char(32) DEFAULT NULL,
  `EMPLOYES` char(32) DEFAULT NULL,
  `NOMBRE_EMPLOYES` int(11) DEFAULT '0',
  `ETAT_INFRAS_SANITAIRE` char(32) DEFAULT NULL,
  `REMARQUE` varchar(200) DEFAULT NULL,
  `ANNEE` int(4) NOT NULL DEFAULT '2016',
  PRIMARY KEY (`ID_SANITAIRE`),
  UNIQUE KEY `as2` (`ID_FKT`,`ANNEE`),
  KEY `I_FK_SANITAIRE_FOKONTANY` (`ID_FKT`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Vider la table avant d'insérer `sanitaire`
--

TRUNCATE TABLE `sanitaire`;
--
-- Contenu de la table `sanitaire`
--

INSERT INTO `sanitaire` (`ID_SANITAIRE`, `ID_FKT`, `INFRAS_SANITAIRE`, `NOMBRE_INFRAS_SANITAIRE`, `NATURE_INFRAS_SANITAIRE`, `EMPLOYES`, `NOMBRE_EMPLOYES`, `ETAT_INFRAS_SANITAIRE`, `REMARQUE`, `ANNEE`) VALUES
(2, 'MND_C1_F1', 'Item 4', 32, 'Prive', 'Item 2', 43, 'Moyenne', 'Tsisy', 2015),
(3, 'MND_C5_F1', 'Item 1', 3, 'Publique', 'Item 1', 5, 'Mauvaise', 'rien', 2015);

-- --------------------------------------------------------

--
-- Structure de la table `scolaire`
--

DROP TABLE IF EXISTS `scolaire`;
CREATE TABLE IF NOT EXISTS `scolaire` (
  `ID_SCOLAIRE` char(32) NOT NULL,
  `ID_FKT` char(32) NOT NULL,
  `CATEG_SCOLAIRE` char(32) DEFAULT NULL,
  `NATURE_SCOLAIRE` char(32) DEFAULT NULL,
  `NOMBRE_SCOLAIRE` char(32) DEFAULT NULL,
  `ETAT_SCOLAIRE` char(32) DEFAULT NULL,
  `ELEVE_MASCULAIN` char(32) DEFAULT NULL,
  `ELEVE_FEMININ` char(32) DEFAULT NULL,
  `EFFECTIF_TOTAL` char(32) DEFAULT NULL,
  `ANNEE_SCOLAIRE` char(32) DEFAULT NULL,
  `ANNEE` int(4) NOT NULL DEFAULT '2016',
  PRIMARY KEY (`ID_SCOLAIRE`),
  UNIQUE KEY `as3` (`ID_FKT`,`ANNEE`,`ANNEE_SCOLAIRE`),
  KEY `I_FK_SCOLAIRE_FOKONTANY` (`ID_FKT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vider la table avant d'insérer `scolaire`
--

TRUNCATE TABLE `scolaire`;
--
-- Contenu de la table `scolaire`
--

INSERT INTO `scolaire` (`ID_SCOLAIRE`, `ID_FKT`, `CATEG_SCOLAIRE`, `NATURE_SCOLAIRE`, `NOMBRE_SCOLAIRE`, `ETAT_SCOLAIRE`, `ELEVE_MASCULAIN`, `ELEVE_FEMININ`, `EFFECTIF_TOTAL`, `ANNEE_SCOLAIRE`, `ANNEE`) VALUES
('1', 'FND_C1_F1', 'Prescolaire', 'Publique', '12', 'Mauvaise', '13', '14', '27', '2012-2013', 2016);

-- --------------------------------------------------------

--
-- Structure de la table `securite`
--

DROP TABLE IF EXISTS `securite`;
CREATE TABLE IF NOT EXISTS `securite` (
  `ID_SECURITE` char(32) NOT NULL,
  `ID_FKT` char(32) NOT NULL,
  `CATEG_SEC` char(32) DEFAULT NULL,
  `NOMBRE` char(32) DEFAULT NULL,
  `REMARQUE` char(32) DEFAULT NULL,
  `ANNEE` int(4) NOT NULL DEFAULT '2016',
  PRIMARY KEY (`ID_SECURITE`),
  UNIQUE KEY `as4` (`ID_FKT`,`ANNEE`),
  KEY `I_FK_SECURITE_FOKONTANY` (`ID_FKT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vider la table avant d'insérer `securite`
--

TRUNCATE TABLE `securite`;
--
-- Contenu de la table `securite`
--

INSERT INTO `securite` (`ID_SECURITE`, `ID_FKT`, `CATEG_SEC`, `NOMBRE`, `REMARQUE`, `ANNEE`) VALUES
('1', 'FND_C2_F1', 'Miaramila', '2', 'Tsy ampy ny poste avance', 2016),
('2', 'FND_C1_F1', 'Quartier Mobile', '4', 'Tsy misy', 2016);

-- --------------------------------------------------------

--
-- Structure de la table `sportive`
--

DROP TABLE IF EXISTS `sportive`;
CREATE TABLE IF NOT EXISTS `sportive` (
  `ID_SPORT` char(32) NOT NULL,
  `ID_FKT` char(32) NOT NULL,
  `CATEG_SPORT` char(32) DEFAULT NULL,
  `EXISTANCE_TERRAIN` char(32) DEFAULT NULL,
  `ETAT_TERRAIN` char(32) DEFAULT NULL,
  `NOMBRE_TERRAIN` int(11) DEFAULT NULL,
  `REMARQUE_SPORT` char(32) DEFAULT NULL,
  `ANNEE` int(4) NOT NULL DEFAULT '2016',
  PRIMARY KEY (`ID_SPORT`),
  UNIQUE KEY `as6` (`ID_FKT`,`ANNEE`),
  KEY `I_FK_SPORTIVE_FOKONTANY` (`ID_FKT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vider la table avant d'insérer `sportive`
--

TRUNCATE TABLE `sportive`;
--
-- Contenu de la table `sportive`
--

INSERT INTO `sportive` (`ID_SPORT`, `ID_FKT`, `CATEG_SPORT`, `EXISTANCE_TERRAIN`, `ETAT_TERRAIN`, `NOMBRE_TERRAIN`, `REMARQUE_SPORT`, `ANNEE`) VALUES
('1', 'FND_C2_F1', 'Item 2', 'Basket', 'Moyenne', 3, 'Tsy misy', 2015),
('2', 'FND_C1_F2', 'Item 2', 'Volley', 'Bonne', 1, 'Mbola tena Tsara', 2016),
('3', 'FND_C2_F1', 'Item 2', 'Foot', 'Mauvaise', 2, 'Mila Fanavaozana', 2016);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `NOM_UT` varchar(40) NOT NULL,
  `MOT_PASS_UT` varchar(40) NOT NULL,
  PRIMARY KEY (`NOM_UT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vider la table avant d'insérer `utilisateur`
--

TRUNCATE TABLE `utilisateur`;
--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`NOM_UT`, `MOT_PASS_UT`) VALUES
('OPS', '2016'),
('SIRAMM', 'SIRAMM');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `population`
--
ALTER TABLE `population`
  ADD CONSTRAINT `population_ibfk_1` FOREIGN KEY (`ID_FKT`) REFERENCES `fokontany` (`ID_FKT`);

--
-- Contraintes pour la table `sanitaire`
--
ALTER TABLE `sanitaire`
  ADD CONSTRAINT `sanitaire_ibfk_1` FOREIGN KEY (`ID_FKT`) REFERENCES `fokontany` (`ID_FKT`);

--
-- Contraintes pour la table `scolaire`
--
ALTER TABLE `scolaire`
  ADD CONSTRAINT `scolaire_ibfk_1` FOREIGN KEY (`ID_FKT`) REFERENCES `fokontany` (`ID_FKT`);

--
-- Contraintes pour la table `securite`
--
ALTER TABLE `securite`
  ADD CONSTRAINT `securite_ibfk_1` FOREIGN KEY (`ID_FKT`) REFERENCES `fokontany` (`ID_FKT`);

--
-- Contraintes pour la table `sportive`
--
ALTER TABLE `sportive`
  ADD CONSTRAINT `sportive_ibfk_1` FOREIGN KEY (`ID_FKT`) REFERENCES `fokontany` (`ID_FKT`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
