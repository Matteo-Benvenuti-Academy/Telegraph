DROP DATABASE IF EXISTS chatDB;
CREATE DATABASE chatDB;
USE chatDB;

CREATE TABLE chat_user(
	userID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
	username VARCHAR(250) UNIQUE NOT NULL,
	pass VARCHAR(250) NOT NULL,
	email VARCHAR(250) UNIQUE NOT NULL,
    removed BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE chat_room(
	roomID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
	roomname VARCHAR(250) NOT NULL,
	uniquecode VARCHAR(250) UNIQUE NOT NULL,
    roomdescription VARCHAR(250) NOT NULL
);

CREATE TABLE chat_room_subscription(
	subscriptionID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
	userRIF INTEGER NOT NULL,
    roomRIF INTEGER NOT NULL,
	isadmin BOOLEAN NOT NULL,
    FOREIGN KEY (userRIF) REFERENCES chat_user(userID),
    FOREIGN KEY (roomRIF) REFERENCES chat_room(roomID) ON DELETE CASCADE,
    UNIQUE(userRIF,roomRIF)
);

CREATE TABLE chat_message(
	messageID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
	sendtime DATETIME NOT NULL,
	messagetext VARCHAR(500) NOT NULL,
    userRIF INTEGER NOT NULL,
    roomRIF INTEGER NOT NULL,
    FOREIGN KEY (userRIF) REFERENCES chat_user(userID),
    FOREIGN KEY (roomRIF) REFERENCES chat_room(roomID) ON DELETE CASCADE
);

INSERT INTO chat_user(username, pass,email) VALUES
('Matteo','1234','pippo@gmail.com'),
('Valerione','1234','ancheio@gmail.com');

INSERT INTO chat_room(roomName, uniquecode,roomdescription) VALUES
('Stanza1','STA1',"Descrizione 1"),
('Stanza2','STA2',"Descrizione 2");

INSERT INTO chat_room_subscription(userRIF,roomRIF,isadmin) VALUES
(1,1,true),
(2,1,false),
(2,2,true);

INSERT INTO chat_message(sendtime,messagetext,userRIF,roomRIF) VALUES
(CURRENT_TIME(),"Ciao STANZA1!",1,1),
(CURRENT_TIME(),"Ciao STANZA2!",2,2);

