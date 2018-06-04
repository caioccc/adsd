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


CREATE TABLE `user_organization` (
  `iduser` integer,
  `idorganization` integer,
  PRIMARY KEY (`iduser`,`idorganization`),
  CONSTRAINT `user_organization_ibfk_1`
  FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`),
  CONSTRAINT `user_organization_ibfk_2`
  FOREIGN KEY (`idorganization`) REFERENCES `organization` (`idorganization`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `project` (
  `idproject` integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `iduser` integer NOT NULL,
  `idorganization` integer NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255),
  `dateupdate` datetime NOT NULL,
  `status` bool NOT NULL,
  `tags` text,
  FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`),
  FOREIGN KEY (`idorganization`) REFERENCES `organization` (`idorganization`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `variable` (
  `idvariable` integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `iduser` integer NOT NULL,
  `idproject` integer NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) ,
  `dateupdate` datetime NOT NULL,
  `type` varchar(255) NOT NULL,
  `tags` text,
  FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`),
  FOREIGN KEY (`idproject`) REFERENCES `project` (`idproject`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `diagram` (
  `iddiagram` integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `iduser` integer NOT NULL,
  `idproject` integer NOT NULL,
  `name` varchar(255) NOT NULL,
  `version` varchar(255) NOT NULL,
  `dateupdate` datetime NOT NULL,
  `startdate` datetime NOT NULL,
  `enddate` datetime NOT NULL,
  `tags` text,
  FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`),
  FOREIGN KEY (`idproject`) REFERENCES `project` (`idproject`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;