CREATE TABLE country (country_alphaID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, country_alpha varchar(2) NOT NULL , country_name varchar(50) NOT NULL);
CREATE TABLE user_status (statusID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, status_name varchar(50) NOT NULL);
CREATE TABLE report_status (statusID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, rstatus_name varchar(50) NOT NULL);

CREATE TABLE person (personID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, first_name varchar(50) NOT NULL, last_name varchar(50) NOT NULL, uniqueID varchar(4) NOT NULL, username varchar(30) NOT NULL, person_password BLOB NOT NULL, email varchar(100) NOT NULL, country_alphaID integer,FOREIGN KEY (country_alphaID) REFERENCES country(country_alphaID), birth_date date NOT NULL, create_date date NOT NULL, update_date date NOT NULL, img_data BLOB,  statusID integer,FOREIGN KEY (statusID) REFERENCES user_status(statusID));
CREATE TABLE report_header (reportID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, personID INTEGER NOT NULL, report_name varchar(100) NOT NULL, report_date date NOT NULL, report_location varchar(100) NOT NULL, report_number varchar(50) NOT NULL, report_total REAL NOT NULL, statusID INTEGER NOT NULL,FOREIGN KEY (personID) REFERENCES person(personID),FOREIGN KEY (statusID) REFERENCES report_status(statusID));
CREATE TABLE line_item (lineitemID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, reportID INTEGER NOT NULL, lineitem_date date NOT NULL, lineitem_total REAL NOT NULL, lineitem_purpose varchar(100) NOT NULL, lineitem_merchant varchar(50) NOT NULL, lineitem_allocation varchar(100) NOT NULL, FOREIGN KEY (reportID) REFERENCES report_header(reportID));
CREATE TABLE routing_process(routingID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, personID INTEGER NOT NULL, reportID INTEGER NOT NULL,FOREIGN KEY (personID) REFERENCES person(personID),FOREIGN KEY (reportID) REFERENCES report_header(reportID));

select * from user_status;
select * from mysql.user;

desc country;


INSERT INTO country(country_alpha, country_name) VALUES ('US', 'United States of America');
select * from country
