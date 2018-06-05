CREATE TABLE users(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) ,
	mail VARCHAR(50),
	pass VARCHAR(50)
);

INSERT INTO users(name, mail, pass)
VALUES('山田', 'yamada@mail', MD5('yamada1'));
INSERT INTO users(name, mail, pass)
VALUES('田中', 'tanaka@mail', MD5('tanaka67'));
INSERT INTO users(name, mail, pass)
VALUES('中田', 'nakata@mail', MD5('nakata6'));

