CREATE TABLE `coin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `symbol` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `currency` varchar(45) NOT NULL,
  `lowestAsk` varchar(45) DEFAULT NULL,
  `highestBid` varchar(45) DEFAULT NULL,
  `volume` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `marketId` int DEFAULT NULL,
  `modifiedDate` datetime NOT NULL,
  `createdDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `coin_test`.`coin`
(`id`,`symbol`,`description`,`currency`,`lowestAsk`,`highestBid`,`volume`,`source`,`marketId`,`modifiedDate`,`createdDate`)
VALUES
(1,'BTC','Bitcoin','TRY',0,0,0,'Jmeter','1','2022-03-03 21:00:00','2022-03-03 21:00:00');

INSERT INTO `coin_test`.`coin`
(`id`,`symbol`,`description`,`currency`,`lowestAsk`,`highestBid`,`volume`,`source`,`marketId`,`modifiedDate`,`createdDate`)
VALUES
(2,'BTC','Bitcoin','TRY',0,0,0,'Jmeter','2','2022-03-03 21:00:00','2022-03-03 21:00:00');

INSERT INTO `coin_test`.`coin`
(`id`,`symbol`,`description`,`currency`,`lowestAsk`,`highestBid`,`volume`,`source`,`marketId`,`modifiedDate`,`createdDate`)
VALUES
(3,'BTC','Bitcoin','TRY',0,0,0,'Jmeter','3','2022-03-03 21:00:00','2022-03-03 21:00:00');

INSERT INTO `coin_test`.`coin`
(`id`,`symbol`,`description`,`currency`,`lowestAsk`,`highestBid`,`volume`,`source`,`marketId`,`modifiedDate`,`createdDate`)
VALUES
(4,'BTC','Bitcoin','TRY',0,0,0,'Jmeter','4','2022-03-03 21:00:00','2022-03-03 21:00:00');

INSERT INTO `coin_test`.`coin`
(`id`,`symbol`,`description`,`currency`,`lowestAsk`,`highestBid`,`volume`,`source`,`marketId`,`modifiedDate`,`createdDate`)
VALUES
(5,'BTC','Bitcoin','TRY',0,0,0,'Jmeter','5','2022-03-03 21:00:00','2022-03-03 21:00:00');

CREATE TABLE `coin_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coinId` int NOT NULL,
  `currency` varchar(45) NOT NULL,
  `lowestAsk` varchar(45) DEFAULT NULL,
  `highestBid` varchar(45) DEFAULT NULL,
  `volume` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `marketId` int DEFAULT NULL,
  `createdDate` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `market` (
  `id` int NOT NULL AUTO_INCREMENT,
  `marketName` varchar(45) DEFAULT NULL,
  `sourceType` varchar(45) DEFAULT NULL,
  `sourceUrl` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `coin_test`.`market`
(`id`,`marketName`,`sourceType`,`sourceUrl`)
VALUES
(1,'Paribu','API','https://www.paribu.com/ticker');

INSERT INTO `coin_test`.`market`
(`id`,`marketName`,`sourceType`,`sourceUrl`)
VALUES
(2,'BTCTurk','API','https://api.btcturk.com/api/v2/ticker');

INSERT INTO `coin_test`.`market`
(`id`,`marketName`,`sourceType`,`sourceUrl`)
VALUES
(3,'Binance','API','https://api.binance.com/api/v3/ticker/24hr');

INSERT INTO `coin_test`.`market`
(`id`,`marketName`,`sourceType`,`sourceUrl`)
VALUES
(4,'CoinMarketCap-Mobile','Android','https://play.google.com/store/apps/details?id=com.coinmarketcap.android&hl=tr&gl=US');

INSERT INTO `coin_test`.`market`
(`id`,`marketName`,`sourceType`,`sourceUrl`)
VALUES
(5,'CoinMarketCap-Web','Web','https://coinmarketcap.com/currencies/bitcoin/');

