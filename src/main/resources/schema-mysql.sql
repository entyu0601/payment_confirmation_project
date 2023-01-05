CREATE TABLE IF NOT EXISTS `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `object_id` varchar(45) DEFAULT NULL,
  `payment_deadline` int DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_method` varchar(45) DEFAULT NULL,
  `late_checked` int DEFAULT '0',
  `payment_months` int DEFAULT NULL,
  `payment_amount` int DEFAULT NULL,
  `rents_month` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `object_id` varchar(45) DEFAULT NULL,
  `payment_deadline` int DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_method` varchar(45) DEFAULT NULL,
  `late_checked` int DEFAULT '0',
  `payment_months` int DEFAULT NULL,
  `payment_amount` int DEFAULT NULL,
  `rents_month` int DEFAULT NULL,
  PRIMARY KEY (`id`)
); 