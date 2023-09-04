DROP TABLE IF EXISTS player_coach;
DROP TABLE IF EXISTS coach;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS location;


CREATE TABLE location (
location_id int NOT NULL AUTO_INCREMENT,
team_address varchar(256) NOT NULL,
street_address varchar(128) NOT NULL,
city varchar(60),
state varchar(40),
zip varchar(20),
phone varchar(30),
PRIMARY KEY (location_id)
);

CREATE TABLE player (
player_id int NOT NULL AUTO_INCREMENT,
location_id int NULL,
name varchar(60) NOT NULL,
player_position varchar(128),
age int,
PRIMARY KEY (player_id),
FOREIGN KEY (location_id) REFERENCES location (location_id) ON DELETE CASCADE
);

CREATE TABLE coach (
coach_id int NOT NULL AUTO_INCREMENT,
name varchar(128),
PRIMARY KEY (coach_id)
);

CREATE TABLE player_coach (
player_id int NOT NULL,
coach_id int NOT NULL,
FOREIGN KEY (player_id) REFERENCES player (player_id) ON DELETE CASCADE,
FOREIGN KEY (coach_id) REFERENCES coach (coach_id) ON DELETE CASCADE
);
