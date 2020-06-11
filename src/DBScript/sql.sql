DROP DATABASE IF EXISTS CDIOFinal;
CREATE DATABASE CDIOFinal;
USE CDIOFinal;

DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Raavare;
DROP TABLE IF EXISTS RaavareBatch;
DROP TABLE IF EXISTS Recept;
DROP TABLE IF EXISTS ProduktBatch;
DROP TABLE IF EXISTS UserProduktBatch;
DROP TABLE IF EXISTS ReceptRaavare;


CREATE TABLE Roles(
	roleId decimal(8,0),
    rolename varchar(255),
    PRIMARY KEY(roleId)
);

CREATE TABLE Users (
    userId decimal(8,0),
    userName varchar(20) UNIQUE,
    userIni varchar(8),
    CPRnummer char(10),
    password varchar(512),
    tilstand boolean default true,
    PRIMARY KEY(userId)
);

CREATE TABLE RolesUsers(
    roleId decimal(8,0),
    userId decimal(8,0),
    PRIMARY KEY(userId, roleId),
    CONSTRAINT fk_roleid FOREIGN KEY (roleid)
    REFERENCES Roles(roleId),
    CONSTRAINT fk_userid FOREIGN KEY (userid)
    REFERENCES Users(userId)
);

CREATE TABLE Raavare(
	raavareId decimal(8,0),
	raavareName varchar(20),
	PRIMARY KEY(raavareId)
);

CREATE TABLE Recept(
	receptId decimal(8,0),
	receptName varchar(20),
	PRIMARY KEY(receptId)
);

CREATE TABLE ReceptRaavare(
	receptId decimal(8,0),
	raavareId decimal(8,0),
	nonNetto decimal (8,4),
	tolerance decimal (4,1),
	PRIMARY KEY(receptId,raavareId)
);


CREATE TABLE RaavareBatch(
	rbId decimal(8,0),
	raavareId decimal(8,0),
	maengde decimal(14,4),
	leverandoer varchar(20),
	CONSTRAINT fk_raavareId1 FOREIGN KEY (raavareId)
	REFERENCES Raavare(raavareId),
	PRIMARY KEY(rbId)
);



CREATE TABLE ProduktBatch(
	pbId decimal(8,0),
	receptId decimal(8,0),
	status decimal(1,0),
	opretDato DATETIME,
	CONSTRAINT fk_receptId FOREIGN KEY (receptId)
	REFERENCES Recept(receptId),
	PRIMARY KEY(pbId)
);

CREATE TABLE UserProduktBatch(
	pbId decimal(8,0),
	userId decimal(8,0),
    rbId decimal(8,0),
    tara decimal (10,4),
    netto decimal (10,4),
    terminal decimal(5,0),
    CONSTRAINT fk_pbId FOREIGN KEY (pbId)
	REFERENCES ProduktBatch(pbId),
	CONSTRAINT fk_userId2 FOREIGN KEY (userId)
	REFERENCES Users(userId),
    CONSTRAINT fk_rbId FOREIGN KEY (rbId)
	REFERENCES RaavareBatch(rbId),
    PRIMARY KEY(pbId,userId)
);