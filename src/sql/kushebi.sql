CREATE TABLE GAMES(
    ID INT PRIMARY KEY NOT NULL,
    TEAM1 VARCHAR(30),
    TEAM2 VARCHAR(30),
    COEFFICIENT1 NUMERIC,
    COEFFICIENT2 NUMERIC,
    COEFFICIENTX NUMERIC,
    DAY DATE,
    TIME TIME
);

CREATE TABLE TICKET (
    ID INT PRIMARY KEY NOT NULL UNIQUE,
    GAMES NUMERIC,
    BET NUMERIC,
    KUSH NUMERIC,
    WIN NUMERIC
);

CREATE TABLE GAMETCK (
    TCKID INT REFERENCES TICKET(ID) ,
    GAMEID INT REFERENCES GAMES(ID),
    VIZEDADO INT NOT NULL,
    KUSH NUMERIC


);

