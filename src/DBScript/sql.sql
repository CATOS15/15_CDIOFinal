DROP DATABASE IF EXISTS CDIOFinal;
CREATE DATABASE CDIOFinal;
USE CDIOFinal;


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
	PRIMARY KEY(receptId,raavareId),
    constraint fk_receptId1 foreign key(receptId)
    references Recept (receptId),
    constraint fk_raavareId foreign key(raavareId)
    references Raavare(raavareId)

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
	status ENUM('Oprettet', 'Under produktion', 'Afsluttet') DEFAULT 'Oprettet',
	opretDato DATETIME,
	slutDato DATETIME,
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
	REFERENCES ProduktBatch(pbId) ON DELETE RESTRICT,
	CONSTRAINT fk_userId2 FOREIGN KEY (userId)
	REFERENCES Users(userId) ON DELETE RESTRICT,
    CONSTRAINT fk_rbId FOREIGN KEY (rbId)
	REFERENCES RaavareBatch(rbId) ON DELETE RESTRICT,
    PRIMARY KEY(pbId,userId,rbId)
);

DELIMITER ///
CREATE TRIGGER ins_userproduktbatch AFTER INSERT ON UserProduktBatch
    FOR EACH ROW
    BEGIN
        IF((SELECT status FROM ProduktBatch as pb WHERE NEW.pbId = pb.pbId) != 'Afsluttet') THEN
			UPDATE ProduktBatch as pb SET status = 'Under produktion' WHERE NEW.pbId = pb.pbId;
        END IF;
    END;
///

INSERT INTO roles VALUES(1, "Administrator"),(2, "Farmaceut"),(3, "Produktionsleder"),(4, "Laborant");
INSERT INTO Users VALUES (123,'admin','adm','1234567891','10fa6a38dde194c6e2f03cfa8a2c0e1aaa52d11e553a2686d0fb56f4e9f5647518aea8d4a2633065638a349dc2c2aab6ab2bde3dbd0df55b1d233ecac9f163cc',true);
INSERT INTO rolesusers VALUES(1, 123);