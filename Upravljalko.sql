-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 15, 2020 at 11:45 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Upravljalko`
--

-- --------------------------------------------------------

--
-- Table structure for table `clanstvo`
--

CREATE TABLE `clanstvo` (
  `delavec_id` int(11) NOT NULL,
  `skupina_id` int(11) NOT NULL,
  `clan` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_slovenian_ci;

--
-- Dumping data for table `clanstvo`
--

INSERT INTO `clanstvo` (`delavec_id`, `skupina_id`, `clan`) VALUES
(67, 35, 1),
(65, 35, 0),
(68, 35, 0),
(68, 42, 0),
(68, 58, 1),
(75, 58, 1),
(68, 59, 1),
(67, 59, 1),
(68, 60, 1);

-- --------------------------------------------------------

--
-- Table structure for table `delavec`
--

CREATE TABLE `delavec` (
  `id` int(11) NOT NULL,
  `ime` varchar(50) COLLATE utf16_slovenian_ci NOT NULL,
  `priimek` varchar(50) COLLATE utf16_slovenian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_slovenian_ci;

--
-- Dumping data for table `delavec`
--

INSERT INTO `delavec` (`id`, `ime`, `priimek`) VALUES
(65, 'Tadej', 'Leb'),
(67, 'Marcela', 'Bon'),
(68, 'Eva', 'Cep'),
(69, 'Vesna', 'Leben'),
(72, 'Miha', 'Rek'),
(73, 'Tadeja', 'Albin'),
(74, 'Rene', 'Leben'),
(75, 'Janez', 'Novak'),
(97, 'Rebeka', 'Smola'),
(98, 'Nino', 'Breg');

-- --------------------------------------------------------

--
-- Table structure for table `skupina`
--

CREATE TABLE `skupina` (
  `id` int(11) NOT NULL,
  `naziv` varchar(50) COLLATE utf16_slovenian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_slovenian_ci;

--
-- Dumping data for table `skupina`
--

INSERT INTO `skupina` (`id`, `naziv`) VALUES
(35, 'Elektrotehnik'),
(42, 'Mehanik'),
(45, 'Programer'),
(50, 'Pripravnik'),
(52, 'Manager'),
(58, 'Asistent'),
(59, 'Dizajner'),
(60, 'Mentor');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clanstvo`
--
ALTER TABLE `clanstvo`
  ADD KEY `clanstvo_ibfk_1` (`delavec_id`),
  ADD KEY `clanstvo_ibfk_2` (`skupina_id`);

--
-- Indexes for table `delavec`
--
ALTER TABLE `delavec`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `skupina`
--
ALTER TABLE `skupina`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `delavec`
--
ALTER TABLE `delavec`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- AUTO_INCREMENT for table `skupina`
--
ALTER TABLE `skupina`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `clanstvo`
--
ALTER TABLE `clanstvo`
  ADD CONSTRAINT `clanstvo_ibfk_1` FOREIGN KEY (`delavec_id`) REFERENCES `delavec` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `clanstvo_ibfk_2` FOREIGN KEY (`skupina_id`) REFERENCES `skupina` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
