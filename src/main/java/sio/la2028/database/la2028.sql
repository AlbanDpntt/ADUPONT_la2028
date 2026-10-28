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

--
-- Structure de la table `athlete`
--

CREATE TABLE `pays` (
                        `id` int(11) NOT NULL,
                        `code` varchar(3) NOT NULL,
                        `nom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `athlete` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `pays_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `athlete`
--

INSERT INTO `athlete` (`id`, `nom`, `pays_id`) VALUES
(1, 'Rinner', 1),
(2, 'Biles', 2);

<<<<<<< Updated upstream
-- --------------------------------------------------------
=======
CREATE TABLE `athlete_epreuve` (
    `athlete_id` int(11),
    `epreuve_id` int(11),
    `position` int(5)
);


-- Insertion de sports supplémentaires si besoin
INSERT INTO `sport` (`id`, `nom`) VALUES
                                      (3, 'Gymnastique'),
                                      (4, 'Basket-ball'),
                                      (5, 'Judo'),
                                      (6, 'Cyclisme BMX');

-- Insertion de nouvelles épreuves reliées aux sports
INSERT INTO `epreuve` (`id`, `nom`, `sport_id`) VALUES
                                                    (3, '100m haies', 2),
                                                    (4, 'Concours général individuel', 3),
                                                    (5, 'Tournoi masculin 5x5', 4),
                                                    (6, 'Poids lourds (+100kg)', 5),
                                                    (7, 'BMX Racing', 6);

INSERT INTO `athlete_epreuve` (athlete_id, epreuve_id, position) VALUES
    (1, 1, 1);


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
>>>>>>> Stashed changes

--
-- Structure de la table `pays`
--

<<<<<<< Updated upstream
CREATE TABLE `pays` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `pays`
--

INSERT INTO `pays` (`id`, `nom`) VALUES
(1, 'France'),
(2, 'Etats-Unis');
=======
>>>>>>> Stashed changes

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `athlete`
--
ALTER TABLE `athlete`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ath_pays` (`pays_id`),
  ADD KEY `fk_ath_athlete_epreuve` (`id`);

<<<<<<< Updated upstream
--
-- Index pour la table `pays`
--
=======
ALTER TABLE `sport`
    ADD PRIMARY KEY (`id`);

ALTER TABLE `epreuve`
    ADD PRIMARY KEY (`id`),
 MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,
  ADD KEY `fk_epr_sport` (`sport_id`),
  ADD KEY `fk_epr_athlete_epreuve` (`id`);

ALTER TABLE `athlete_epreuve`
    ADD PRIMARY KEY (`athlete_id`, `epreuve_id`);

>>>>>>> Stashed changes
ALTER TABLE `pays`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `athlete`
--
ALTER TABLE `athlete`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `pays`
--
ALTER TABLE `pays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `athlete`
--
ALTER TABLE `athlete`
  ADD CONSTRAINT `fk_ath_pays` FOREIGN KEY (`pays_id`) REFERENCES `pays` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
