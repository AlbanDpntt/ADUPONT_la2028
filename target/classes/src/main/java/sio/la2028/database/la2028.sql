-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : 25 août 2026 à 17:43
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `la2026`
--

-- --------------------------------------------------------
DROP TABLE IF EXISTS `athlete`;
DROP TABLE IF EXISTS `pays`;
DROP TABLE IF EXISTS `sport`;
DROP TABLE IF EXISTS `epreuve`;
DROP TABLE  IF EXISTS `athlete_epreuve`;

--
-- Structure de la table `athlete`
--

CREATE TABLE `athlete` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
    `prenom` varchar(50) NOT NULL,
  `pays_id` int(11) NOT NULL,
  `date_de_naissance` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `epreuve` (
    `id` int(11),
    `nom` varchar(50),
    `sport_id` int(11)
);

CREATE TABLE `sport` (
    `id` int(11),
    `nom` varchar(50)
);

CREATE TABLE `athlete_epreuve` (
    `athlete_id` int(11),
    `epreuve_id` int(11)
);


INSERT INTO `sport` (`id`, `nom`) VALUES
(1, 'Nage'),
(2, 'Course');

INSERT INTO `epreuve` (id, `nom`, `sport_id`) VALUES
(1, 'nage libre ', 1),
(2, 'nage 400m', 1);

INSERT INTO `athlete_epreuve` (athlete_id, epreuve_id) VALUES
    (1, 1);




INSERT INTO `athlete` (`id`, `prenom`, `nom`, `date_de_naissance`, `pays_id`) VALUES
(1, 'Teddy', 'Riner', '1989-04-07', 93),
(2, 'Simone', 'Biles', '1997-03-14', 88),
(3, 'Sydney', 'McLaughlin-Levrone', '1999-08-07', 88),
(4, 'Stephen', 'Curry', '1988-03-14', 88),
(5, 'Caroline', 'Marks', '2002-02-14', 88),
(6, 'Noah', 'Lyles', '1997-07-18', 88),
(7, 'Mikaela', 'Shiffrin', '1995-03-13', 88),
(8, 'Léon', 'Marchand', '2002-05-17', 93),
(9, 'Victor', 'Wembanyama', '2004-01-04', 93),
(10, 'Youssef', 'Krou', '1989-08-16', 93),
(11, 'Anastasia', 'Bayandina', '1996-11-01', 93),
(12, 'Joris', 'Daudet', '1991-02-12', 93);


CREATE TABLE `pays` (
  `id` int(11) NOT NULL,
    `code` varchar(3) NOT NULL,
  `nom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



LOAD DATA LOCAL INFILE 'C:/Users/alban/Documents/GitHub/la2028/src/main/java/sio/la2028/database/pays.csv'
INTO TABLE pays
FIELDS TERMINATED BY ';'          -- Spécifie que le séparateur est une tabulation
LINES TERMINATED BY '\r\n'         -- Gère correctement les retours à la ligne Windows
IGNORE 0 ROWS                      -- N'ignorez aucune ligne si votre fichier n'a pas d'entête (Id Code Nom)
(id, code, nom);


ALTER TABLE `athlete`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ath_pays` (`pays_id`);

ALTER TABLE `sport`
    ADD PRIMARY KEY (`id`);

ALTER TABLE `epreuve`
    ADD PRIMARY KEY (`id`),
 MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,
  ADD KEY `fk_epr_sport` (`sport_id`);

ALTER TABLE `athlete_epreuve`
    ADD PRIMARY KEY (`athlete_id`, `epreuve_id`);

ALTER TABLE `pays`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `athlete`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `pays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

ALTER TABLE `epreuve`
    ADD CONSTRAINT `fk_epr_sport` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`id`);

ALTER TABLE `athlete`
  ADD CONSTRAINT `fk_ath_pays` FOREIGN KEY (`pays_id`) REFERENCES `pays` (`id`);
COMMIT;
