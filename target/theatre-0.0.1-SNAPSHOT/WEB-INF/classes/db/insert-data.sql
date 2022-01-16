INSERT INTO performances (performance_name, performance_start_date, performance_end_date) VALUES ('Chaika', '2020-03-20', '2020-04-10');
INSERT INTO performances (performance_name, performance_start_date, performance_end_date) VALUES ('Scarlet Sails', '2020-04-01', '2020-04-20');
INSERT INTO performances (performance_name, performance_start_date, performance_end_date) VALUES ('War and Peace', '2020-05-10', '2020-05-30');
INSERT INTO performances (performance_name, performance_start_date, performance_end_date) VALUES ('BOEING-BOEING-BOEING', '2020-03-20', '2020-04-10');
INSERT INTO performances (performance_name, performance_start_date, performance_end_date) VALUES ('House with mezzanine', '2020-03-20', '2020-04-10');

INSERT INTO halls (hall_name, rows_number, seats_number) values ('bigHall', '40', '1000');
INSERT INTO halls (hall_name, rows_number, seats_number) values ('smallHall', '15', '375');
INSERT INTO halls (hall_name, rows_number, seats_number) values ('chamberHall', '20', '400');
INSERT INTO halls (hall_name, rows_number, seats_number) values ('musicHall', '25', '500');
INSERT INTO halls (hall_name, rows_number, seats_number) values ('mainHall', '25', '450');

INSERT INTO sessions (performance_id, session_start_dateTime, session_duration) VALUES ('1', '2020-03-20 21:30:00', '120');
INSERT INTO sessions (performance_id, session_start_dateTime, session_duration) VALUES ('2', '2020-04-01 10:00:00', '120');
INSERT INTO sessions (performance_id, session_start_dateTime, session_duration) VALUES ('3', '2020-05-11 12:00:00', '150');
INSERT INTO sessions (performance_id, session_start_dateTime, session_duration) VALUES ('4', '2020-04-01 18:00:00', '130');
INSERT INTO sessions (performance_id, session_start_dateTime, session_duration) VALUES ('5', '2020-05-10 19:00:00', '100');

INSERT INTO seats (hall_id, row_number, seat_number) VALUES ('1', '1', '1');
INSERT INTO seats (hall_id, row_number, seat_number) VALUES ('2', '1', '1');
INSERT INTO seats (hall_id, row_number, seat_number) VALUES ('3', '1', '1');
INSERT INTO seats (hall_id, row_number, seat_number) VALUES ('4', '1', '1');
INSERT INTO seats (hall_id, row_number, seat_number) VALUES ('5', '1', '1');

INSERT INTO tickets (session_id, seat_id, price) VALUES ('1', '1', '5.00');
INSERT INTO tickets (session_id, seat_id, price) VALUES ('2', '2', '10.50');
INSERT INTO tickets (session_id, seat_id, price) VALUES ('3', '3', '11.50');
INSERT INTO tickets (session_id, seat_id, price) VALUES ('4', '4', '15.50');
INSERT INTO tickets (session_id, seat_id, price) VALUES ('5', '5', '13.25'); 

INSERT INTO accounts (username,ROLE) VALUES ('user1','USER');
INSERT INTO accounts (username,ROLE) VALUES ('user2','USER');
INSERT INTO accounts (username,ROLE) VALUES ('admin','ADMIN');
