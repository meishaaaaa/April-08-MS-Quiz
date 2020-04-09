CREATE TABLE `park` (
  `pkid` int NOT NULL AUTO_INCREMENT,
  `stall_num` varchar(10) DEFAULT NULL,
  `plate_num` varchar(15) DEFAULT NULL,
  `parking_start_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8