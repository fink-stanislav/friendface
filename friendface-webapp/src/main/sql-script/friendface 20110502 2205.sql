﻿SET NAMES 'utf8';
DROP DATABASE IF EXISTS friendface;
CREATE DATABASE IF NOT EXISTS friendface
CHARACTER SET utf8
COLLATE utf8_general_ci;

USE friendface;

CREATE TABLE IF NOT EXISTS friendface.users(
  id INT (11) NOT NULL AUTO_INCREMENT,
  loginEmail VARCHAR (20) NOT NULL,
  passwordHash VARCHAR (100) NOT NULL,
  username VARCHAR (20) NOT NULL,
  userSurname VARCHAR (20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX login USING BTREE (loginEmail)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS friendface.albums(
  id INT (11) NOT NULL AUTO_INCREMENT,
  userId INT (11) NOT NULL,
  title VARCHAR (20) NOT NULL,
  PRIMARY KEY (id),
  INDEX albums_user USING BTREE (userId),
  CONSTRAINT albums_user FOREIGN KEY (userId)
  REFERENCES friendface.users (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS friendface.friends(
  id INT (11) NOT NULL AUTO_INCREMENT,
  sender INT (11) NOT NULL COMMENT 'Здесь sender является внешним ключом, хотя разницы нет - можно и receiver сделать. По идее вообще должны быть оба',
  receiver INT (11) NOT NULL,
  isApproved TINYINT (1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  INDEX friends_user USING BTREE (sender),
  UNIQUE INDEX unique_value USING BTREE (receiver, sender),
  CONSTRAINT user_friends FOREIGN KEY (sender)
  REFERENCES friendface.users (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS friendface.videos(
  id INT (11) NOT NULL AUTO_INCREMENT,
  userId INT (11) NOT NULL,
  title VARCHAR (20) DEFAULT NULL COMMENT 'Может быть пустым',
  PRIMARY KEY (id),
  INDEX videos_user USING BTREE (userId),
  CONSTRAINT videos_user FOREIGN KEY (userId)
  REFERENCES friendface.users (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS friendface.wall_messages(
  id INT (11) NOT NULL AUTO_INCREMENT,
  receiver INT (11) NOT NULL COMMENT 'Сообщения на стене хранятся у того, кто их получил. Поэтому receiver это внешний ключ',
  sender INT (11) NOT NULL,
  PRIMARY KEY (id),
  INDEX messages_user USING BTREE (receiver),
  CONSTRAINT wall_messages_user FOREIGN KEY (receiver)
  REFERENCES friendface.users (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS friendface.pictures(
  id INT (11) NOT NULL AUTO_INCREMENT,
  albumId INT (11) NOT NULL,
  title VARCHAR (20) DEFAULT NULL COMMENT 'Может быть пустым',
  PRIMARY KEY (id),
  INDEX photos_album USING BTREE (albumId),
  CONSTRAINT pics_album FOREIGN KEY (albumId)
  REFERENCES friendface.albums (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

DELIMITER $$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE friendface.countNotApprovedFriends(IN userId INT, OUT result INT)
BEGIN

  SELECT
    COUNT(friends.id)
  INTO
    result
  FROM
    friends
  WHERE
    friends.userId = userId AND friends.isApproved = 0;

END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE friendface.countPhotosInAlbum(IN albumId INT, OUT result INT)
BEGIN

  DECLARE result INT;

  SET result = 0;

  SELECT
    COUNT(photos.id)
  INTO
    result
  FROM
    photos
  WHERE
    photos.albumId = albumId;

END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE friendface.isPasswordRight(IN loginEmail VARCHAR (20), IN passwordHash INT, OUT result BOOL)
BEGIN

  DECLARE hashToCompare INT;

  SELECT
    `user`.passwordHash
  INTO
    hashToCompare
  FROM
    `user`
  WHERE
    `user`.loginEmail = loginEmail;

  IF (hashToCompare = passwordHash) THEN
    SET result = TRUE;
  ELSE
    SET result = FALSE;
  END IF;

END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE friendface.isUserExists(IN loginEmail VARCHAR (20), OUT result BOOL)
BEGIN

  DECLARE userId INT;

  SELECT
    `user`.id
  INTO
    userId
  FROM
    `user`
  WHERE
    `user`.loginEmail = loginEmail;

  IF (userId != 0) THEN
    SET result = TRUE;
  ELSE
    SET result = FALSE;
  END IF;

END
$$

CREATE
DEFINER = 'root'@'localhost'
TRIGGER friendface.checkId
BEFORE INSERT
ON friendface.friends
FOR EACH ROW
BEGIN
  DECLARE dummy INT;

  IF (new.sender = new.receiver) THEN
    SELECT
      'Can not insert record. Equal ids'
    INTO
      dummy;
  END IF;
END
$$

DELIMITER ;
