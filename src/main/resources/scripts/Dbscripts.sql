CREATE TABLE IF NOT EXISTS EMPLOYEE (
    ID int NOT NULL AUTO_INCREMENT,
    FIRST_NAME varchar(25) NOT NULL,
    LAST_NAME varchar(25),
    COMPANY_NAME varchar(255),
    ADDRESS varchar(255),
    CITY varchar(25),
    COUNTRY varchar(25),
    STATE varchar(25),
    ZIP int,
    CREATED_DATE varchar(25),
    UPDATED_DATE varchar(25),
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS STUDENT (
    ID int NOT NULL AUTO_INCREMENT,
    STUDENT_ID BIGINT,
    STUDENT_NAME varchar(50) NOT NULL,
    FATHER_NAME varchar(50),
    GENDER varchar(25),
    MOBILE BIGINT,
    CATEGORY varchar(255),
    CREATED_DATE varchar(25),
    UPDATED_DATE varchar(25),
    PRIMARY KEY (ID)
);

commit;