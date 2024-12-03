CREATE TABLE IF NOT EXISTS person
(
    person_id  INTEGER     NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(20) NOT NULL,
    last_name  VARCHAR(20) NOT NULL,
    gender     CHAR(1)     NOT NULL,
    dob        DATE,
    income     DOUBLE,
    PRIMARY KEY (person_id)
);