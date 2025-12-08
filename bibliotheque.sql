-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 08, 2025 at 08:57 PM
-- Server version: 9.1.0
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bibliotheque`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `idAdmin` int NOT NULL,
  PRIMARY KEY (`idAdmin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `emprunt`
--

DROP TABLE IF EXISTS `emprunt`;
CREATE TABLE IF NOT EXISTS `emprunt` (
  `idEmprunt` int NOT NULL AUTO_INCREMENT,
  `idLivre` int NOT NULL,
  `idMembre` int NOT NULL,
  `dateEmprunt` date NOT NULL,
  `dateRetour` date DEFAULT NULL,
  `dateRetourPrevue` date DEFAULT NULL,
  PRIMARY KEY (`idEmprunt`),
  KEY `idx_emprunt_livre` (`idLivre`),
  KEY `idx_emprunt_membre` (`idMembre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `livre`
--

DROP TABLE IF EXISTS `livre`;
CREATE TABLE IF NOT EXISTS `livre` (
  `idLivre` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `auteur` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `annee` int DEFAULT NULL,
  `categorie` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `stock` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`idLivre`),
  KEY `idx_livre_titre` (`titre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `membre`
--

DROP TABLE IF EXISTS `membre`;
CREATE TABLE IF NOT EXISTS `membre` (
  `idMembre` int NOT NULL,
  `telephone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`idMembre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `lastname` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `passwordHash` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `role` tinyint(1) NOT NULL DEFAULT '0',
  `is_active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`idUser`, `firstname`, `lastname`, `username`, `email`, `passwordHash`, `role`, `is_active`, `created_at`) VALUES
(1, 'Lionel', 'Julien', 'l_julien', 'julien@gmail.com', '$2y$10$51CJ1Kp260vnjsNoKfA5y./2vCuNhMLRalpVNh//tTwfEy5A4CwYi', 1, 1, '2025-12-04 14:03:26'),
(2, 'Lionel', 'kylian', 'kiki', 'lkiki@gmail.com', '$2y$10$fqo3I4PtWQSc5WhH/Fi04eLvD0vJjK3l1tF9FGqX6DFonRyi1dfEy', 0, 1, '2025-12-05 02:01:27'),
(3, 'marie', 'lena', 'lmarie', 'marie@gmail.com', '$2a$10$EEQ7s2R0R3N/6I8o1TFBAujhEfHEX2hcYHDJS1T1F3AfgIWrhkmiy', 0, 1, '2025-12-05 02:17:17'),
(4, 'jav', 'jaso', 'jaso', 'jaso@gmail.com', '$2a$10$xxZE.ww.NRkS3on4NTDwXOL1cYwCL78jSx.ehbhxY2SeYtl5DL/J6', 0, 1, '2025-12-05 02:48:21'),
(6, 'leroy', 'kylian', 'lkiki', 'lkikii@gmail.com', '$2a$10$cTFWC4oa7o.sb1kMT842eOxtTiUFBdARapxmf.QzuqACeL/gS.UK.', 0, 1, '2025-12-05 12:22:14'),
(7, 'geremie', 'geremi', 'csgere', 'csgere@gmail.com', '$2a$10$S1iF/x77BIm0u7H1PJYYP.ZgP0Wt.W67r1vupzIA9haD.TZp8w6l2', 0, 1, '2025-12-07 17:44:41'),
(8, 'maeva', 'oceanne', 'maeoce', 'maeoce@gmail.com', '$2a$10$1fdOaEtfSdyRdOSfO4s4xOTuhESQ20XTsBbiaBFLZMrrB1Nmj0kge', 0, 1, '2025-12-07 20:28:51'),
(9, 'jonathan', 'jona', 'jona', 'jona@gmail.com', '$2a$10$ps8p108zQk1FYTLDjI9MLOlVpK2iDV9iKDhBtIse92HKp8tF.9nUa', 0, 1, '2025-12-08 12:31:09');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `fk_admin_user` FOREIGN KEY (`idAdmin`) REFERENCES `users` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `emprunt`
--
ALTER TABLE `emprunt`
  ADD CONSTRAINT `fk_emprunt_livre` FOREIGN KEY (`idLivre`) REFERENCES `livre` (`idLivre`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_emprunt_membre` FOREIGN KEY (`idMembre`) REFERENCES `membre` (`idMembre`) ON UPDATE CASCADE;

--
-- Constraints for table `membre`
--
ALTER TABLE `membre`
  ADD CONSTRAINT `fk_membre_user` FOREIGN KEY (`idMembre`) REFERENCES `users` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
