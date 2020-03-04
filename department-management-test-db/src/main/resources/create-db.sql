
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS employee;

CREATE TABLE department (
  department_id INT NOT NULL AUTO_INCREMENT,
  department_name VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (department_id)
);

CREATE TABLE employee (
  employee_id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  department_id int NOT NULL,
  salary int NOT NULL,
  PRIMARY KEY (employee_id)
);

ALTER TABLE employee ADD FOREIGN KEY (department_id) REFERENCES department(department_id);

