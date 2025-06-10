
-- Dumping database structure for hibernatedemos
DROP DATABASE IF EXISTS cms;
CREATE DATABASE cms; 
USE cms;


-- Dumping structure for table hibernatedemos.customer
DROP TABLE IF EXISTS customer;
CREATE TABLE IF NOT EXISTS customer (
  customerId int(11) unsigned NOT NULL AUTO_INCREMENT,
  customerName varchar(20) DEFAULT NULL,
  billingDate datetime DEFAULT NULL,
  bill double DEFAULT NULL,
  PRIMARY KEY (customerId)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

INSERT INTO customer (customerId, customerName,  billingDate, bill) VALUES
	(1001, 'MSD', '2016-01-28 00:00:00', 100000),
	(1002, 'James', '2016-01-28 00:00:00', 0),
	(1003, 'Rocky', '2016-01-28 00:00:00', 100000);
commit;
