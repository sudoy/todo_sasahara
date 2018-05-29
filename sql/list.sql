CREATE TABLE list(
	id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(100),
	detail VARCHAR(100),
	imp VARCHAR(3),
	limit_date DATE
);

INSERT INTO list(title, detail, imp, limit_date)
VALUES("テスト", "SQLのテスト", "★★★", "2015-06-20");
INSERT INTO list(title, detail, imp, limit_date)
VALUES("テスト2", "Javaのテスト", "★", "2015-06-21");
INSERT INTO list(title, detail, imp, limit_date)
VALUES("テスト3", "HTMLのテスト", "★★", "2015-06-24");
INSERT INTO list(title, detail, imp)
VALUES("テスト4", "なにかのテスト", "★");