CREATE TABLE config (
	id VARCHAR(45) PRIMARY KEY,
	str_value VARCHAR(255),
	int_value INT
);

CREATE TABLE account (
	id SERIAL PRIMARY KEY,
	name VARCHAR(45) UNIQUE,
	email VARCHAR(255) UNIQUE,
	password VARCHAR(166)
);

CREATE TABLE article (
	id SERIAL PRIMARY KEY,
	title VARCHAR(255),
	content TEXT
);

CREATE TABLE question (
	id SERIAL PRIMARY KEY,
	pos INT,
	name VARCHAR(255),
	description TEXT,
	num_choices INT
);

CREATE TABLE choice (
	id SERIAL PRIMARY KEY,
	question INT REFERENCES question(id),
	pos INT,
	name VARCHAR(255),
	UNIQUE(question, pos)
);

CREATE TABLE label (
	article INT REFERENCES article(id),
	account INT REFERENCES account(id),
	question INT REFERENCES question(id),
	choice INT REFERENCES choice(id),
	PRIMARY KEY (article, account, question)
);

INSERT INTO config (id, int_value) VALUES ('reviews_per_article', 10);
