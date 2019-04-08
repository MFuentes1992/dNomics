
create database dnomics_open;
use dnomics_open;

/*Country no longer needed*/
/*CREATE TABLE country (country_alphaID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, country_alpha varchar(2) NOT NULL , country_name varchar(50) NOT NULL);*/
CREATE TABLE user_status (statusID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, status_name varchar(50) NOT NULL);
CREATE TABLE report_status (statusID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, status_name varchar(50) NOT NULL);
CREATE TABLE currency(currencyID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, currency_code varchar(5) NOT NULL, currency_name varchar(100) not null);
CREATE TABLE category(categoryID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, category_name varchar(50));

CREATE TABLE usuario (userID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, first_name varchar(50), last_name varchar(50), 
	user_password varchar(100), user_email varchar(100) NOT NULL, currencyID INTEGER NOT NULL,create_date date NOT NULL, update_date date NOT NULL,
    img_url text,  statusID integer, create_source varchar(100),FOREIGN KEY (statusID) REFERENCES user_status(statusID), FOREIGN KEY(currencyID) REFERENCES currency(currencyID));
    
CREATE TABLE report_header (reportID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, userID INTEGER NOT NULL, report_name varchar(100) NOT NULL, 
	report_date date NOT NULL, report_location varchar(100) NOT NULL, report_number varchar(50) NOT NULL, report_total REAL NOT NULL, statusID INTEGER NOT NULL,
	FOREIGN KEY (userID) REFERENCES usuario(userID),FOREIGN KEY (statusID) REFERENCES report_status(statusID));

CREATE TABLE line_item (lineitemID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, reportID INTEGER NOT NULL, lineitem_date date NOT NULL, 
	lineitem_total REAL NOT NULL, lineitem_description text NOT NULL, lineitem_merchant varchar(100) NOT NULL, 
	categoryID integer not null, FOREIGN KEY (reportID) REFERENCES report_header(reportID), foreign key(categoryID) references category(categoryID));

CREATE TABLE routing_process(routingID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, userID INTEGER NOT NULL, reportID INTEGER NOT NULL,
	FOREIGN KEY (userID) REFERENCES usuario(userID),FOREIGN KEY (reportID) REFERENCES report_header(reportID));

INSERT INTO user_status(status_name)Values('Pending');
INSERT INTO user_status(status_name)Values('Active');
INSERT INTO user_status(status_name)Values('Deleted');

insert into report_status(status_name)Values('Draft');
insert into report_status(status_name)Values('Active');
insert into report_status(status_name)Values('Paid');

insert into currency(currency_code, currency_name) values('USD', 'American Dolars');
insert into currency(currency_code, currency_name) values('MXN', 'Mexican Peso');


insert into usuario(user_email, user_password, currencyID, create_date, update_date, statusID, create_source)values('prueba3@hotmail.com', 'des2tramp2dos2', 2, '2019-03-30', '2019-03-30', 1, 'Email');
select userID, first_name, last_name, user_password, user_email, create_date, update_date, img_url, create_source , currency_name, status_name 
	from usuario u, user_status us, currency c 
		where u.statusID = us.statusID and u.currencyID = c.currencyID and u.user_email = 'prueba3@hotmail.com' 
		AND u.user_password = 'des2tramp2dos2' AND u.create_source <> 'Facebook';
        
select userID, first_name, last_name, user_password, user_email, currencyID, create_date, update_date, img_url, statusID, create_source , currency_name, status_name
    	   from usuario u, user_status us, currency c
    		   where u.statusID = us.statusID and u.currencyID = c.currencyID and u.user_email = 'prueba3@hotmail.com'
    		     AND u.user_password ='des2tramp2dos2' AND u.create_source <> 'Facebook'

UPDATE usuario set img_data = 0 where userID = 8;
desc usuario;
select * from usuario;
SELECT * FROM usuario WHERE user_email = 'prueba3@hotmail.com' AND user_password = 'des2tramp2dos2' AND create_source <> 'Facebook';
SELECT * FROM usuario WHERE userID = 15;