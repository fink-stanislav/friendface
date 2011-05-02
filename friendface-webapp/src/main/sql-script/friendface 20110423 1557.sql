-- Скрипт сгенерирован Devart dbForge Studio for MySQL, Версия 3.60.351.1
-- Дата: 4/23/2011 3:59:00 PM
-- Версия сервера: 5.0.67-community-nt
-- Версия клиента: 4.1

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;

SET NAMES 'utf8';
--
-- Описание для базы данных friendface
--
DROP DATABASE IF EXISTS friendface;
CREATE DATABASE IF NOT EXISTS friendface
CHARACTER SET utf8
COLLATE utf8_general_ci;

USE friendface;

--
-- Описание для таблицы friendface.users
--
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
AUTO_INCREMENT = 21
AVG_ROW_LENGTH = 4096
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы friendface.albums
--
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

--
-- Описание для таблицы friendface.friends
--
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
AUTO_INCREMENT = 2
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы friendface.videos
--
CREATE TABLE IF NOT EXISTS friendface.videos(
  id INT (11) NOT NULL AUTO_INCREMENT,
  videoId VARCHAR (255) NOT NULL,
  userId INT (11) NOT NULL,
  title VARCHAR (20) DEFAULT NULL COMMENT 'Может быть пустым',
  PRIMARY KEY (id),
  UNIQUE INDEX videoId USING BTREE (videoId),
  INDEX videos_user USING BTREE (userId),
  CONSTRAINT videos_user FOREIGN KEY (userId)
  REFERENCES friendface.users (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы friendface.wall_messages
--
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

--
-- Описание для таблицы friendface.photos
--
CREATE TABLE IF NOT EXISTS friendface.photos(
  id INT (11) NOT NULL AUTO_INCREMENT,
  photoId VARCHAR (255) NOT NULL,
  albumId INT (11) NOT NULL,
  title VARCHAR (20) DEFAULT NULL COMMENT 'Может быть пустым',
  PRIMARY KEY (id),
  UNIQUE INDEX photoId USING BTREE (photoId),
  INDEX photos_album USING BTREE (albumId),
  CONSTRAINT photos_album FOREIGN KEY (albumId)
  REFERENCES friendface.albums (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

DELIMITER $$

--
-- Описание для процедуры friendface.countNotApprovedFriends
--
CREATE PROCEDURE friendface.countNotApprovedFriends(IN userId INT, OUT result INT)
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

--
-- Описание для процедуры friendface.countPhotosInAlbum
--
CREATE PROCEDURE friendface.countPhotosInAlbum(IN albumId INT, OUT result INT)
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

--
-- Описание для процедуры friendface.isPasswordRight
--
CREATE PROCEDURE friendface.isPasswordRight(IN loginEmail VARCHAR (20), IN passwordHash INT, OUT result BOOL)
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

--
-- Описание для процедуры friendface.isUserExists
--
CREATE PROCEDURE friendface.isUserExists(IN loginEmail VARCHAR (20), OUT result BOOL)
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

--
-- Описание для триггера friendface.checkId
--
CREATE TRIGGER friendface.checkId
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

--
-- Описание для пользователя 'flightmanager'@'localhost'
--
DROP USER 'flightmanager'@'localhost';
CREATE USER 'flightmanager'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE
ON aircraftcompany.destination
TO 'flightmanager'@'localhost';
GRANT SELECT
ON aircraftcompany.plane
TO 'flightmanager'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE
ON aircraftcompany.fly
TO 'flightmanager'@'localhost';

--
-- Описание для пользователя 'planemanager'@'localhost'
--
DROP USER 'planemanager'@'localhost';
CREATE USER 'planemanager'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE
ON aircraftcompany.plane_type
TO 'planemanager'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE
ON aircraftcompany.plane
TO 'planemanager'@'localhost';
GRANT SELECT, UPDATE
ON aircraftcompany.fly
TO 'planemanager'@'localhost';

--
-- Описание для пользователя 'root'@'%'
--
DROP USER 'root'@'%';
CREATE USER 'root'@'%';
GRANT USAGE
ON *.*
TO 'root'@'%'
WITH GRANT OPTION;

--
-- Описание для пользователя 'root'@'localhost'
--
DROP USER 'root'@'localhost';
CREATE USER 'root'@'localhost';
GRANT USAGE
ON *.*
TO 'root'@'localhost'
WITH GRANT OPTION;

--
-- Описание для пользователя 'ticketseller'@'localhost'
--
DROP USER 'ticketseller'@'localhost';
CREATE USER 'ticketseller'@'localhost';
GRANT SELECT
ON aircraftcompany.destination
TO 'ticketseller'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE
ON aircraftcompany.ticket
TO 'ticketseller'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE
ON aircraftcompany.passenger
TO 'ticketseller'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE
ON aircraftcompany.luggage
TO 'ticketseller'@'localhost';
GRANT SELECT
ON aircraftcompany.fly
TO 'ticketseller'@'localhost';

-- 
-- Вывод данных для таблицы friendface.users
-- 
INSERT INTO friendface.users VALUES (14, 'qqq@email.com', '96e79218965eb72c92a549dd5a330112', 'qqqqqqqqq', 'qqqqqqqqq');
INSERT INTO friendface.users VALUES (18, 'yayelko@mail.ru', 'e10adc3949ba59abbe56e057f20f883e', 'stas', 'fink');
INSERT INTO friendface.users VALUES (19, 'ravenhollm@gmail.com', '93279e3308bdbbeed946fc965017f67a', 'flippy', 'robinson');
INSERT INTO friendface.users VALUES (20, 'mail@trash.bin', '2467d3744600858cc9026d5ac6005305', 'asdf', 'qwer');

-- 
-- Вывод данных для таблицы friendface.albums
-- 
-- Таблица не содержит данных

-- 
-- Вывод данных для таблицы friendface.friends
-- 
INSERT INTO friendface.friends VALUES (1, 19, 18, 1);

-- 
-- Вывод данных для таблицы friendface.videos
-- 
-- Таблица не содержит данных

-- 
-- Вывод данных для таблицы friendface.wall_messages
-- 
-- Таблица не содержит данных

-- 
-- Вывод данных для таблицы friendface.photos
-- 
-- Таблица не содержит данных

/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;

