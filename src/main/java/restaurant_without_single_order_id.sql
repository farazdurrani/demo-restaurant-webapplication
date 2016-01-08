/*
 * THIS DOESN'T WORK. GET RID OF IT WHEN ALL IS WELL 
 */
CREATE DATABASE restaurant DEFAULT CHARACTER SET 'utf8'
  DEFAULT COLLATE 'utf8_unicode_ci';
 
USE restaurant;

CREATE TABLE admin (
  admin_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  PRIMARY KEY (admin_id)
) ENGINE=InnoDB;

CREATE TABLE singleorders (
  useless_id int(11) NOT NULL AUTO_INCREMENT,
  single_order_id bigint NOT NULL,    
  person_name varchar(255) NOT NULL, 
  primary key (useless_id),
   KEY (single_order_id)
) ENGINE=InnoDB;

CREATE TABLE customer (
  customer_id int(11) NOT NULL AUTO_INCREMENT,
  fullname varchar(255) DEFAULT NULL,
  username varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  signedin boolean NOT NULL default 0,
  PRIMARY KEY (customer_id)
) ENGINE=InnoDB;

CREATE TABLE menus (
  menu_id int(11) NOT NULL AUTO_INCREMENT,
  day varchar(45) NOT NULL,
  name varchar(45) NOT NULL,
  price int(11) NOT NULL,
  open boolean NOT NULL default 1,
  PRIMARY KEY (menu_id) 
) ENGINE=InnoDB;

CREATE TABLE menusforshow (
  menu_id int(11) NOT NULL AUTO_INCREMENT,
  day varchar(45) NOT NULL,
  name varchar(45) NOT NULL,
  price int(11) NOT NULL, 
  PRIMARY KEY (menu_id)
) ENGINE=InnoDB;

CREATE TABLE `customer_menu` (
  `customer_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`customer_id`,`menu_id`),
  UNIQUE KEY `menu_id_UNIQUE` (`menu_id`),
  KEY `fk_customer` (`customer_id`),
  KEY `fk_menu` (`menu_id`),
  CONSTRAINT `fk_menu` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`menu_id`),
  CONSTRAINT `fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
);

CREATE TABLE `singleorder_menu` (
  `useless_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`useless_id`,`menu_id`),
  UNIQUE KEY `menu_id_UNIQUE` (`menu_id`),
  KEY `fk_singleorder` (`useless_id`),
  KEY `fk_menuXXX` (`menu_id`),
  CONSTRAINT `fk_menuXXX` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`menu_id`),
  CONSTRAINT `fk_singleorder` FOREIGN KEY (`useless_id`) REFERENCES `singleorders` (`useless_id`)
);

INSERT INTO admin ( username, password)
                       VALUES
 ("faraz","1");

INSERT INTO menusforshow ( day, name, price)
                       VALUES
 ("Monday", "Cholay", 13), ("Monday", "Garam Cholay", 10),
 ("Monday", "Thanday Cholay", 13),  
	("Monday", "Safaid Aloo", 10),
		("Monday", "Kalay Cholay", 13),	("Tuesday", "Dahi bhallay", 8),
		("Tuesday", "Palak", 7),("Wednesday", "Biriyani", 12),
		("Wednesday", "Nihari", 15),("Thursday", "Kabab", 12),
		("Thursday", "Dahi", 15),
		("Friday", "Daal", 12),
		("Friday", "Chawal", 15),
		("Saturday", "Sabzi", 12),
		("Saturday", "Gobi", 15),
		("Sunday", "Papay", 12),
		("Sunday", "Double Roti", 15),
		("Sunday", "Unday", 12),
		("Sunday", "Cheese", 15),
		("Sunday", "Mukhun", 12),
		("Sunday", "Paratha", 15),
		("Sunday", "Coffee", 12),
		("Sunday", "Baqar Khani", 15);   
