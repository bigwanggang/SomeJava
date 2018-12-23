/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.12 : Database - muke
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`muke` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `muke`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `over` varchar(30) DEFAULT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`over`,`mobile`) values (1,'唐僧','功德佛','123534234345,13423124,3424343463'),(2,'猪八戒','净坛使者','12398978798,172879234'),(3,'孙悟空','斗战胜佛','1239893478923,13712487293,13687234283,13302734873'),(4,'沙僧','金身罗汉','187873472834,18987289374');

/*Table structure for table `user_kills` */

DROP TABLE IF EXISTS `user_kills`;

CREATE TABLE `user_kills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `timestr` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `kills` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `user_kills` */

insert  into `user_kills`(`id`,`user_id`,`timestr`,`kills`) values (1,2,'2011-02-22 13:44:23',1),(2,3,'2011-02-23 08:23:56',3),(3,2,'2011-02-23 12:00:00',2),(4,4,'2011-02-23 16:00:00',1),(5,3,'2011-02-24 01:00:00',2),(6,4,'2011-02-24 03:00:00',2),(7,3,'2011-02-24 08:00:00',3),(8,2,'2011-02-24 16:00:00',2),(9,2,'2011-02-25 06:00:00',4),(10,3,'2011-02-26 09:00:00',5),(11,3,'2011-02-23 15:00:00',4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
