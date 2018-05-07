CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL UNIQUE ,
  `password` varchar(100) NOT NULL,
  `tags` text,
  `role` varchar(255) NOT NULL,
  `master` BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


CREATE TABLE `organization` (
  `idorganization` integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL UNIQUE ,
  `status` bool NOT NULL,
  `tags` text,
  `accesskey` varchar(255) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;


CREATE TABLE `role` (
  `idrole` integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `role` varchar(255) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;