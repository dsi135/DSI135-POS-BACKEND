-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: mysql-container
-- Generation Time: Jun 21, 2019 at 07:15 AM
-- Server version: 5.5.62
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dsi2019`
--

-- --------------------------------------------------------

--
-- Table structure for table `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`) VALUES
(1, 'BEBIDAS CALIENTES'),
(2, 'BEBIDAS FRIAS'),
(5, 'PUPUSAS'),
(8, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `detalle_orden`
--

CREATE TABLE `detalle_orden` (
  `id` int(11) NOT NULL,
  `producto` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detalle_orden`
--

INSERT INTO `detalle_orden` (`id`, `producto`, `cantidad`, `precio`) VALUES
(1, 1, 2, 1),
(1, 6, 2, 2),
(1, 10, 1, 1.35);

-- --------------------------------------------------------

--
-- Table structure for table `orden`
--

CREATE TABLE `orden` (
  `id` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `mesero` varchar(100) DEFAULT NULL,
  `mesa` int(2) DEFAULT NULL,
  `cliente` varchar(100) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `observaciones` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orden`
--

INSERT INTO `orden` (`id`, `fecha`, `mesero`, `mesa`, `cliente`, `estado`, `total`, `observaciones`) VALUES
(1, '2019-06-20', 'primero', 2, 'primer cliente', 1, 20, NULL),
(2, '2019-06-20', 'primero', 2, 'tercer cliente', 1, 20, NULL),
(3, '2019-06-20', 'primero', 2, 'tercer cliente', 0, 20, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `parametros`
--

CREATE TABLE `parametros` (
  `id` int(11) NOT NULL DEFAULT '0',
  `nombre` varchar(100) DEFAULT NULL,
  `valor` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL DEFAULT '0',
  `nombre` varchar(100) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `categoria` int(11) DEFAULT NULL,
  `preparado` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `precio`, `categoria`, `preparado`) VALUES
(1, 'PEPSI', 0.5, 2, NULL),
(2, 'COCA COLA', 0.6, 2, NULL),
(3, 'SUPREMA', 1.5, 2, NULL),
(4, 'VODKA', 7, 2, NULL),
(5, 'PILSENER', 1, 2, NULL),
(6, 'CAFE', 1, 1, 1),
(7, 'CHOCOLATE', 1.6, 1, 1),
(8, 'TE CALIENTE', NULL, 1, 1),
(9, 'LICUADOS', 1.15, 2, 1),
(10, 'FROZEN', 1.35, 2, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detalle_orden`
--
ALTER TABLE `detalle_orden`
  ADD PRIMARY KEY (`id`,`producto`),
  ADD UNIQUE KEY `producto` (`producto`),
  ADD KEY `id` (`id`) USING BTREE;

--
-- Indexes for table `orden`
--
ALTER TABLE `orden`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `parametros`
--
ALTER TABLE `parametros`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria` (`categoria`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `orden`
--
ALTER TABLE `orden`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detalle_orden`
--
ALTER TABLE `detalle_orden`
  ADD CONSTRAINT `detalle_orden_ibfk_1` FOREIGN KEY (`id`) REFERENCES `orden` (`id`),
  ADD CONSTRAINT `detalle_orden_ibfk_2` FOREIGN KEY (`producto`) REFERENCES `producto` (`id`);

--
-- Constraints for table `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
