CREATE TABLE `carteira` (
    `carCodi` varchar(64) NOT NULL,
    `usuCodi` varchar(64) NULL,
    `carSaldo` decimal(14,4) DEFAULT NULL,
    PRIMARY KEY (`carCodi`)
) ENGINE=InnoDB;


