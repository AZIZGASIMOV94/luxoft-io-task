-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2022 at 02:08 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `luxoft_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `file_table`
--

CREATE TABLE `file_table` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(200) NOT NULL,
  `updated_timestamp` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `file_table`
--

INSERT INTO `file_table` (`id`, `name`, `description`, `updated_timestamp`) VALUES
(12, ' TEST_NAME_15', ' TEST_DESCRIPTION_15', '2022-12-15 16:40:14'),
(15, ' TEST_NAME_14', ' TEST_DESCRIPTION_14', '2022-12-15 16:40:14'),
(16, ' TEST_NAME_16', ' TEST_DESCRIPTION_16', '2022-12-15 16:40:14'),
(17, ' TEST_NAME_17', ' TEST_DESCRIPTION_17', '2019-12-15 16:40:14'),
(18, ' TEST_NAME_18', ' TEST_DESCRIPTION_18', '2021-12-15 16:40:14'),
(19, '  ', ' TEST_DESCRIPTION_16', '2020-12-15 16:40:14'),
(20, ' TEST_NAME_17', ' TEST_DESCRIPTION_22', '2019-12-15 16:40:14'),
(21, ' TEST_NAME_18', ' TEST_DESCRIPTION_22', '2021-12-15 16:40:14'),
(22, '  ', ' TEST_DESCRIPTION_22', '2020-12-15 16:40:14');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `file_table`
--
ALTER TABLE `file_table`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `file_table`
--
ALTER TABLE `file_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
