CREATE TABLE config (
	key VARCHAR(45) PRIMARY KEY,
	str_value VARCHAR(255),
	int_value INTEGER
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
	pos INTEGER,
	name VARCHAR(255),
	description TEXT,
	num_choices INTEGER
);

CREATE TABLE choice (
	id SERIAL PRIMARY KEY,
	question INTEGER REFERENCES question(id),
	pos INTEGER,
	name VARCHAR(255),
	UNIQUE(question, pos)
);

CREATE TABLE label (
	article INTEGER REFERENCES article(id),
	account INTEGER REFERENCES account(id),
	question INTEGER REFERENCES question(id),
	choice INTEGER REFERENCES choice(id),
	PRIMARY KEY (article, account, question)
);

INSERT INTO config (key, int_value) VALUES ('reviews_per_article', 10);
