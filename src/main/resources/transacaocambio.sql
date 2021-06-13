CREATE TABLE `transacaocambio` (
   `tcamCodi` varchar(64) NOT NULL,
   `carCodi` varchar(64) NOT NULL,
   `cotCodi` varchar(64) not NULL,
   `tcamData` datetime not null,
   `tcamQtd` decimal(14,4) not NULL,
   `tcamTipo` int not NULL,
   PRIMARY KEY (`tcamCodi`)
) ENGINE=InnoDB;
