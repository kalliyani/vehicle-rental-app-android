-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 11, 2024 at 05:13 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `car_rent`
--

-- --------------------------------------------------------

--
-- Table structure for table `car_tbl`
--

CREATE TABLE IF NOT EXISTS `car_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  `image` tinytext NOT NULL,
  `rate` varchar(100) NOT NULL,
  `transmission_type` varchar(100) NOT NULL,
  `fuel_type` varchar(100) NOT NULL,
  `no_of_seats` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `car_tbl`
--

INSERT INTO `car_tbl` (`id`, `name`, `model`, `location`, `image`, `rate`, `transmission_type`, `fuel_type`, `no_of_seats`, `status`) VALUES
(1, 'Maruthi Suzuki', 'Baleno', 'Palarivottom', '2024-01-18-11-44-45car.jpg', '500', 'Manual', 'Petrol', '5', 'available'),
(2, 'BMW', '5 series', 'Edapally', '2024-01-20-11-28-202024-01-19-02-07-57car2.png', '1800', 'Automatic', 'Diesel', '5', 'available'),
(3, 'Benz', 'C-43', 'Kakkanad', '2024-01-20-11-28-40car3.png', '2000', 'Automatic', 'Diesel', '5', 'unavailable'),
(4, 'Range Rover', 'Evoque', 'Kakkanad', '2024-01-20-11-30-012024-01-19-02-10-40car4.png', '2500', 'Automatic', 'Diesel', '5', 'unavailable'),
(5, 'Traveler', 'force', 'paravur', 'traveller.jpg', '2500', 'manual', 'desiel', '17', 'available');

-- --------------------------------------------------------

--
-- Table structure for table `feedback_tbl`
--

CREATE TABLE IF NOT EXISTS `feedback_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(100) NOT NULL,
  `feedback` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `manager_tbl`
--

CREATE TABLE IF NOT EXISTS `manager_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `number` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `manager_tbl`
--

INSERT INTO `manager_tbl` (`id`, `name`, `number`, `email`, `password`) VALUES
(1, 'Rahul', '9495826110', 'rahul25@gmail.com', 'rahul25');

-- --------------------------------------------------------

--
-- Table structure for table `payment_tbl`
--

CREATE TABLE IF NOT EXISTS `payment_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) NOT NULL,
  `bookingid` varchar(100) NOT NULL,
  `cardnumber` varchar(100) NOT NULL,
  `amount` varchar(100) NOT NULL,
  `date` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `payment_tbl`
--

INSERT INTO `payment_tbl` (`id`, `user_id`, `bookingid`, `cardnumber`, `amount`, `date`) VALUES
(1, '4', '2', '1236547899', '2500', '2024-08-18');

-- --------------------------------------------------------

--
-- Table structure for table `renting_tbl`
--

CREATE TABLE IF NOT EXISTS `renting_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(100) NOT NULL,
  `car_id` varchar(100) NOT NULL,
  `license_no` varchar(100) NOT NULL,
  `noofdays` varchar(100) NOT NULL,
  `booking_date` varchar(100) NOT NULL,
  `image` tinytext NOT NULL,
  `amount` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `renting_tbl`
--

INSERT INTO `renting_tbl` (`id`, `userid`, `car_id`, `license_no`, `noofdays`, `booking_date`, `image`, `amount`, `status`) VALUES
(1, '4', '1', '1356789999765', '5', '2024-08-13', 'image1.jpg', '2500', 'Approved'),
(2, '4', '1', '124567888888888', '5', '2024-08-18', 'image2.jpg', '2500', 'paid'),
(4, '16', '5', '8787878787', '8', '2024-12-06', 'image4.jpg', '20000', 'booked');

-- --------------------------------------------------------

--
-- Table structure for table `user_tbl`
--

CREATE TABLE IF NOT EXISTS `user_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `number` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `user_tbl`
--

INSERT INTO `user_tbl` (`id`, `name`, `number`, `email`, `password`) VALUES
(1, 'Sugjvvjkb', '756988', 'gauthams1098@gmail.com', '12345'),
(2, 'Aswathy', '7735683577', 'aswathy123@gmail.com', '123'),
(3, 'Lekshmi', '9495068008', 'leksshmi123@gmail.com', '1122'),
(4, 'Anu', '9497812699', 'anu123@gmail.com', 'anu123'),
(5, 'Bellabellzz', '0123456789', 'bellamariya@.com', '321'),
(6, 'gadha', '9744006025', 'sradhagadha02@gmail.com', 'ammu123'),
(7, 'Meera ', '7786953588', 'meera@gmail.com', '111'),
(11, 'kalliyani', '7785085688', 'gauthams1098@gmail.com', '123'),
(12, 'adithyan t. b ', '7902357448', '\nadithyan4t7@gmail.com\n', '4455'),
(14, 'Adithyan T B ', '7736583657', 'adithyan@gmail.com', '4455'),
(15, 'adhi12', '7902357448', 'adhi@gmail.com', '7890'),
(16, 'Kallu', '7736473857', 'kallu@gmail.com', '333');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
