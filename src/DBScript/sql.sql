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