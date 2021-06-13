CREATE TABLE `transacaoativo` (
   `tatiCodi` varchar(64) NOT NULL,
   `carCodi` varchar(64) NOT NULL,
   `empCodi` varchar(64) not NULL,
   `tatiData` datetime not null,
   `tatiQtd` decimal(14,4) not NULL,
   `tatiTipo` int not NULL,
   PRIMARY KEY (`tatiCodi`)
) ENGINE=InnoDB;