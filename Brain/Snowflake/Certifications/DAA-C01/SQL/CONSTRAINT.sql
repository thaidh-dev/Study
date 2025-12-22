CREATE OR REPLACE TABLE employees (
    employee_id INT,
    first_name  STRING NOT NULL,       -- cannot be null
    last_name   STRING NOT NULL,       -- cannot be null
    email       STRING not null CONSTRAINT uniq_email UNIQUE ENFORCED,
    hire_date   DATE NOT NULL,
    primary key(employee_id)
);

INSERT INTO employees (employee_id, first_name, last_name, email, hire_date)
VALUES
    (1, 'Alice', 'Nguyen', 'alice.nguyen@example.com', '2023-01-15'),
    (null, 'Bao', 'Tran', 'bao.tran@example.com', '2023-02-10'),
    (3, 'Chi', 'Le', 'chi.le@example.com', '2023-03-05');

select * from employees

INSERT INTO employees (employee_id, first_name, last_name, email, hire_date)
VALUES
    (3, 'Ngoc', 'Tran', 'ngoc.tran@example.com', '1995-10-05')


select * from employees

ALTER TABLE employees 
  alter COLUMN email STRING CONSTRAINT uniq_email UNIQUE NOT ENFORCED;

-------------------------------------------------------------------------
-- Unsupported feature 'CHECK'.
CREATE TABLE EMPLOYEES (
    EMPLOYEE_ID INT PRIMARY KEY,
    SALARY NUMBER(10, 2) NOT NULL CHECK (SALARY > 0),
    DEPARTMENT VARCHAR NOT NULL CHECK (DEPARTMENT IN ('SALES', 'MARKETING', 'ENGINEERING')),
    CHECK (NOT (DEPARTMENT = 'SALES' AND (SALARY < 50000 OR SALARY > 100000)))
);

CREATE TABLE my_table (
    id INT PRIMARY KEY,
    age INT CHECK (age >= 0 AND age <= 120),
    status VARCHAR(50)
);

------------------------------------------------------------------

CREATE OR REPLACE TABLE T1(
    id integer,
    name varchar, 
    email varchar unique not null, 
    primary key(id)
);

insert into t1 values
(1, 'joe', 'joe@me.com'), 
(NULL, 'lilly', 'joe@me.com'), 
(3, 'billy', 'billye@me.com');

----------------------------------------------------------------------------





CREATE OR REPLACE TABLE employees (
    employee_id INT,
    first_name  STRING NOT NULL,
    last_name   STRING NOT NULL,
    email       STRING UNIQUE
);

INSERT INTO employees (employee_id, first_name, last_name, email)
VALUES
    (1, 'Alice', 'Nguyen', 'alice.nguyen@example.com'),
    (2, 'Bao', 'Tran', 'bao.tran@example.com'),
    (3, 'Chi', 'Le', 'chi.le@example.com'),
    (4, 'Thai', 'Duong', 'chi.le@example.com'); -- This will cause a UNIQUE constraint violation on email
    
