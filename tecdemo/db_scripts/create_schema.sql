
DROP TABLE emplyoee;
CREATE TABLE employee
(id int,
 employeenumber INT,
 login VARCHAR(255),
 firstname VARCHAR(255),
 lastname VARCHAR(255)
);

DROP TABLE customer;
CREATE TABLE customer
(id int,
 customerenumber INT,
 login VARCHAR(255),
 customertypeid INT,
 firstname VARCHAR(255),
 lastname VARCHAR(255),
 birthdate DATE,
 associationid INT
);

DROP TABLE customertype;
CREATE TABLE customertype
(id int,
 employeenumber INT,
 login VARCHAR(255),
 firstname VARCHAR(255),
 lastname VARCHAR(255)
);