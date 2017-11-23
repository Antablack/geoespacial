# geoespacial
This project is based in this repository https://github.com/alexishevia/postgres_geoespacial

steps:

1.Create database geolocalizacion

2.execute this script:

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO"; SET AUTOCOMMIT = 0; START TRANSACTION; SET time_zone = "+00:00";

  CREATE TABLE `stops` (
    `ID` int(11) NOT NULL,
    `LATITUDE` float NOT NULL,
    `LONGITUDE` float NOT NULL,
    `DESCRIPTION` varchar(500) NOT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;


  ALTER TABLE `stops`
    ADD PRIMARY KEY (`ID`);

  ALTER TABLE `stops`
    MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;COMMIT;
    

3.execute project "server" in netbeans. 


4.then entry to folder "client" and execute "npm install && npm start" 


5.after open http://localhost:4200 in your browser
