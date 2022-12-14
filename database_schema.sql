DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS people;

CREATE TABLE person (
    person_id  INTEGER      PRIMARY KEY AUTOINCREMENT
    						UNIQUE
                            NOT NULL,
    first_name VARCHAR (20) NOT NULL,
    last_name  VARCHAR (20) NOT NULL,
    birth_date DATE         NOT NULL
);

CREATE TABLE people (
    people_id    INTEGER      PRIMARY KEY AUTOINCREMENT
                              UNIQUE
                              NOT NULL,
    adult_count INTEGER      NOT NULL,
    child_count INTEGER      NOT NULL,
    timestamp DATETIME DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime'))
);