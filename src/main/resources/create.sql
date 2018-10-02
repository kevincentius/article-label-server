CREATE TABLE account (
	id SERIAL,
	name VARCHAR(45) UNIQUE,
	email VARCHAR(255) UNIQUE,
	password VARCHAR(166)
);
