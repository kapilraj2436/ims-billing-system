-- MySQL dump 10.13  Distrib 9.0.1, for macos14 (arm64)
--
-- Host: localhost    Database: inventory_management
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `INV_Address`
--

DROP TABLE IF EXISTS `INV_Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `INV_Address` (
  `addressId` int NOT NULL AUTO_INCREMENT,
  `addressString1` varchar(255) NOT NULL,
  `addressString2` varchar(255) DEFAULT NULL,
  `cityId` int DEFAULT NULL,
  PRIMARY KEY (`addressId`),
  KEY `cityId` (`cityId`),
  CONSTRAINT `inv_address_ibfk_1` FOREIGN KEY (`cityId`) REFERENCES `INV_City` (`cityId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INV_Address`
--

LOCK TABLES `INV_Address` WRITE;
/*!40000 ALTER TABLE `INV_Address` DISABLE KEYS */;
INSERT INTO `INV_Address` VALUES (10,'Mera office ka address','bahot bada address hi hai',13),(11,'1002, Wing B, Alpha landmark','Wagholi',13),(12,'Alfa landmark, wing B','Wagholi, pune',13);
/*!40000 ALTER TABLE `INV_Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INV_BankDetails`
--

DROP TABLE IF EXISTS `INV_BankDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `INV_BankDetails` (
  `bankDetailsId` int NOT NULL AUTO_INCREMENT,
  `bankName` varchar(255) NOT NULL,
  `accountNumber` varchar(50) NOT NULL,
  `ifscCode` varchar(11) NOT NULL,
  `branch` varchar(255) DEFAULT NULL,
  `ownerName` varchar(255) NOT NULL,
  `description` text,
  PRIMARY KEY (`bankDetailsId`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INV_BankDetails`
--

LOCK TABLES `INV_BankDetails` WRITE;
/*!40000 ALTER TABLE `INV_BankDetails` DISABLE KEYS */;
INSERT INTO `INV_BankDetails` VALUES (18,'VIJAYA BANK','445563323498','DENA0988','Mandsaur','Nicky Rawat','DENA bank me hai account'),(19,'VIJAYA BANK','445563323498','DENA0988','Mandsaur','Nicky Rawat','DENA bank me hai account'),(20,'VIJAYA BANK','445563323498','DENA0988','Mandsaur','Nicky Rawat','DENA bank me hai account'),(21,'VIJAYA BANK','445563323498','DENA0988','Mandsaur','Nicky Rawat','DENA bank me hai account'),(22,'VIJAYA BANK','445563323498','DENA0988','Mandsaur','Nicky Rawat','DENA bank me hai account'),(23,'ICICI','98097987797979','ICIC0999','Indore','Kapil','Kapil'),(24,'PNB Bank','09987778899','IFSCPNB009','Sitamau','Chouhan bag works','Ghar ka saman'),(25,'PNB','9098776666','IFSCk998','Sitmayu','Sitamau','Sitamau'),(26,'PNB','9098776666','IFSCk998','Sitmayu','Sitamau','Sitamau'),(27,'PNB','9098776666','IFSCk998','Sitmayu','Sitamau','Sitamau'),(28,'ICICI Bank','987655678','IFSC09090','Indore','Prateek','Prateek'),(29,'Dena Bank','878787878787','IFSC89898','Neemach','Prateek','Prateek');
/*!40000 ALTER TABLE `INV_BankDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INV_City`
--

DROP TABLE IF EXISTS `INV_City`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `INV_City` (
  `cityId` int NOT NULL AUTO_INCREMENT,
  `cityName` varchar(255) NOT NULL,
  `cityPinCode` varchar(10) NOT NULL,
  `cityDescription` text,
  PRIMARY KEY (`cityId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INV_City`
--

LOCK TABLES `INV_City` WRITE;
/*!40000 ALTER TABLE `INV_City` DISABLE KEYS */;
INSERT INTO `INV_City` VALUES (13,'Indore','458002','Indore is cleanest city'),(14,'Sitamau','458990','Sitamau is cleanest city'),(15,'Mandsaur','458993','MDS is cleanest city'),(16,'Mandsaur','458993','MDS is cleanest city');
/*!40000 ALTER TABLE `INV_City` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INV_Customer`
--

DROP TABLE IF EXISTS `INV_Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `INV_Customer` (
  `customerId` int NOT NULL AUTO_INCREMENT,
  `customerName` varchar(255) NOT NULL,
  `customerMobile` varchar(15) NOT NULL,
  `customerAddressId` int DEFAULT NULL,
  `gstinNumber` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`customerId`),
  KEY `customerAddressId` (`customerAddressId`),
  CONSTRAINT `inv_customer_ibfk_1` FOREIGN KEY (`customerAddressId`) REFERENCES `INV_Address` (`addressId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INV_Customer`
--

LOCK TABLES `INV_Customer` WRITE;
/*!40000 ALTER TABLE `INV_Customer` DISABLE KEYS */;
INSERT INTO `INV_Customer` VALUES (13,'Daily customer','8989897797',11,'hsdh8977'),(14,'Vivek Agnihotri','0999383883',10,'jdfhds76786'),(15,'Prateek Mhatre','9876567899',12,'TIN0987j');
/*!40000 ALTER TABLE `INV_Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INV_DISCOUNT`
--

DROP TABLE IF EXISTS `INV_DISCOUNT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `INV_DISCOUNT` (
  `discountId` int NOT NULL AUTO_INCREMENT,
  `discountName` varchar(255) NOT NULL,
  `discountValue` varchar(50) NOT NULL,
  `discountDescription` text,
  PRIMARY KEY (`discountId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INV_DISCOUNT`
--

LOCK TABLES `INV_DISCOUNT` WRITE;
/*!40000 ALTER TABLE `INV_DISCOUNT` DISABLE KEYS */;
INSERT INTO `INV_DISCOUNT` VALUES (7,'Monday offer','22','Monday pe sell lagi he'),(8,'Diwali Offer','10','Diwali pe sell lagi he'),(9,'New Year offer','25','New Year offer'),(10,'Student Offer','15','Student offer');
/*!40000 ALTER TABLE `INV_DISCOUNT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inv_order`
--

DROP TABLE IF EXISTS `inv_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inv_order` (
  `orderId` int NOT NULL AUTO_INCREMENT,
  `customerId` int DEFAULT NULL,
  `hsnCode` varchar(15) DEFAULT NULL,
  `orderPrice` decimal(10,2) NOT NULL,
  `discountId` int DEFAULT NULL,
  `paymentId` int DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `customerId` (`customerId`),
  KEY `discountId` (`discountId`),
  KEY `paymentId` (`paymentId`),
  CONSTRAINT `inv_order_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `INV_Customer` (`customerId`),
  CONSTRAINT `inv_order_ibfk_2` FOREIGN KEY (`discountId`) REFERENCES `INV_DISCOUNT` (`discountId`),
  CONSTRAINT `inv_order_ibfk_3` FOREIGN KEY (`paymentId`) REFERENCES `INV_Payments` (`paymentId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inv_order`
--

LOCK TABLES `inv_order` WRITE;
/*!40000 ALTER TABLE `inv_order` DISABLE KEYS */;
INSERT INTO `inv_order` VALUES (24,13,'HSN8899K0909',6700.00,7,14),(25,14,'',500.00,8,15),(26,14,'',1825.00,9,16),(27,14,'',5125.00,8,17),(28,14,'',5125.00,8,18),(29,14,'',5125.00,8,19),(30,15,'',5150.00,10,20),(31,15,'',4000.00,8,21);
/*!40000 ALTER TABLE `inv_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INV_Order_Product`
--

DROP TABLE IF EXISTS `INV_Order_Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `INV_Order_Product` (
  `orderId` int NOT NULL,
  `productId` bigint NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`orderId`,`productId`),
  KEY `productId` (`productId`),
  CONSTRAINT `inv_order_product_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `INV_Order` (`orderId`),
  CONSTRAINT `inv_order_product_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `INV_Product` (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INV_Order_Product`
--

LOCK TABLES `INV_Order_Product` WRITE;
/*!40000 ALTER TABLE `INV_Order_Product` DISABLE KEYS */;
INSERT INTO `INV_Order_Product` VALUES (24,7,2),(24,9,2),(25,7,1),(26,7,1),(26,8,13),(26,9,1),(26,10,10),(27,7,1),(27,8,1),(27,9,1),(27,10,1),(28,7,1),(28,8,1),(28,9,1),(28,10,1),(29,7,1),(29,8,1),(29,9,1),(29,10,1),(30,7,1),(30,8,1),(30,9,1),(30,10,1),(30,11,1),(31,9,1);
/*!40000 ALTER TABLE `INV_Order_Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INV_PaymentMethod`
--

DROP TABLE IF EXISTS `INV_PaymentMethod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `INV_PaymentMethod` (
  `paymentMethodId` int NOT NULL AUTO_INCREMENT,
  `paymentMethod` varchar(100) NOT NULL,
  `bankDetailsId` int DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`paymentMethodId`),
  KEY `bankDetailsId` (`bankDetailsId`),
  CONSTRAINT `inv_paymentmethod_ibfk_1` FOREIGN KEY (`bankDetailsId`) REFERENCES `INV_BankDetails` (`bankDetailsId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INV_PaymentMethod`
--

LOCK TABLES `INV_PaymentMethod` WRITE;
/*!40000 ALTER TABLE `INV_PaymentMethod` DISABLE KEYS */;
INSERT INTO `INV_PaymentMethod` VALUES (10,'Online',18,'Payment online bank me liya hai'),(11,'Online',19,'Payment online bank me liya hai'),(12,'Online',20,'Payment online bank me liya hai'),(13,'Online',21,'Payment online bank me liya hai'),(14,'Online',22,'Payment online bank me liya hai'),(15,'Cash',23,'cash'),(16,'Cash',24,'Cash'),(17,'Cash',25,'Cash'),(18,'Cash',26,'Cash'),(19,'Cash',27,'Cash'),(20,'Online UPI',28,'UPI pe paise aa gaye'),(21,'UPI',29,'Online UPI');
/*!40000 ALTER TABLE `INV_PaymentMethod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INV_Payments`
--

DROP TABLE IF EXISTS `INV_Payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `INV_Payments` (
  `paymentId` int NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,2) NOT NULL,
  `paymentDate` datetime NOT NULL,
  `invoiceId` varchar(50) DEFAULT NULL,
  `paymentMethodId` int DEFAULT NULL,
  `paymentStatusId` int DEFAULT NULL,
  `paymentReceivedBy` varchar(255) DEFAULT NULL,
  `transactionId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`paymentId`),
  KEY `paymentMethodId` (`paymentMethodId`),
  KEY `paymentStatusId` (`paymentStatusId`),
  CONSTRAINT `inv_payments_ibfk_1` FOREIGN KEY (`paymentMethodId`) REFERENCES `INV_PaymentMethod` (`paymentMethodId`),
  CONSTRAINT `inv_payments_ibfk_2` FOREIGN KEY (`paymentStatusId`) REFERENCES `INV_PaymentStatus` (`paymentStatusId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INV_Payments`
--

LOCK TABLES `INV_Payments` WRITE;
/*!40000 ALTER TABLE `INV_Payments` DISABLE KEYS */;
INSERT INTO `INV_Payments` VALUES (10,4000.00,'2024-10-28 23:31:13','9',10,10,'Kapil','88099876667898'),(11,4000.00,'2024-10-28 23:32:24','9',11,11,'Kapil','88099876667898'),(12,4000.00,'2024-10-28 23:32:54','9',12,12,'Kapil','88099876667898'),(13,4000.00,'2024-10-28 23:33:40','9',13,13,'Kapil','88099876667898'),(14,4000.00,'2024-10-28 23:37:10','9',14,14,'Kapil','88099876667898'),(15,555.00,'2024-10-29 01:56:22','5',15,15,'Kapil5','555'),(16,1825.00,'2024-11-06 14:01:11','99',16,16,'Kapil','TRA0998898'),(17,5125.00,'2024-11-06 14:06:26','inv9989',17,17,'Kalu','kiu786'),(18,5125.00,'2024-11-06 14:06:55','inv9989',18,18,'Kalu','kiu786'),(19,5125.00,'2024-11-06 14:09:27','inv9989',19,19,'Kalu','kiu786'),(20,5150.00,'2024-11-06 22:11:11','INVId',20,20,'Kapil','Trabsaction00990'),(21,4000.00,'2024-11-06 22:41:05','invoiceId',21,21,'Vaibhav','56565656');
/*!40000 ALTER TABLE `INV_Payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INV_PaymentStatus`
--

DROP TABLE IF EXISTS `INV_PaymentStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `INV_PaymentStatus` (
  `paymentStatusId` int NOT NULL AUTO_INCREMENT,
  `paymentStatusName` varchar(100) NOT NULL,
  `description` text,
  PRIMARY KEY (`paymentStatusId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INV_PaymentStatus`
--

LOCK TABLES `INV_PaymentStatus` WRITE;
/*!40000 ALTER TABLE `INV_PaymentStatus` DISABLE KEYS */;
INSERT INTO `INV_PaymentStatus` VALUES (10,'PAID','Payment paid successfully'),(11,'PAID','Payment paid successfully'),(12,'PAID','Payment paid successfully'),(13,'PAID','Payment paid successfully'),(14,'PAID','Payment paid successfully'),(15,'PAID','PAID'),(16,'PAID','PAID'),(17,'PAID','PAID'),(18,'PAID','PAID'),(19,'PAID','PAID'),(20,'PAID','PAID'),(21,'PAID','PAID');
/*!40000 ALTER TABLE `INV_PaymentStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INV_PRODUCT`
--

DROP TABLE IF EXISTS `INV_PRODUCT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `INV_PRODUCT` (
  `productId` bigint NOT NULL AUTO_INCREMENT,
  `productName` varchar(255) NOT NULL,
  `productDescription` text,
  `pricePerUnit` double NOT NULL,
  `availableQuantity` int NOT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INV_PRODUCT`
--

LOCK TABLES `INV_PRODUCT` WRITE;
/*!40000 ALTER TABLE `INV_PRODUCT` DISABLE KEYS */;
INSERT INTO `INV_PRODUCT` VALUES (7,'Dhaniya','Dhaniye ka bap Baniya',25,80),(8,'Revolving Chair','Chair 4 wheeler',1000,80),(9,'Washing Machine','LG company washing machine',4000,10),(10,'SOAp','Soundrya Sabun Nirma',10,900),(11,'Madicine','Sar dard ki tablet',25,100);
/*!40000 ALTER TABLE `INV_PRODUCT` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-24 17:17:26
