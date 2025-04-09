DROP TABLE IF EXISTS Author CASCADE;
DROP TABLE IF EXISTS Book CASCADE;
DROP TABLE IF EXISTS Book_Author CASCADE;
DROP TABLE IF EXISTS Magazine CASCADE;
DROP TABLE IF EXISTS Publisher CASCADE;

CREATE TABLE IF NOT EXISTS Author (
	id serial NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT author_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Book (
	id serial NOT NULL,
	title varchar(200) NOT NULL,
	quantity integer NOT NULL,
	CONSTRAINT book_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Book_Author (
	id serial NOT NULL,
	book_id integer,
	author_id integer,
	CONSTRAINT bookauthor_pkey PRIMARY KEY (id),
	CONSTRAINT fk_author_id FOREIGN KEY (author_id)
		REFERENCES Author (id) MATCH SIMPLE
		ON UPDATE CASCADE
		ON DELETE CASCADE
		NOT VALID,
	CONSTRAINT fk_book_id FOREIGN KEY (book_id)
		REFERENCES Book (id) MATCH SIMPLE
		ON UPDATE CASCADE
		ON DELETE CASCADE
		NOT VALID
);

CREATE TABLE IF NOT EXISTS Publisher (
	id serial NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT publisher_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Magazine (
	id serial NOT NULL,
	title varchar(200) NOT NULL,
	quantity integer NOT NULL,
	publisher_id integer,
	CONSTRAINT magazine_pkey PRIMARY KEY (id),
	CONSTRAINT fk_publisher_id FOREIGN KEY (publisher_id)
		REFERENCES Publisher (id) MATCH SIMPLE
		ON UPDATE CASCADE
		ON DELETE SET NULL
		NOT VALID
);

INSERT INTO Author (name)
VALUES ('Herbert Schildt'),
       ('Erich Gamma'),
       ('Richard Helm'),
       ('Ralph Johnson'),
       ('John Vlissides'),
       ('Aditya Y. Bhargava'),
       ('Leo Tolstoy'),
       ('Mark Twain'),
       ('Frank Herbert');

INSERT INTO Book (title, quantity)
VALUES ('Java The Complete Reference 9th Edition', 40),
       ('Design Patterns: Elements of Reusable Object-Oriented Software', 30),
       ('Grokking Algorithms', 25),
       ('War and Peace', 30),
       ('Adventures of Huckleberry Finn', 50),
       ('Dune', 45);

INSERT INTO Book_Author (book_id, author_id)
VALUES (1, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (3, 6),
       (4, 7),
       (5, 8),
       (6, 9);

INSERT INTO Publisher (name)
VALUES ('National Geographic Society'),
       ('The National Trust'),
       ('Immediate Media Company');

INSERT INTO Magazine (title, quantity, publisher_id)
VALUES ('National Geographic', 50, 1),
       ('National Trust Magazine', 40, 2),
       ('Radio Times', 25, 3),
       ('BBC Good Food', 20, 3),
       ('BBC Gardeners'' World', 20, 3),
       ('BBC Top Gear Magazine', 20, 3);
