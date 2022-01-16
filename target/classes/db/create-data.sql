CREATE TABLE performances (
performance_id LONG NOT NULL AUTO_INCREMENT,
performance_name VARCHAR(100) NOT NULL,
performance_start_date DATE NOT NULL,
performance_end_date DATE NOT NULL,
PRIMARY KEY (performance_id));

CREATE TABLE halls (
hall_id INT NOT NULL AUTO_INCREMENT,
hall_name VARCHAR(100) NOT NULL,
rows_number INT NOT NULL,
seats_number INT NOT NULL,
PRIMARY KEY (hall_id));

CREATE TABLE sessions (
session_id INT NOT NULL AUTO_INCREMENT,
performance_id INT NOT NULL,
session_start_dateTime DATETIME NOT NULL,
session_duration INT NOT NULL,
PRIMARY KEY (session_id));


CREATE TABLE seats (
seat_id INT NOT NULL AUTO_INCREMENT,
hall_id INT NOT NULL,
row_number INT NOT NULL,
seat_number INT NOT NULL,
PRIMARY KEY (seat_id));

CREATE TABLE tickets (
ticket_id INT NOT NULL AUTO_INCREMENT,
session_id INT NOT NULL,
seat_id INT NOT NULL,
price DOUBLE NOT NULL,
PRIMARY KEY (ticket_id));

CREATE TABLE accounts (
id INT NOT NULL AUTO_INCREMENT,
username VARCHAR(50) NOT NULL,
ROLE VARCHAR(50) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE carts (
cart_id INT NOT NULL AUTO_INCREMENT,
account_id INT NOT NULL,
PRIMARY KEY (cart_id));

CREATE TABLE cart_items (
id INT NOT NULL AUTO_INCREMENT,
cart_id INT NOT NULL,
ticket_id INT NOT NULL,
session_id INT NOT NULL,
seat_id INT NOT NULL,
price DOUBLE NOT NULL,
PRIMARY KEY (id));
