
set foreign_key_checks=0;


INSERT INTO department
VALUES (DEFAULT, 'Department1');
INSERT INTO department
VALUES (DEFAULT, 'Department2');
INSERT INTO department
VALUES (DEFAULT, 'Department3');

INSERT INTO employee
VALUES (DEFAULT, 'Ivan', 'Ivanov', 1, 3000);

INSERT INTO employee
VALUES (DEFAULT, 'Petr', 'Petrov', 2, 2000);

INSERT INTO employee
VALUES (DEFAULT, 'Sergey', 'Ivanov',3, 1000);

INSERT INTO employee
VALUES (DEFAULT, 'Mike', 'Djonson', 1, 2500);

INSERT INTO employee
VALUES (DEFAULT, 'Artem', 'Lopata', 2, 2300);

INSERT INTO employee
VALUES (DEFAULT,'Astra', 'Mironova', 3, 1800);

INSERT INTO employee
VALUES (DEFAULT, 'Zlata', 'Maximochkina',2, 1700);

INSERT INTO employee
VALUES (DEFAULT, 'Daria', 'Lihacheva', 3, 1600);

set foreign_key_checks=1;